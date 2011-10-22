/**
 * (C) Copyright 2010, 2011 upTick Pty Ltd
 * 
 * Licensed under the terms of the GNU General Public License version 3 
 * as published by the Free Software Foundation. You may obtain a copy of the
 * License at: http://www.gnu.org/copyleft/gpl.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations 
 * under the License.
 */

package com.fountainhead.client.view;

import com.fountainhead.client.presenter.ErrorPagePresenter;
import com.fountainhead.client.ui.ErrorPageUiHandlers;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
public class ErrorPageView extends ViewWithUiHandlers<ErrorPageUiHandlers> implements 
ErrorPagePresenter.MyView {

	private static String html = "<div>\n"
			+ "<table align=\"center\">\n"
			+ "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"
			+ "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"
			+ "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"
			+ "  <tr>\n"    
			+ "    <td style=\"font-weight:bold;\">An error has occurred.</td>\n"
			+ "  </tr>\n"
			+ "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"
			+ "  <tr>\n" + "<td>Try this action again. If the problem continues,</td>\n" + "</tr>\n"
			+ "  <tr>\n" + "<td>check the Serendipity forums for a solution or</td>\n" + "</tr>\n"
			+ "  <tr>\n" + "<td>contact your Serendipity administrator.</td>\n" + "</tr>\n"
			+ "  <tr>\n" + "<td>&nbsp;</td>\n" + "</tr>\n"    
			+ "  <tr>\n"
			+ "    <td id=\"okButtonContainer\"></td>\n"  
			+ "  </tr>\n"     
			+ "</table>\n"
			+ "</div>\n";

	private final HTMLPanel panel;

	private final Button okButton;

	public ErrorPageView() {

		panel = new HTMLPanel(html);

		okButton = new Button("OK");

		panel.add(okButton, "okButtonContainer");

		bindCustomUiHandlers();
	}

	protected void bindCustomUiHandlers() {

		okButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if (getUiHandlers() != null) {
					getUiHandlers().onOkButtonClicked();
				}
			}
		});
	}  

	@Override
	public Widget asWidget() {
		return panel;
	}
}
