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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cart_table")

public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "cartID", unique = true, nullable = false)
	private long id;

	@OneToOne
	private User user;

	@ManyToMany
	@JoinTable(
		name = "cart_item_table",
		joinColumns = {
			@JoinColumn(name = "cartID", nullable = false, updatable = false, referencedColumnName = "cartID")
		},
		inverseJoinColumns = {
			@JoinColumn(name = "itemID", referencedColumnName = "itemID")
		}
	)
	private Set<Item> items = new HashSet<Item> ();

	@Column(name = "title")
	private String title;


	@Column(name = "totalprice")
	private Float totalprice;

	@Column(name = "quantity")
	private int quantity;

	public Cart() {

	}
        public Cart(String title, User user, Float totalprice, int quantity, Float finalPrice) {
		this.title = title;
		this.totalprice = totalprice;
		this.user = user;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Float totalprice) {
		this.totalprice = totalprice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}