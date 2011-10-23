/**

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

package com.fountainhead.client.presenter;

import com.fountainhead.client.place.NameTokens;
import com.fountainhead.client.ui.ErrorPageUiHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Place;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;


public class ErrorPagePresenter extends
Presenter<ErrorPagePresenter.MyView, ErrorPagePresenter.MyProxy> implements
ErrorPageUiHandlers {

	private final PlaceManager placeManager;

	// don't forget to update SerendipityGinjector & ClientModule
	@ProxyCodeSplit
	@NameToken(NameTokens.errorPage)
	@NoGatekeeper
	public interface MyProxy extends Proxy<ErrorPagePresenter>, Place {
	}

	public interface MyView extends View, HasUiHandlers<ErrorPageUiHandlers> {
	}

	@Inject
	public ErrorPagePresenter(EventBus eventBus, MyView view, MyProxy proxy,
			PlaceManager placeManager) {
		super(eventBus, view, proxy);

		getView().setUiHandlers(this);

		this.placeManager = placeManager;
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void revealInParent() {
		// RevealRootLayoutContentEvent.fire(this, this);
		RevealRootContentEvent.fire(this, this);
	}

	@Override
	public void onOkButtonClicked() {
		PlaceRequest placeRequest = new PlaceRequest(NameTokens.mainPage);
		placeManager.revealPlace(placeRequest);
	}
}