package com.fountainhead.client.view;

import com.fountainhead.client.presenter.MyLoginPresenter;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class MyLoginView extends ViewImpl implements MyLoginPresenter.MyView {

	private final Widget widget;

	public interface Binder extends UiBinder<Widget, MyLoginView> {
	}

	@Inject
	public MyLoginView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	@UiField
	TextBox loginBox;

	@UiField
	TextBox passwordBox;
	@UiField
	Button buttonSubmit;
	@UiField
	Label errorLabel;

	// @UiHandler("buttonSubmit")
	// void doClickSubmit(ClickEvent event) {
	// Window.alert(loginBox.getValue());
	// }

	@Override
	public Button getSendButton() {
		// TODO Auto-generated method stub
		return this.buttonSubmit;
	}

	@Override
	public void setError(String errorText) {
		errorLabel.setText(errorText);

	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.loginBox.getText();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.passwordBox.getText();
	}

	@Override
	public void resetAndFocus() {
		loginBox.setFocus(true);
		loginBox.selectAll();

	}
}
