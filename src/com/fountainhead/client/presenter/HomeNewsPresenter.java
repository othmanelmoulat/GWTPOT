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

import com.fountainhead.client.gin.ClientGinjector;
import com.fountainhead.client.place.NameTokens;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.TabDataBasic;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.TabContentProxyPlace;

/**
 * A sample {@link Presenter} filled with arbitrary content. It appears as a tab
 * within {@link HomePresenter}, which is itself a s tab in
 * {@link MainPagePresenter}.
 * <p />
 * It demonstrates the option 3 described in {@link TabInfo}.
 * 


 */
public class HomeNewsPresenter
extends
Presenter<HomeNewsPresenter.MyView, HomeNewsPresenter.MyProxy> {
	/**
	 * {@link HomeNewsPresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.homeNewsPage)
	// @UseGatekeeper(IsAdminGatekeeper.class)
	public interface MyProxy extends TabContentProxyPlace<HomeNewsPresenter> {
	}

	@TabInfo(container = HomePresenter.class)
	static TabData getTabLabel(ClientGinjector ginjector) {
		// Priority = 0, means it will be the left-most tab in the home tab
		return new TabDataBasic(ginjector.getMyConstants().news(), 0);
	}

	/**
	 * {@link HomeNewsPresenter}'s view.
	 */
	public interface MyView extends View {
		void setConfirmationText(String text);
		void setPresenter(HomeNewsPresenter presenter);
	}

	private final PlaceManager placeManager;

	private boolean confirmationEnabled;

	@Inject
	public HomeNewsPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
		view.setPresenter(this);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, HomePresenterBase.TYPE_SetTabContent,
				this);
	}

	@Override
	protected void onReveal() {
		enableConfirmation(false);
	}

	/**
	 * Enables or disables the confirmation dialog.
	 * 
	 * @param enabled
	 *            {@code true} to enable the confirmation dialog, {@code false}
	 *            to disable it.
	 */
	private void enableConfirmation(boolean enabled) {
		this.confirmationEnabled = enabled;
		if (enabled) {
			placeManager
			.setOnLeaveConfirmation("Are you sure you want to navigate away from this page?");
			getView().setConfirmationText(
					"Navigation confirmation ON, click here to disable it!");
		} else {
			placeManager.setOnLeaveConfirmation(null);
			getView().setConfirmationText(
					"Navigation confirmation OFF, click here to enable it!");
		}
	}

	/**
	 * Toggles the state of the confirmation dialog.
	 */
	public void toggleConfirmation() {
		enableConfirmation(!confirmationEnabled);
	}
}
