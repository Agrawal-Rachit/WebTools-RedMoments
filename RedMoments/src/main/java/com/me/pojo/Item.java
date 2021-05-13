package com.me.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "item_table")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itemID", unique = true, nullable = false)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "message")
	private String message;


	@Column(name = "price")
	private Float price;

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@ManyToOne
	private User user;

	@ManyToMany(mappedBy = "items")
	private Set<Category> categories = new HashSet<Category> ();

	@ManyToMany(mappedBy = "items")
	private Set<Cart> cart = new HashSet<Cart> ();

	@Transient
	int postedBy;

        public Item(String title, String message, User user, Category catergory, Cart cart){
		this.title = title;
		this.message = message;
		this.user = user;
		this.categories.add(catergory);
		this.cart.add(cart);
	}

	public Item() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public int getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}

	public Set<Cart> getCart() {
		return cart;
	}

	public void setCart(Set<Cart> cart) {
		this.cart = cart;
	}

}