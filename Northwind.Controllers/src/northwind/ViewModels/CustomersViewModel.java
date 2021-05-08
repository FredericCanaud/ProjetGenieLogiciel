package northwind.ViewModels;

public class CustomersViewModel {
	
	private int m_Id;
	private String m_FirstName;
	private String m_LastName;
	private String m_EmailAddress;
	private OrdersViewModel[] m_Orders;
	
	public CustomersViewModel(Integer id, String lastName, String firstName, String emailAddress, OrdersViewModel[] orders) {
		this.m_Id = id;
		this.m_LastName = lastName;
		this.m_FirstName = firstName;
		this.m_EmailAddress = emailAddress;
		this.m_Orders = orders;
	}

	public int getM_Id() {
		return m_Id;
	}

	public String getM_FirstName() {
		return m_FirstName;
	}

	public String getM_LastName() {
		return m_LastName;
	}

	public String getM_EmailAddress() {
		return m_EmailAddress;
	}
	
	public OrdersViewModel[] getM_Orders() {
		return m_Orders;
	}
}
