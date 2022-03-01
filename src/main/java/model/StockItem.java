package model;
import javax.persistence.*;

/**
 * @author William Thomas, wdthomas2
 * CIS175 - Spring 2022
 * Feb 28, 2022
 */


/*
 * Notes:
 *   formatting float to currency format is  String.format("%.2f",**varName**)
 *   overloaded constructors are present since Id can be generated value
 *   make sure when calculating with price you use rounding to 2 decimal places
 *   
 */

@Entity
@Table(name="items")
public class StockItem {
	@Id
	@GeneratedValue
	private int id;
	private String make;
	private String item;
	private float price;
	
	public StockItem() {
		super();
	}
	
	public StockItem(int id, String make, String item, float price) {
		super();
		this.id = id;
		this.make = make;
		this.item = item;
		this.price = price;
	}
	
	public StockItem(String make, String item, float price) {
		super();
		this.make = make;
		this.item = item;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "StockItem [id=" + id + ", make=" + make + ", item=" + item + ", price=" + price + "]";
	}
	
	public String returnItemDetails() {
		return this.make + ": " + this.item + " -$" + String.format("%.2f",this.price);
	}
	
}
