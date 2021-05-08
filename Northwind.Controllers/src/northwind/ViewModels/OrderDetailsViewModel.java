package northwind.ViewModels;

import java.math.BigDecimal;

public class OrderDetailsViewModel {
	private int m_OrderId;
	private int m_CustomerId;
	private String m_ProductName;
	private BigDecimal m_Quantity;
	private BigDecimal m_UnitPrice;
	private double m_Discount;
	
	public OrderDetailsViewModel(int m_OrderId, int m_CustomerId, String m_ProductName, BigDecimal m_Quantity, BigDecimal m_UnitPrice,
			double m_Discount) {
		this.m_OrderId = m_OrderId;
		this.m_CustomerId = m_CustomerId;
		this.m_ProductName = m_ProductName;
		this.m_Quantity = m_Quantity;
		this.m_UnitPrice = m_UnitPrice;
		this.m_Discount = m_Discount;
	}
	
	public int getM_OrderId() {
		return m_OrderId;
	}
	public int getM_CustomerID() {
		return m_CustomerId;
	}
	public String getM_ProductName() {
		return m_ProductName;
	}
	public BigDecimal getM_Quantity() {
		return m_Quantity;
	}
	public BigDecimal getM_UnitPrice() {
		return m_UnitPrice;
	}
	public double getM_Discount() {
		return m_Discount;
	}
}
