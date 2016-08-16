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

import com.i2i.model.Category;

/**
 * @author Kumar Albert
 *
 * version 1.0
 * <p> The Subcategory class acts as a model which represents Subategory details</p>
 *
 */
@Entity
@Table(name="Subcategory")
public class Subcategory {
	private int id;
	private String name;
	private Category category;
	private int createdBy;
	private Timestamp createdAt;
	private int modifiedBy;
	private Timestamp modifiedAt;
	private Set<Product> products = new HashSet<Product>();
	/**
	 * 
	 */
	public Subcategory() {
	}

	public Subcategory(String name, Category category, int createdBy, Timestamp createdAt, int modifiedBy, Timestamp modifiedAt) {
		this.name = name;
		this.category = category;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.modifiedBy = modifiedBy;
		this.modifiedAt = modifiedAt;
	}

	public Subcategory(String name, Category category, int createdBy, Timestamp createdAt, int modifiedBy, Timestamp modifiedAt,
			Set<Product> products) {
		this.name = name;
		this.category = category;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.modifiedBy = modifiedBy;
		this.modifiedAt = modifiedAt;
		this.products = products;
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
	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
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
	@JoinColumn(name="categoryId", nullable=false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinColumn(name="subcategoryId")
	public Set<Product> getProducts() {
		return products;
	}


	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
