package com.fountainhead.client.gin;

import com.fountainhead.client.core.LoginPresenter;
import com.fountainhead.client.core.LoginView;
import com.fountainhead.client.core.MainPagePresenter;
import com.fountainhead.client.core.MainPageView;
import com.fountainhead.client.core.ResponsePresenter;
import com.fountainhead.client.core.ResponseView;
import com.fountainhead.client.core.UserPresenter;
import com.fountainhead.client.core.UserView;
import com.fountainhead.client.place.ClientPlaceManager;
import com.fountainhead.client.place.DefaultPlace;
import com.fountainhead.client.place.NameTokens;
import com.fountainhead.shared.CurrentUser;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class,
				MainPageView.class, MainPagePresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.loginPage);

		bindPresenter(ResponsePresenter.class, ResponsePresenter.MyView.class,
				ResponseView.class, ResponsePresenter.MyProxy.class);
		bind(CurrentUser.class).asEagerSingleton();
		//bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(
			//	Singleton.class);
		//bind(RootPresenter.class).asEagerSingleton();
		//bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class)
				//.in(Singleton.class);

		// Presenters
		bindPresenter(LoginPresenter.class, LoginPresenter.MyView.class,
				LoginView.class, LoginPresenter.MyProxy.class);		

		bindPresenter(UserPresenter.class, UserPresenter.MyView.class,
				UserView.class, UserPresenter.MyProxy.class);
	}
}
