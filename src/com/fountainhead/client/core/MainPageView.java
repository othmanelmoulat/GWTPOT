
package com.fountainhead.client.core;

import com.fountainhead.client.core.ui.RoundTabPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.Tab;
import com.gwtplatform.mvp.client.TabData;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * This is the main view of the application. Every time a leaf presenter wants
 * to reveal himself, mainPage will add the content of the target inside the
 * mainContantPanel.
 * 
 * 
 */
public class MainPageView extends ViewImpl implements MainPagePresenter.MyView {
	private static MainPageViewUiBinder uiBinder = GWT
			.create(MainPageViewUiBinder.class);

	interface MainPageViewUiBinder extends UiBinder<Widget, MainPageView> {
	}

	public final Widget widget;

	@UiField
	RoundTabPanel tabPanel;

	public MainPageView() {
		widget = uiBinder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	/*@Override
	public void setContent(Object slot, Widget content) {
		if (slot == MainPagePresenter.TYPE_SetTabContent) {
			tabPanel.setPanelContent(content);
		} else {
			super.setContent(slot, content);
		}
	}

	@Override
	public Tab addTab(String tabName, String historyToken, float priority) {
		return tabPanel.addTab(tabName, historyToken, priority);
	}
*/
	@Override
	public void removeTab(Tab tab) {
		tabPanel.removeTab(tab);
	}

	@Override
	public void removeTabs() {
		tabPanel.removeTabs();
	}

	@Override
	public void setActiveTab(Tab tab) {
		tabPanel.setActiveTab(tab);
	}

	@Override
	public Tab addTab(TabData tabData, String historyToken) {
		// TODO Auto-generated method stub
		return tabPanel.addTab(tabData, historyToken);
	}
}