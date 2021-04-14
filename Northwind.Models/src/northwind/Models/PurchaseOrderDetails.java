package northwind.Models;
// Generated 14 avr. 2021 à 14:22:08 by Hibernate Tools 5.2.12.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * PurchaseOrderDetails generated by hbm2java
 */
@Entity
@Table(name = "purchase_order_details", schema = "NORTHWIND", catalog = "NORTHWIND")
public class PurchaseOrderDetails implements java.io.Serializable {

	private Integer id;
	private InventoryTransactions inventoryTransactions;
	private Products products;
	private PurchaseOrders purchaseOrders;
	private BigDecimal quantity;
	private BigDecimal unitCost;
	private Date dateReceived;
	private int postedToInventory;

	public PurchaseOrderDetails() {
	}

	public PurchaseOrderDetails(PurchaseOrders purchaseOrders, BigDecimal quantity, BigDecimal unitCost,
			int postedToInventory) {
		this.purchaseOrders = purchaseOrders;
		this.quantity = quantity;
		this.unitCost = unitCost;
		this.postedToInventory = postedToInventory;
	}

	public PurchaseOrderDetails(InventoryTransactions inventoryTransactions, Products products,
			PurchaseOrders purchaseOrders, BigDecimal quantity, BigDecimal unitCost, Date dateReceived,
			int postedToInventory) {
		this.inventoryTransactions = inventoryTransactions;
		this.products = products;
		this.purchaseOrders = purchaseOrders;
		this.quantity = quantity;
		this.unitCost = unitCost;
		this.dateReceived = dateReceived;
		this.postedToInventory = postedToInventory;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inventory_id")
	public InventoryTransactions getInventoryTransactions() {
		return this.inventoryTransactions;
	}

	public void setInventoryTransactions(InventoryTransactions inventoryTransactions) {
		this.inventoryTransactions = inventoryTransactions;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	public Products getProducts() {
		return this.products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "purchase_order_id", nullable = false)
	public PurchaseOrders getPurchaseOrders() {
		return this.purchaseOrders;
	}

	public void setPurchaseOrders(PurchaseOrders purchaseOrders) {
		this.purchaseOrders = purchaseOrders;
	}

	@Column(name = "quantity", nullable = false, precision = 18, scale = 4)
	public BigDecimal getQuantity() {
		return this.quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Column(name = "unit_cost", nullable = false, scale = 4)
	public BigDecimal getUnitCost() {
		return this.unitCost;
	}

	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_received", length = 26)
	public Date getDateReceived() {
		return this.dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	@Column(name = "posted_to_inventory", nullable = false)
	public int getPostedToInventory() {
		return this.postedToInventory;
	}

	public void setPostedToInventory(int postedToInventory) {
		this.postedToInventory = postedToInventory;
	}

}
