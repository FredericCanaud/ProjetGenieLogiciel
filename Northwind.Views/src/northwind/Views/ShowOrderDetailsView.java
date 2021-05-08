package northwind.Views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import northwind.Controllers.ListCustomersController;
import northwind.Controllers.ShowOrderDetailsController;
import northwind.ViewModels.OrderDetailsViewModel;

public class ShowOrderDetailsView extends Dialog {
	protected Object result;
	protected Shell shlListProducts;
	protected Table table;
	
	public RunController m_Infrastructure;
	
	protected OrderDetailsViewModel[] getViewModel()
	{
		if (m_Infrastructure != null)
			return (OrderDetailsViewModel[])m_Infrastructure.m_ViewModel;
		else
			return new OrderDetailsViewModel[0];
	}
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ShowOrderDetailsView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlListProducts.open();
		shlListProducts.layout();
		Display display = getParent().getDisplay();
		while (!shlListProducts.isDisposed()) {
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
		int orderId = getViewModel()[0].getM_OrderId();
		shlListProducts = new Shell(getParent(), SWT.TITLE);
		shlListProducts.setSize(800, 500);
		shlListProducts.setText("Détail de la commande " + orderId);
		
		table = new Table(shlListProducts, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(50, 50, 700, 350);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tOrdersProduit = new TableColumn(table, SWT.NONE);
		tOrdersProduit.setWidth(100);
		tOrdersProduit.setText("Produit");
		tOrdersProduit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shlListProducts, ShowOrderDetailsController.class, orderId, 1);
			}
		});
		
		TableColumn tOrdersQuantite = new TableColumn(table, SWT.NONE);
		tOrdersQuantite.setWidth(100);
		tOrdersQuantite.setText("Quantité");
		tOrdersQuantite.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shlListProducts, ShowOrderDetailsController.class, orderId, 2);
			}
		});
		
		TableColumn tOrdersPrixUnitaire = new TableColumn(table, SWT.NONE);
		tOrdersPrixUnitaire.setWidth(100);
		tOrdersPrixUnitaire.setText("Prix unitaire");
		tOrdersPrixUnitaire.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shlListProducts, ShowOrderDetailsController.class, orderId, 3);
			}
		});
		
		TableColumn tOrdersDiscount = new TableColumn(table, SWT.NONE);
		tOrdersDiscount.setWidth(100);
		tOrdersDiscount.setText("Discount");
		tOrdersDiscount.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shlListProducts, ShowOrderDetailsController.class, orderId, 4);
			}
		});
		
		OrderDetailsViewModel[] ordersDetails = getViewModel();
		
		for (OrderDetailsViewModel orderDetails : ordersDetails)
		{
	        	TableItem item = new TableItem(table, SWT.NONE);
	     		item.setText(new String[] { ""+orderDetails.getM_ProductName(), orderDetails.getM_Quantity().toString(), orderDetails.getM_UnitPrice().toString(), String.valueOf(orderDetails.getM_Discount())});
		}

		Button btnRevenir = new Button(shlListProducts, SWT.NONE);
		btnRevenir.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shlListProducts, ListCustomersController.class, getViewModel().length != 0 ? getViewModel()[0].getM_CustomerID() - 1 : 1);
			}
		});
		
		btnRevenir.setBounds(650, 420, 100, 30);
		btnRevenir.setText("Revenir");
	}
}
