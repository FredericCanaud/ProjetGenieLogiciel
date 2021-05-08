package northwind.Views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import northwind.Controllers.ListCustomersController;

public class UpdateCustomerView extends Dialog {

	protected Object result;
	protected Shell shlEditionRussie;
	
	public RunController m_Infrastructure;
	
	protected Integer getViewModel()
	{
		if (m_Infrastructure != null)
			return (Integer)m_Infrastructure.m_ViewModel;
		else
			return -1;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public UpdateCustomerView(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlEditionRussie.open();
		shlEditionRussie.layout();
		Display display = getParent().getDisplay();
		while (!shlEditionRussie.isDisposed()) {
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
		shlEditionRussie = new Shell(getParent(), getStyle());
		shlEditionRussie.setSize(800, 500);
		shlEditionRussie.setText("Edition réussie");
		
		Label lblXx = new Label(shlEditionRussie, SWT.NONE);
		lblXx.setBounds(300, 68, 400, 15);
		lblXx.setText("Le client " + getViewModel() + " a bien été modifié");
		
		Button btnRevenirLa = new Button(shlEditionRussie, SWT.NONE);
		btnRevenirLa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				m_Infrastructure.runController(shlEditionRussie, ListCustomersController.class, getViewModel());
			}
		});
		btnRevenirLa.setBounds(153, 105, 250, 25);
		btnRevenirLa.setText("Revenir à la liste des clients");

	}

}

