package northwind.Controllers;

import northwind.Models.Customers;
import northwind.Models.Model;
import northwind.ViewModels.CustomersViewModel;

public class EditCustomerController extends Controller{
	
	@Override
	public ActionResult run(Object ... args)
	{
		Integer customerId = (Integer)args[0];
		
		Customers customer = Model.getCustomers().m_Customers.get(customerId);
		CustomersViewModel customerViewModel = new CustomersViewModel(customer.getId(), customer.getLastName(), customer.getFirstName(), customer.getEmailAddress(), null);
		
		return View(customerViewModel);
	}
}
