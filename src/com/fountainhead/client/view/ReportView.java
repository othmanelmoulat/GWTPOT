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

package com.fountainhead.client.view;

import com.fountainhead.client.presenter.ReportPresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * The view implementation for
 * {@link com.fountainhead.client.presenter.ReportPresenter}.
 * 
 */
@SuppressWarnings("rawtypes")
public class ReportView extends ViewWithUiHandlers<ReportUiHandlers>
		implements
			ReportPresenter.MyView {

	/**
	 */
	public interface Binder extends UiBinder<Widget, ReportView> {
	}

	private final Widget widget;

	@Inject
	public ReportView(Binder uiBinder) {
		widget = uiBinder.createAndBindUi(this);
		this.bindCustomUiHandlers();
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setAdmin(boolean isAdmin) {

	}

	@Override
	public void setUserName(String username) {

	}

	@UiField
	Button button1;
	@UiField
	Button button2;
	@UiField
	Button button3;
	@UiField
	Button saveButton;
	@UiField
	Button runButton;
	@UiField
	ListBox itemsList;
	@UiField
	Tree itemsTree;

	void onButtonClick(ClickEvent event) {
	}

	@Override
	public Tree getTree() {
		// TODO Auto-generated method stub
		return itemsTree;
	}
	@SuppressWarnings("unchecked")
	protected void bindCustomUiHandlers() {

		itemsTree.addSelectionHandler(new SelectionHandler() {

			@Override
			public void onSelection(SelectionEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onSelection();
				}

			}
		});
	}

	@Override
	public ListBox getList() {
		// TODO Auto-generated method stub
		return itemsList;
	}

}