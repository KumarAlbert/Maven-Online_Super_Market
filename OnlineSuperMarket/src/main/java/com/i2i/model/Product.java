/**
 * 
 */
package com.i2i.model;

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

import com.i2i.model.Subcategory;
/**
 * @author Kumar Albert
 *
 * version 1.0
 * <p> The Project class acts as a model which represents Project details</p>
 * 
 */
@Entity
@Table(name="Product")
public class Product {
	private int id;
	private String name;
	private String description;
	private Subcategory subcategory;
	private String imageUrl;
	private int stock;
	private double price;
	private int createdBy;
	private Timestamp createdAt;
	private long modifiedBy;
	private Timestamp modifiedAt;
	private Set<Cart> carts = new HashSet<Cart>();

	public Product(String name, String description, Subcategory subcategory, String imageUrl, int stock, double price,
			int createdBy, Timestamp createdAt, long modifiedBy, Timestamp modifiedAt) {
		this.name = name;
		this.description = description;
		this.subcategory = subcategory;
		this.imageUrl = imageUrl;
		this.stock = stock;
		this.price = price;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.modifiedBy = modifiedBy;
		this.modifiedAt = modifiedAt;
	}

	public Product(String name, String description, Subcategory subcategory, String imageUrl, int stock, double price,
			int createdBy, Timestamp createdAt, int modifiedBy, Timestamp modifiedAt, Set<Cart> carts) {
		this.name = name;
		this.description = description;
		this.subcategory = subcategory;
		this.imageUrl = imageUrl;
		this.stock = stock;
		this.price = price;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.modifiedBy = modifiedBy;
		this.modifiedAt = modifiedAt;
		this.carts = carts;
	}

	
	public Product() {
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

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="imageUrl")
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name="stock")
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Column(name="price")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name="createdBy")
	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="createdAt")
	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name="modifiedBy")
	public long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name="modifiedAt")
	public Timestamp getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	@ManyToOne
	@JoinColumn(name="subcategoryId", nullable=false)
	public Subcategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(Subcategory subcategory) {
		this.subcategory = subcategory;
	}

	@OneToMany(cascade={CascadeType.PERSIST},fetch = FetchType.LAZY)
	@JoinColumn(name="productId")
	public Set<Cart> getCarts() {
		return carts;
	}

	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}

}
