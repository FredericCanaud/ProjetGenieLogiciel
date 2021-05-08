package northwind.Controllers;

import java.util.List;

import northwind.Models.Customers;
import northwind.Models.Model;
import northwind.Models.Orders;
import northwind.ViewModels.CustomersViewModel;
import northwind.ViewModels.OrdersViewModel;

public class ListCustomersController extends Controller
{
	@Override
	public ActionResult run(Object ... args)
	{
		List<Customers> customers = Model.getCustomers().m_Customers;
		Model.s_Model = null;
		CustomersViewModel[] customerViewModel = new CustomersViewModel[customers.size()];
		
		for (int i=0; i < customers.size(); i++)
		{
			Customers customer = customers.get(i);
			
			OrdersViewModel[] ordersViewModel = null;
			if((Integer) args[0] == i) {
				List<Orders> orders = Model.getOrders(customer.getId()).m_Orders;
				Model.s_Model = null;
				ordersViewModel = new OrdersViewModel[orders.size()];
				for(int j=0; j < orders.size(); j++) {
					Orders order = orders.get(j);
					ordersViewModel[j] = new OrdersViewModel(order.getId(), order.getOrderDate());
				}
			}

			customerViewModel[i] = new CustomersViewModel(customer.getId(), customer.getLastName(), 
					customer.getFirstName(), customer.getEmailAddress(), ordersViewModel);
		}
		return View(customerViewModel);
	}
}
