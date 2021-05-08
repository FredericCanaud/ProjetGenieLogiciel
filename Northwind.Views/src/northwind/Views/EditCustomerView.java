package northwind.Views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import northwind.Controllers.ListCustomersController;
import northwind.Controllers.UpdateCustomerController;
import northwind.ViewModels.CustomersViewModel;

public class EditCustomerView extends Dialog {

	protected Object result;
	protected Shell shlEditCustomer;
	
	private Text txtLastName;
	private Text txtFirstName;
	private Text txtEmailAddress;

	public RunController m_Infrastructure;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public EditCustomerView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}
	
	protected CustomersViewModel getViewModel()
	{
		return (CustomersViewModel)m_Infrastructure.m_ViewModel;
	}
	
	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlEditCustomer.open();
		shlEditCustomer.layout();
		Display display = getParent().getDisplay();
		while (!shlEditCustomer.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}
	
	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		
		shlEditCustomer = new Shell(getParent(), SWT.TITLE | SWT.CLOSE);
		shlEditCustomer.setSize(800, 500);
		shlEditCustomer.setText("Edition d'un client");
		
		Button btnUpdate = new Button(shlEditCustomer, SWT.NONE);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String newLastName = txtLastName.getText();
				String newFirstName = txtFirstName.getText();
				String newEmailAdress = txtEmailAddress.getText();
				
				m_Infrastructure.runController(shlEditCustomer, UpdateCustomerController.class, getViewModel().getM_Id(), newLastName, newFirstName, newEmailAdress);
			}
		});
		btnUpdate.setBounds(50, 140, 200, 30);
		btnUpdate.setText("Mettre à jour");
		
		txtLastName = new Text(shlEditCustomer, SWT.BORDER);
		txtLastName.setBounds(154, 50, 300, 21);
		txtLastName.setText(getViewModel().getM_LastName());
		
		txtFirstName = new Text(shlEditCustomer, SWT.BORDER);
		txtFirstName.setBounds(154, 80, 300, 21);
		txtFirstName.setText(getViewModel().getM_FirstName());
		
		txtEmailAddress = new Text(shlEditCustomer, SWT.BORDER);
		txtEmailAddress.setBounds(154, 110, 300, 21);
		if(getViewModel().getM_EmailAddress() != null) {
			txtEmailAddress.setText(getViewModel().getM_EmailAddress());
		}
	
		Label lblCustomerFirstName = new Label(shlEditCustomer, SWT.NONE);
		lblCustomerFirstName.setBounds(50, 50, 100, 30);
		lblCustomerFirstName.setText("Nom :");
	
		Label lblCustomerLastName = new Label(shlEditCustomer, SWT.NONE);
		lblCustomerLastName.setBounds(50, 80, 100, 30);
		lblCustomerLastName.setText("Prénom :");
		
		Label lblCustomerEmailAddress = new Label(shlEditCustomer, SWT.NONE);
		lblCustomerEmailAddress.setBounds(50, 110, 100, 30);
		lblCustomerEmailAddress.setText("Email :");
		
		Button btnReturn = new Button(shlEditCustomer, SWT.NONE);
		btnReturn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {	
				m_Infrastructure.runController(shlEditCustomer, ListCustomersController.class, getViewModel().getM_Id() - 1);
			}
		});
		btnReturn.setBounds(550, 420, 200, 30);
		btnReturn.setText("Liste des clients");
	}
}
