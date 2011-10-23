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

import java.util.Iterator;
import java.util.Set;

import com.fountainhead.client.place.NameTokens;
import com.fountainhead.client.view.ReportUiHandlers;
import com.fountainhead.shared.CurrentUser;
import com.fountainhead.shared.LoadTreeAction;
import com.fountainhead.shared.LoadTreeResult;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
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
		Tree getTree();
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
		// loadTree();
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
		getDispatcher().execute(new LoadTreeAction(""),
				new AsyncCallback<LoadTreeResult>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());

			}

			@Override
			public void onSuccess(LoadTreeResult result) {
				JSONValue jsonValue = result.getResponse();

			}
		});
	}
	/*
	 * Add the object presented by the JSONValue as a children to the requested
	 * TreeItem.
	 */
	private void addChildren(TreeItem treeItem, JSONValue jsonValue) {
		JSONArray jsonArray;
		JSONObject jsonObject;
		JSONString jsonString;

		if ((jsonArray = jsonValue.isArray()) != null) {
			for (int i = 0; i < jsonArray.size(); ++i) {
				TreeItem child = treeItem.addItem(getChildText("["
						+ Integer.toString(i) + "]"));
				addChildren(child, jsonArray.get(i));
			}
		} else if ((jsonObject = jsonValue.isObject()) != null) {
			Set keys = jsonObject.keySet();
			for (Iterator iter = keys.iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				TreeItem child = treeItem.addItem(getChildText(key));
				addChildren(child, jsonObject.get(key));
			}
		} else if ((jsonString = jsonValue.isString()) != null) {
			// Use stringValue instead of toString() because we don't want
			// escaping
			treeItem.addItem(jsonString.stringValue());
		} else {
			// JSONBoolean, JSONNumber, and JSONNull work well with toString().
			treeItem.addItem(getChildText(jsonValue.toString()));
		}
	}

	private void displayError(String responseText) {
		Tree jsonTree = getView().getTree();
		jsonTree.removeItems();
		jsonTree.setVisible(true);
		TreeItem treeItem = jsonTree.addItem("Failed to parse JSON response");
		treeItem.addItem(responseText);
		treeItem.setStyleName("JSON-JSONResponseObject");
		treeItem.setState(true);
	}

	/*
	 * Update the treeview of a JSON object.
	 */
	private void displayJSONObject(JSONValue jsonValue) {
		Tree jsonTree = getView().getTree();
		jsonTree.removeItems();
		jsonTree.setVisible(true);
		TreeItem treeItem = jsonTree.addItem("root");
		addChildren(treeItem, jsonValue);
		// treeItem.setStyleName("JSON-JSONResponseObject");
		treeItem.setState(true);
	}
	/*
	 * Causes the text of child elements to wrap.
	 */
	private String getChildText(String text) {
		return "<span style='white-space:normal'>" + text + "</span>";
	}

	/**
	 * @return the dispatcher
	 */
	public DispatchAsync getDispatcher() {
		return dispatcher;
	}

	/**
	 * @return the currentUser
	 */
	public CurrentUser getCurrentUser() {
		return currentUser;
	}

}