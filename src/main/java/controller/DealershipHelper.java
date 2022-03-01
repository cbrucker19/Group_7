package controller;

import java.util.List;

import javax.persistence.*;

import model.Dealership;

/**
 * @author William Thomas, wdthomas2
 * CIS175 - Spring 2022
 * Feb 28, 2022
 */
public class DealershipHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("OrderList");
	
	public void insertDealership(Dealership d) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(d);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Dealership> showAllDealerships() {
		EntityManager em = emfactory.createEntityManager();
		List<Dealership> allDealers = em.createQuery("Select d FROM DEALERSHIP d").getResultList();
		return allDealers;
	}
	
	public Dealership findDealership(String nameToLookup) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Dealership> typedQuery = em.createQuery("select d from dealerhsip d where d.dealerName = :selectedName", Dealership.class);
		typedQuery.setParameter("selectedName", nameToLookup);
		typedQuery.setMaxResults(1);
		Dealership foundDealer;
		try {
			foundDealer = typedQuery.getSingleResult();
		} catch (NoResultException e) {
			foundDealer = new Dealership(nameToLookup);
		}
		em.close();
		return foundDealer;
	}
}
