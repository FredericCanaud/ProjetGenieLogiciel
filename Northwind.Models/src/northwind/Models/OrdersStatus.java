package northwind.Models;
// Generated 14 avr. 2021 à 14:22:08 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * OrdersStatus generated by hbm2java
 */
@Entity
@Table(name = "orders_status", schema = "NORTHWIND", catalog = "NORTHWIND")
public class OrdersStatus implements java.io.Serializable {

	private int id;
	private String statusName;
	private Set<Orders> orderses = new HashSet<Orders>(0);

	public OrdersStatus() {
	}

	public OrdersStatus(int id, String statusName) {
		this.id = id;
		this.statusName = statusName;
	}

	public OrdersStatus(int id, String statusName, Set<Orders> orderses) {
		this.id = id;
		this.statusName = statusName;
		this.orderses = orderses;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "status_name", nullable = false, length = 50)
	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ordersStatus")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

}
