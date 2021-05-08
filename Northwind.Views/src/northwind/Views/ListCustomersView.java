package northwind.Views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import northwind.Controllers.EditCustomerController;
import northwind.Controllers.ListCustomersController;
import northwind.Controllers.ShowOrderDetailsController;
import northwind.ViewModels.CustomersViewModel;
import northwind.ViewModels.OrdersViewModel;

public class ListCustomersView extends Dialog{
	protected Object result;
	protected Shell shlListCustomers;
	protected Table table;
	private Combo combo;
	private Label lblError;
	
	public RunController m_Infrastructure;
	
	protected CustomersViewModel[] getCustomersViewModel()
	{
		if (m_Infrastructure != null)
			return (CustomersViewModel[])m_Infrastructure.m_ViewModel;
		else
			return new CustomersViewModel[0];
	}
	
	protected OrdersViewModel[] getOrdersViewModel()
	{
		if (m_Infrastructure != null)
			return (OrdersViewModel[])m_Infrastructure.m_ViewModel2;
		else
			return new OrdersViewModel[0];
	}
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ListCustomersView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlListCustomers.open();
		shlListCustomers.layout();
		Display display = getParent().getDisplay();
		while (!shlListCustomers.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	protected void createContents() {
		shlListCustomers = new Shell(getParent(), SWT.TITLE);
		shlListCustomers.setSize(800, 500);
		shlListCustomers.setText("Liste des clients");
		
		lblError = new Label(shlListCustomers, SWT.NONE);
		lblError.setBounds(250, 120, 300, 50);
		lblError.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));

		Label lblClients = new Label(shlListCustomers, SWT.NONE);
		lblClients.setBounds(50, 50, 60, 50);
		lblClients.setText("Clients :");
		
		table = new Table(shlListCustomers, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(50, 150, 700, 250);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Button btnEditer = new Button(shlListCustomers, SWT.NONE);
		btnEditer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int customerId = combo.getSelectionIndex();
				m_Infrastructure.runController(shlListCustomers, EditCustomerController.class, customerId);
			}
		});
		
		btnEditer.setBounds(650, 75, 100, 30);
		btnEditer.setText("Editer");
		
		TableColumn tOrdersId = new TableColumn(table, SWT.NONE);
		tOrdersId.setWidth(100);
		tOrdersId.setText("Id");
		
		TableColumn tOrdersDate = new TableColumn(table, SWT.NONE);
		tOrdersDate.setWidth(100);
		tOrdersDate.setText("Date de commande");
		
		CustomersViewModel[] customers = getCustomersViewModel();
		
	    combo = new Combo(shlListCustomers, SWT.READ_ONLY);
	    combo.setBounds(50, 75, 500, 30);
	    String[] customersNames = new String[customers.length];
	    int selected = 1;
	    
	    int i = 0;
		for (CustomersViewModel customer : customers)
		{
		    customersNames[i] = customer.getM_LastName() + " " + customer.getM_FirstName();
	        i++;
	        if(customer.getM_Orders() != null) {
	        	selected = customer.getM_Id() - 1;
	        	for(OrdersViewModel order : customer.getM_Orders()) {
	        		 TableItem item = new TableItem(table, SWT.NONE);
	     		     item.setText(new String[] { ""+order.getM_Id(), order.getM_OrderDate().toString()});
	        	}
	        }
		}
	    combo.setItems(customersNames);
	    combo.select(selected);

	    combo.addSelectionListener(new SelectionAdapter() {
	        public void widgetSelected(SelectionEvent e) {
	        	int customerId = combo.getSelectionIndex();
				m_Infrastructure.runController(shlListCustomers, ListCustomersController.class, customerId);				
	        }
	    });
	     
		Button btnOrderDetails = new Button(shlListCustomers, SWT.NONE);
		btnOrderDetails.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table.getSelection().length != 0) {
					int orderId = Integer.parseInt(table.getSelection()[0].getText());
					m_Infrastructure.runController(shlListCustomers, ShowOrderDetailsController.class, orderId, 1);
				} else {
					lblError.setText("Vous n'avez pas sélectionné de commande !");
				}
			}
		});
		btnOrderDetails.setBounds(550, 420, 200, 30);
		btnOrderDetails.setText("Voir détails commande");
	}
}
