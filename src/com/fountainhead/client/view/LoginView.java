package com.fountainhead.client.view;

import com.fountainhead.client.presenter.LoginPresenter;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class LoginView extends ViewImpl implements LoginPresenter.MyView {

	private static String html = "<h1>Login</h1>\n"
			+ "<table align=\"center\">\n"
			+ "  <tr>\n"
			+ "    <td colspan=\"2\" style=\"font-weight:bold;\">Login</td>\n"
			+ "  </tr>\n"
			+ "  <tr>\n"
			+ "    <td id=\"nameFieldContainer\"></td>\n"
			+ "  </tr>\n"
			+ "  <tr>\n"
			+ "    <td id=\"passwordFieldContainer\"></td>\n"
			+ "  </tr>\n"
			+ "  <tr>\n"
			+ "    <td id=\"sendButtonContainer\"></td>\n"
			+ "  </tr>\n"
			+ "  <tr>\n"
			+ "    <td colspan=\"2\" style=\"color:red;\" id=\"errorLabelContainer\"></td>\n"
			+ "  </tr>\n" + "</table>\n";

	HTMLPanel panel = new HTMLPanel(html);

	private final TextBox nameField;
	private final PasswordTextBox passwordField;
	private final Button sendButton;
	private final Label errorLabel;

	@Inject
	public LoginView() {
		sendButton = new Button("Send");
		nameField = new TextBox();
		passwordField = new PasswordTextBox();
		errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		panel.add(nameField, "nameFieldContainer");
		panel.add(passwordField, "passwordFieldContainer");
		panel.add(sendButton, "sendButtonContainer");
		panel.add(errorLabel, "errorLabelContainer");

	}

	@Override
	public void resetAndFocus() {
		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return nameField.getText();
	}

	@Override
	public Button getSendButton() {
		return sendButton;
	}

	@Override
	public void setError(String errorText) {
		errorLabel.setText(errorText);
	}

	@Override
	public String getPassword() {
		return passwordField.getText();
	}

}
