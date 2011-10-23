package com.fountainhead.client.gin;


import com.fountainhead.client.core.AdminGatekeeper;
import com.fountainhead.client.core.IsAdminGatekeeper;
import com.fountainhead.client.core.LoggedInGatekeeper;
import com.fountainhead.client.core.MyConstants;
import com.fountainhead.client.presenter.AdminAreaPresenter;
import com.fountainhead.client.presenter.DialogSamplePresenter;
import com.fountainhead.client.presenter.ErrorPagePresenter;
import com.fountainhead.client.presenter.HomeInfoPresenter;
import com.fountainhead.client.presenter.HomeNewsPresenter;
import com.fountainhead.client.presenter.HomePresenter;
import com.fountainhead.client.presenter.MainPagePresenter;
import com.fountainhead.client.presenter.ReportPresenter;
import com.fountainhead.client.presenter.SignInPagePresenter;
import com.fountainhead.client.ui.LinkMenu;
import com.fountainhead.client.ui.RoundTabPanel;
import com.fountainhead.client.ui.SimpleTabPanel;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
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

	AsyncProvider<HomePresenter> getHomePresenter();

	IsAdminGatekeeper getIsAdminGatekeeper();
	AsyncProvider<DialogSamplePresenter> getDialogSamplePresenter();
	AsyncProvider<AdminAreaPresenter> getAdminAreaPresenter();
	AsyncProvider<ReportPresenter> getSettingsPresenter();
	AsyncProvider<HomeInfoPresenter> getHomeInfoPresenter();
	AsyncProvider<HomeNewsPresenter> getHomeNewsPresenter();
	LoggedInGatekeeper getLoggedInGatekeeper();
	AdminGatekeeper getAdminGatekeeper();

	// Sign In
	Provider<SignInPagePresenter> getSignInPagePresenter();

	MyConstants getMyConstants();

	// The following methods allow these widgets to be used in UiBinder files
	// and participate in dependency injection.
	LinkMenu getLinkMenu();
	RoundTabPanel getRoundTabPanel();
	SimpleTabPanel getSimpleTabPanel();

	// Error Page
	AsyncProvider<ErrorPagePresenter> getErrorPagePresenter();
}
