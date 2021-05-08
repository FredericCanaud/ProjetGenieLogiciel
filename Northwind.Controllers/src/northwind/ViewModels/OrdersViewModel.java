package northwind.ViewModels;

import java.util.Date;

public class OrdersViewModel {
	private int m_Id;
	private Date m_OrderDate;

	public OrdersViewModel(Integer id, Date orderDate) {
		this.m_Id = id;
		this.m_OrderDate = orderDate;
	}

	public int getM_Id() {
		return m_Id;
	}

	public Date getM_OrderDate() {
		return m_OrderDate;
	}
}
