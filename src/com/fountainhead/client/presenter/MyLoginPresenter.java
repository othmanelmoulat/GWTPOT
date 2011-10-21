package com.fountainhead.client.presenter;

import com.fountainhead.client.CurrentUser;
import com.fountainhead.client.NameTokens;
import com.fountainhead.shared.FieldVerifier;
import com.fountainhead.shared.LoginAction;
import com.fountainhead.shared.LoginResult;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class MyLoginPresenter
extends
Presenter<MyLoginPresenter.MyView, MyLoginPresenter.MyProxy> {
	private final DispatchAsync dispatcher;
	private final CurrentUser user;
	private final PlaceManager placeManager;

	public interface MyView extends View {
		public Button getSendButton();

		public void setError(String errorText);

		public String getName();

		public String getPassword();

		public void resetAndFocus();
	}

	@ProxyStandard
	@NameToken(NameTokens.myloginpage)
	public interface MyProxy extends ProxyPlace<MyLoginPresenter> {
	}

	@Inject
	public MyLoginPresenter(EventBus eventBus, MyView view, MyProxy proxy,
			PlaceManager placeManager, DispatchAsync dispatcher,
			CurrentUser user) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		this.dispatcher = dispatcher;
		this.user = user;
	}

	@Override
	protected void revealInParent() {
		// RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetTabContent,
		// this);
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		registerHandler(getView().getSendButton().addClickHandler(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						login();
					}
				}));
	}

	@Override
	protected void onReset() {
		super.onReset();
		getView().resetAndFocus();
	}

	@Override
	protected void onReveal() {
		super.onReveal();
	}

	@Override
	protected void onUnbind() {
		super.onUnbind();
	}
	/**
	 * Send the name from the nameField to the server and wait for a response.
	 */
	private void login() {
		// First, we validate the input.
		getView().setError("");
		String loginText = getView().getName();
		String passwordText = getView().getPassword();

		if (!FieldVerifier.isValidName(loginText)) {
			getView().setError("Please enter at least four characters");
			return;
		}

		dispatcher.execute(
				new LoginAction(loginText, passwordText, this.getEventBus()),
				new AsyncCallback<LoginResult>() {
					@Override
					public void onFailure(Throwable caught) {
						getView().setError(
								"An error occured: " + caught.getMessage());
					}

					@Override
					public void onSuccess(LoginResult result) {

						// user.setRoles(result.getResponse().getRoles());
						// user.setUsername(result.getResponse().getUsername());

						redirect();
					}
				});
	}

	private void redirect() {
		placeManager.revealPlace(new PlaceRequest(NameTokens.homeNewsPage));
	}
}
