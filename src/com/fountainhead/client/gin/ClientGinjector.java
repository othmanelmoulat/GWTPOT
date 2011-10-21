package com.fountainhead.client.gin;

import com.fountainhead.client.core.LoginPresenter;
import com.fountainhead.client.core.MainPagePresenter;
import com.fountainhead.client.core.ResponsePresenter;
import com.fountainhead.client.core.UserPresenter;
import com.fountainhead.client.gatekeeper.UserGatekeeper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

@GinModules({DispatchAsyncModule.class, ClientModule.class})
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	Provider<MainPagePresenter> getMainPagePresenter();

	Provider<ResponsePresenter> getResponsePresenter();
	
	Provider<LoginPresenter> getLoginPresenter();	

	Provider<UserPresenter> getUserPresenter();	

	UserGatekeeper getUserGatekeeper();
}
