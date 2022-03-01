package model;

import javax.persistence.*;

/**
 * @author William Thomas, wdthomas2
 * CIS175 - Spring 2022
 * Feb 28, 2022
 */

/*
 * Notes:
 * 
 */
@Entity
@Table(name="dealer")
public class Dealership {
	@Id
	@GeneratedValue
	private int id;
	private String dealerName;
	private String managerName;
	
	public Dealership() {
		super();
	}
	
	public Dealership(int id, String dealerName, String managerName) {
		super();
		this.id = id;
		this.dealerName = dealerName;
		this.managerName = managerName;
	}
	
	//overloaded constructor for when manager name is not known
	public Dealership(int id, String dealerName) {
		super();
		this.id = id;
		this.dealerName = dealerName;
	}
	//constructor for auto-generations without manager known
	public Dealership(String dealerName) {
		super();
		this.dealerName = dealerName;
	}
	//constructor for auto-generations with manager known
	public Dealership(String dealerName, String managerName) {
		super();
		this.dealerName = dealerName;
		this.managerName = managerName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	@Override
	public String toString() {
		return "Dealership [id=" + id + ", dealerName=" + dealerName + ", managerName=" + managerName + "]";
	}
}
