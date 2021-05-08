package northwind.Models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Model
{	
	public List<Customers> m_Customers;
	public List<Orders> m_Orders;
	public List<OrderDetails> m_OrdersDetails;

	public static Model s_Model = null;
	
	public static Model getCustomers()
	{
		if (s_Model == null)
		{
			Model m =  new Model();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Northwind.Models");
			EntityManager em = emf.createEntityManager();         
			@SuppressWarnings("unchecked")
			TypedQuery<Customers> query = (TypedQuery<Customers>) em.createNamedQuery("Customers.findAll");
			m.m_Customers = query.getResultList(); 
			s_Model = m;
		}
		
		return s_Model;
	}

	public static void setCustomer(Integer customerId, String customerLastName, String customerFirstName,
			String customerEmailAddress) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Northwind.Models");
		EntityManager em = emf.createEntityManager();         
		em.getTransaction().begin();
		Customers customer = em.find(Customers.class, customerId);
		customer.setLastName(customerLastName);
		customer.setFirstName(customerFirstName);
		customer.setEmailAddress(customerEmailAddress);
		em.merge(customer);
		em.flush();
		em.getTransaction().commit();		
	}
	
	public static Model getOrders(Integer customerId) {
			Model m = new Model();
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("Northwind.Models");
			EntityManager em = emf.createEntityManager();
			@SuppressWarnings("unchecked")
			TypedQuery<Orders> query = (TypedQuery<Orders>) em.createNamedQuery("Orders.findByCustomer");
			query.setParameter("id", customerId);
			m.m_Orders = query.getResultList(); 
			s_Model = m;

			return s_Model;			
	}

	@SuppressWarnings("unchecked")
	public static Model getOrderDetails(Integer orderId, Integer display) {
		Model m =  new Model();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Northwind.Models");
		EntityManager em = emf.createEntityManager();
		TypedQuery<OrderDetails> query;
		switch(display) {
			case 2:
				query = (TypedQuery<OrderDetails>) em.createNamedQuery("OrderDetails.findByOrder_OrderByQuantity");
				break;
			case 3:
				query = (TypedQuery<OrderDetails>) em.createNamedQuery("OrderDetails.findByOrder_OrderByUnitPrice");
				break;
			case 4:
				query = (TypedQuery<OrderDetails>) em.createNamedQuery("OrderDetails.findByOrder_OrderByDiscount");
				break;
			default:
				query = (TypedQuery<OrderDetails>) em.createNamedQuery("OrderDetails.findByOrder");
				break;
		}
		
		query.setParameter("id", orderId);
		m.m_OrdersDetails = query.getResultList(); 
		s_Model = m;		
		return s_Model;
	}

}