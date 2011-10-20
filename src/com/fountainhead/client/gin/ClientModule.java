package com.fountainhead.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.fountainhead.client.place.ClientPlaceManager;
import com.fountainhead.client.core.MainPagePresenter;
import com.fountainhead.client.core.MainPageView;
import com.fountainhead.client.place.DefaultPlace;
import com.fountainhead.client.place.NameTokens;
import com.fountainhead.client.core.ResponsePresenter;
import com.fountainhead.client.core.ResponseView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class,
				MainPageView.class, MainPagePresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.main);

		bindPresenter(ResponsePresenter.class, ResponsePresenter.MyView.class,
				ResponseView.class, ResponsePresenter.MyProxy.class);
	}
}
