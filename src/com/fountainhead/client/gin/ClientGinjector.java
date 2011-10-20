package com.fountainhead.client.gin;

import com.google.gwt.inject.client.GinModules;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.fountainhead.client.gin.ClientModule;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.google.inject.Provider;
import com.fountainhead.client.core.MainPagePresenter;
import com.fountainhead.client.core.ResponsePresenter;

@GinModules({DispatchAsyncModule.class, ClientModule.class})
public interface ClientGinjector extends Ginjector {

	EventBus getEventBus();

	PlaceManager getPlaceManager();

	Provider<MainPagePresenter> getMainPagePresenter();

	Provider<ResponsePresenter> getResponsePresenter();
}
