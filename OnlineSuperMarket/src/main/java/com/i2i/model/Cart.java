/**
 * 
 */
package com.i2i.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.i2i.model.Product;
/**
 * <p> Cart model object.
 * <p>Contains various attributes of model and its getters and setters. 
 * @author Mukilan.K
 *
 * @version 1.0

 */
@Entity
@Table(name="Cart")
public class Cart {
	

	private int id;
    private PurchaseOrder purchaseOrder;
    private Product product;
    private int quantity;
    private double totalPrice;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private long createdBy;
    private long modifiedBy;

	/**
	 * 
	 */
	public Cart() {
	}

	/**
	 * @param purchaseOrder
	 * @param product
	 * @param quantity
	 * @param totalPrice
	 * @param createdAt
	 * @param modifiedAt
	 * @param createdBy
	 * @param modifiedBy
	 */
	public Cart(PurchaseOrder purchaseOrder, Product product, int quantity, double totalPrice, java.sql.Timestamp createdAt,
			Timestamp modifiedAt, long createdBy, long modifiedBy) {
		this.purchaseOrder= purchaseOrder;
		this.product = product;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}

	@Id
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="purchaseOrderId")
	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	@Column(name="quantity")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name="total")
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name="createdAt")
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name="modifiedAt")
	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Column(name="createdBy")
	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="modifiedBy")
	public long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@ManyToOne
	@JoinColumn(name="productId")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return "Cart [id=" + id + ", purchaseOrder=" + purchaseOrder + ", product=" + product + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + "]";
	}
}
