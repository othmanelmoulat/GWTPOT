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

import com.fountainhead.client.core.LoggedInGatekeeper;
import com.fountainhead.client.gin.ClientGinjector;
import com.fountainhead.client.place.NameTokens;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.RequestTabsHandler;
import com.gwtplatform.mvp.client.TabContainerPresenter;
import com.gwtplatform.mvp.client.TabView;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.RequestTabs;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.TabContentProxy;

/**
 * A sample {@link TabContainerPresenter} appearing as a tab within
 * {@link MainPagePresenter} and itself containing two tabs. When the tab for
 * {@link HomePresenter} is clicked, them {@link HomeNewsPresenter} is
 * displayed.
 * <p />
 * It demonstrates the option 2 described in {@link TabInfo}, together with the
 * use of the {@code nameToken} parameter of {@code @TabInfo} to specify which
 * place to show when the tab is clicked.
 * 


 */
public class HomePresenter
extends
HomePresenterBase<HomePresenter.MyView, HomePresenter.MyProxy> {
	/**
	 * {@link HomePresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@UseGatekeeper(LoggedInGatekeeper.class)
	public interface MyProxy extends TabContentProxy<HomePresenter> {
	}

	@TabInfo(container = MainPagePresenter.class, priority = 0, // The first tab
			// in the main
			// page
			nameToken = NameTokens.homeNewsPage)
	// Go to HomeNewsPresenter when clicked
	static String getTabLabel(ClientGinjector ginjector) {
		return ginjector.getMyConstants().home();
	}

	/**
	 * {@link HomePresenter}'s view.
	 */
	public interface MyView extends TabView {
	}

	/**
	 * This will be the event sent to our "unknown" child presenters, in order
	 * for them to register their tabs.
	 */
	@RequestTabs
	public static final Type<RequestTabsHandler> TYPE_RequestTabs = new Type<RequestTabsHandler>();

	@Inject
	public HomePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy, TYPE_SetTabContent, TYPE_RequestTabs);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetTabContent,
				this);
	}
}