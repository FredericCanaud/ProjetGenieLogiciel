package northwind.Controllers;

import northwind.Models.Model;

public class UpdateCustomerController extends Controller
{
	@Override
	public ActionResult run(Object ... args)
	{
		Integer customerId = (Integer)args[0];
		String customerLastName = (String)args[1];
		String customerFirstName = (String)args[2];
		String customerEmailAddress = (String)args[3];
		
		Model.setCustomer(customerId, customerLastName, customerFirstName, customerEmailAddress);

		return View(customerId);
	}
}
