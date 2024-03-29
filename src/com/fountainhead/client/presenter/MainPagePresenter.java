/**

 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.fountainhead.client.presenter;

import com.fountainhead.client.core.CurrentUserChangedEvent;
import com.fountainhead.client.core.CurrentUserChangedEvent.CurrentUserChangedHandler;
import com.fountainhead.client.place.NameTokens;
import com.fountainhead.shared.CurrentUser;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.RequestTabsHandler;
import com.gwtplatform.mvp.client.TabContainerPresenter;
import com.gwtplatform.mvp.client.TabView;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.RequestTabs;
import com.gwtplatform.mvp.client.proxy.AsyncCallFailEvent;
import com.gwtplatform.mvp.client.proxy.AsyncCallFailHandler;
import com.gwtplatform.mvp.client.proxy.AsyncCallStartEvent;
import com.gwtplatform.mvp.client.proxy.AsyncCallStartHandler;
import com.gwtplatform.mvp.client.proxy.AsyncCallSucceedEvent;
import com.gwtplatform.mvp.client.proxy.AsyncCallSucceedHandler;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

/**
 * The main {@link Presenter} of the application. It contains a number of tabs
 * allowing access to the various parts of the application. Tabs are refreshed
 * whenever the current user's privileges change in order to hide areas that
 * cannot be accessed.
 * 


 */
public class MainPagePresenter
extends
TabContainerPresenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy>
implements
CurrentUserChangedHandler,
AsyncCallStartHandler,
AsyncCallFailHandler,
AsyncCallSucceedHandler {

	/**
	 * {@link MainPagePresenter}'s proxy.
	 */
	@ProxyStandard
	@NameToken(NameTokens.mainPage)
	public interface MyProxy extends ProxyPlace<MainPagePresenter> {
	}

	/**
	 * {@link MainPagePresenter}'s view.
	 */
	public interface MyView extends TabView {
		void refreshTabs();
		void setTopMessage(String string);
		void setUsername(String username);
	}

	/**
	 * This will be the event sent to our "unknown" child presenters, in order
	 * for them to register their tabs.
	 */
	@RequestTabs
	public static final Type<RequestTabsHandler> TYPE_RequestTabs = new Type<RequestTabsHandler>();

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetTabContent = new Type<RevealContentHandler<?>>();
	private final CurrentUser currentUser;
	@Inject
	public MainPagePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, CurrentUser currentUser) {
		super(eventBus, view, proxy, TYPE_SetTabContent, TYPE_RequestTabs);
		this.currentUser = currentUser;
	}

	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gwtplatform.mvp.client.PresenterWidget#onReveal()
	 */
	@Override
	protected void onReveal() {
		// TODO Auto-generated method stub
		super.onReveal();
		getView().setUsername(currentUser.getLogin());
	}

	@ProxyEvent
	@Override
	public void onCurrentUserChanged(CurrentUserChangedEvent event) {
		getView().refreshTabs();
	}

	@ProxyEvent
	@Override
	public void onAsyncCallStart(AsyncCallStartEvent event) {
		getView().setTopMessage("Loading...");
	}

	@ProxyEvent
	@Override
	public void onAsyncCallFail(AsyncCallFailEvent event) {
		getView().setTopMessage("Oops, something went wrong...");
	}

	@ProxyEvent
	@Override
	public void onAsyncCallSucceed(AsyncCallSucceedEvent event) {
		getView().setTopMessage(null);
	}
}
