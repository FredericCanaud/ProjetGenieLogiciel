package northwind.Controllers;

import java.util.List;

import northwind.Models.Model;
import northwind.Models.OrderDetails;
import northwind.ViewModels.OrderDetailsViewModel;

public class ShowOrderDetailsController extends Controller{
	
	@Override
	public ActionResult run(Object ... args)
	{
		Integer orderId = (Integer)args[0];
		Integer display = (Integer)args[1];
		List<OrderDetails> ordersDetails = Model.getOrderDetails(orderId, display).m_OrdersDetails;
		Model.s_Model = null;
		OrderDetailsViewModel[] orderDetailsViewModel = new OrderDetailsViewModel[ordersDetails.size()];

		for (int i=0; i < ordersDetails.size(); i++)
		{
			OrderDetails orderDetails = ordersDetails.get(i);
			orderDetailsViewModel[i] = new OrderDetailsViewModel(orderDetails.getOrders().getId(), orderDetails.getOrders().getCustomers().getId(), orderDetails.getProducts().getProductName(), orderDetails.getQuantity(), orderDetails.getUnitPrice(), orderDetails.getDiscount());
		}
		return View(orderDetailsViewModel);
	}
}
