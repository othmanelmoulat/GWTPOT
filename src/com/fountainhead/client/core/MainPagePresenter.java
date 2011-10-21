

package com.fountainhead.client.core;

import com.fountainhead.client.gatekeeper.UserGatekeeper;
import com.fountainhead.client.place.NameTokens;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.RequestTabsHandler;
import com.gwtplatform.mvp.client.TabContainerPresenter;
import com.gwtplatform.mvp.client.TabView;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.RequestTabs;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

/**
 * This is the main presenter of the application. It's a
 * {@link com.gwtplatform.mvp.client.TabContainerPresenter}.
 * 
 *
 */
public class MainPagePresenter
		extends
		TabContainerPresenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy> {

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetTabContent = new Type<RevealContentHandler<?>>();

	/**
	 * This will be the event sent to our "unknown" child presenters, in order
	 * for them to register their tabs.
	 */
	@RequestTabs
	public static final Type<RequestTabsHandler> TYPE_RequestTabs = new Type<RequestTabsHandler>();

	public interface MyView extends TabView {
	}

	@ProxyStandard
	@NameToken(NameTokens.main)
	@UseGatekeeper(UserGatekeeper.class)
	public interface MyProxy extends ProxyPlace<MainPagePresenter> {
	}

	@Inject
	public MainPagePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy, TYPE_SetTabContent, TYPE_RequestTabs);
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}
}
