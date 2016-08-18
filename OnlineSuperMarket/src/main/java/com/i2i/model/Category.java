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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Kumar Albert
 *
 * version 1.0
 * <p> The Category class acts as a model which represents Category details</p>
 * 
 */
@Entity
@Table(name="Category")
public class Category {
	
	private int id;
	private String name;
	private int createdBy;
	private Timestamp createdAt;
	private int modifiedBy;
	private Timestamp modifiedAt;
	private Set<Subcategory> subcategories = new HashSet<Subcategory>();

	public Category() {
	}

	public Category(String name, int createdBy, Timestamp createdAt, int modifiedBy, Timestamp modifiedAt) {
		this.name = name;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.modifiedBy = modifiedBy;
		this.modifiedAt= modifiedAt;

	}

	public Category(String name, int createdBy, Timestamp createdAt, int modifiedBy, Timestamp modifiedAt,
			Set<Subcategory> subcategories) {
		this.name = name;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.modifiedBy = modifiedBy;
		this.modifiedAt = modifiedAt;
		this.subcategories = subcategories;
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
    
	@OneToMany(cascade={CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinColumn(name="categoryId")
	public Set<Subcategory> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(Set<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", createdBy=" + createdBy + ", createdAt=" + createdAt
				+ ", modifiedBy=" + modifiedBy + ", modifiedAt=" + modifiedAt + ", subcategories=" + subcategories
				+ "]";
	}
	

}
