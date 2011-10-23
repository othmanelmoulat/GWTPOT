package com.fountainhead.client.view;

import com.fountainhead.client.presenter.SignInPagePresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class SignInPageView extends ViewWithUiHandlers<SignInPageUiHandlers>
		implements
			SignInPagePresenter.MyView {

	private static SignInPageView2UiBinder uiBinder = GWT
			.create(SignInPageView2UiBinder.class);

	interface SignInPageView2UiBinder extends UiBinder<Widget, SignInPageView> {
	}
	private final Widget widget;
	public SignInPageView() {
		widget = SignInPageView.uiBinder.createAndBindUi(this);
	}

	@UiField
	TextBox userNameField;
	@UiField
	PasswordTextBox passwordField;
	@UiField
	Button signInButton;
	@UiField
	Label errorLabel;

	@UiHandler("signInButton")
	void onSignInButtonClicked(ClickEvent event) {
		if (getUiHandlers() != null) {
			getUiHandlers().onOkButtonClicked();
		}
	}

	@Override
	public Widget asWidget() {
		// TODO Auto-generated method stub
		return widget;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return userNameField.getText();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return passwordField.getText();
	}

	@Override
	public void setError(String errorText) {
		errorLabel.setText(errorText);

	}

	@Override
	public void resetAndFocus() {
		userNameField.setFocus(true);
		userNameField.selectAll();

	}

}
