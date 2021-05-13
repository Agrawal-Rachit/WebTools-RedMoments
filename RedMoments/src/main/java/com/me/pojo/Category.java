package com.me.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorytable")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryID", unique = true, nullable = false)
	private long categoryId;

	@Column(name = "categoryTitle", unique = true, nullable = false)
	private String title;

	@ManyToMany
	@JoinTable(
		name = "categoryitemstable",
		joinColumns = {
			@JoinColumn(name = "categoryID", nullable = false, updatable = false, referencedColumnName = "categoryID")
		},
		inverseJoinColumns = {
			@JoinColumn(name = "itemID", referencedColumnName = "itemID")
		}
	)
	private Set<Item> items = new HashSet<Item> ();

	public Category(String title) {
		this.title = title;
	}

	public Category() {}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return title;
	}
}