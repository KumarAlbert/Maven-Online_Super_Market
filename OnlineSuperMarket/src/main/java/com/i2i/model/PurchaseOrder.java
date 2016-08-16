/**
 * 
 */
package com.i2i.model;
import com.i2i.model.User;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <p>Order model object</p>
 * <p>Contains various attributes of model and its getters and setters. 
 * @author Mukilan.K
 *
 * @version 1.0
 */
@Entity
@Table(name="PurchaseOrder")
public class PurchaseOrder {

	private int id;
    private User user;
    private double total;
    private String paymentType;
    private boolean status;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private long createdBy;
    private long modifiedBy;
    private Set<Cart> carts = new HashSet<Cart>();
	/**
	 * 
	 */
	public PurchaseOrder() {
	}
	

	/**
	 * @param userId
	 * @param total
	 * @param paymentType
	 * @param status
	 * @param createdAt
	 * @param modifiedAt
	 * @param createdBy
	 * @param modifiedBy
	 */
	public PurchaseOrder( double total, String paymentType, boolean status, Timestamp createdAt, Timestamp modifiedAt,
			long createdBy, long modifiedBy) {
		this.total = total;
		this.paymentType = paymentType;
		this.status = status;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}


	/**
     * @param user
	 * @param total
	 * @param paymentType
	 * @param status
	 * @param createdAt
	 * @param modifiedAt
	 * @param createdBy
	 * @param modifiedBy
	 */
	public PurchaseOrder(User user, double total, String paymentType, boolean status, Timestamp createdAt, Timestamp modifiedAt,
			long createdBy, long modifiedBy) {
		this.user = user;
		this.total = total;
		this.paymentType = paymentType;
		this.status = status;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
	}


	@Id
	@GeneratedValue
	@Column(name="id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="total")
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Column(name="paymentType")
	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Column(name="status")
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinColumn(name="purchaseOrderId")
	public Set<Cart> getCarts() {
		return carts;
	}


	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}
	
    @Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", user=" + user + ", total=" + total + ", paymentType=" + paymentType
				+ ", status=" + status + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", carts=" + carts + "]";
	}


}
