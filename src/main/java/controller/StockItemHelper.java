package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import javax.persistence.TypedQuery;
import model.StockItem;

/**
 * @author William Thomas, wdthomas2
 * CIS175 - Spring 2022
 * Feb 28, 2022
 */


/*
 * insertItem(StockItem) - void
 * showAllItems() - returns list
 * deleteItem(StockItem) - void, removes entity from table
 * searchForItemByMake(String) - finds entity matching make
 * searchForItemByItem(String) - returns entity matching item
 * searchForItemById(int) - returns entity matching id
 * updateItem(StockItem) - merges input data into matching entity id
 */
public class StockItemHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OrderList");
	
	public void insertItem(StockItem item) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(item);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<StockItem> showAllItems() {
		EntityManager em = emfactory.createEntityManager();
		List<StockItem> allItems = em.createQuery("select si from StockItem si", StockItem.class).getResultList();
		return allItems;
	}
	
	public void deleteItem(StockItem toDelete) {
		EntityManager em = emfactory.createEntityManager(); 
		em.getTransaction().begin();
		TypedQuery<StockItem> typedQuery = em.createQuery("select si from StockItem si where si.make = :selectedMake and si.item = :selectedItem", StockItem.class);
		typedQuery.setParameter("selectedMake", toDelete.getMake());
		typedQuery.setParameter("selectedItem", toDelete.getItem());
		//only want a single result
		typedQuery.setMaxResults(1);
		//get result and save as new object
		StockItem result = typedQuery.getSingleResult();
		//now remove the object from table
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<StockItem> searchForItemByMake(String thisMake) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<StockItem> typedQuery = em.createQuery("select si from StockItem si where si.make = :selectedMake", StockItem.class);
		typedQuery.setParameter("selectedMake", thisMake);
		List<StockItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public List<StockItem> searchForItemByItem(String thisItem) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<StockItem> typedQuery = em.createQuery("select si from StockItem si wher si.item = :selectedItem", StockItem.class);
		typedQuery.setParameter("selectedItem", thisItem);
		List<StockItem> foundItems = typedQuery.getResultList();
		em.close();
		return foundItems;
	}
	
	public StockItem searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		StockItem found = em.find(StockItem.class, idToEdit);
		em.close();
		return found;
	}
	
	public void updateItem(StockItem toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
