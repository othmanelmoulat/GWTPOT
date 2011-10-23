/**
 * Copyright 2011 ArcBees Inc.
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

import com.fountainhead.client.place.NameTokens;
import com.fountainhead.client.view.ReportUiHandlers;
import com.fountainhead.shared.CurrentUser;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TabInfo;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.TabContentProxyPlace;

/**
 * A sample {@link Presenter} that lets user toggle between being an
 * administrator and a regular user.
 * <p />
 * It demonstrates the option 1 described in {@link TabInfo}.
 * 
 * @author Christian Goudreau
 * @author Philippe Beaudoin
 */
public class ReportPresenter
extends
Presenter<ReportPresenter.MyView, ReportPresenter.MyProxy>
implements
ReportUiHandlers {

	private final DispatchAsync dispatcher;
	private final CurrentUser currentUser;
	/**
	 * {@link ReportPresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.settingsPage)
	@TabInfo(container = MainPagePresenter.class, label = "Report", priority = 1)
	// The third tab in the main page
	public interface MyProxy extends TabContentProxyPlace<ReportPresenter> {
	}

	/**
	 * {@link ReportPresenter}'s view.
	 */
	public interface MyView extends View, HasUiHandlers<ReportUiHandlers> {
		void setAdmin(boolean isAdmin);
		void setUserName(String username);
	}



	@Inject
	public ReportPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy, final DispatchAsync dispatcher,
			final CurrentUser currentUser) {
		super(eventBus, view, proxy);
		this.currentUser = currentUser;
		this.dispatcher = dispatcher;
		view.setUiHandlers(this);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetTabContent,
				this);

	}

	@Override
	protected void onReveal() {
		loadTree();
	}

	// @Override
	// public void togglePrivileges() {
	// currentUser.setAdministrator(!currentUser.isAdministrator());
	// updateView();
	// }
	//
	// private void updateView() {
	// System.out.println(currentUser.getLogin());
	// System.out.println("isAdmin="+currentUser.isAdministrator());
	// getView().setAdmin(currentUser.isAdministrator());
	// getView().setUserName(currentUser.getLogin());
	// }

	@Override
	public void loadTree() {

	}
}