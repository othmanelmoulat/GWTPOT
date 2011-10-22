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

package com.fountainhead.client.core;

import com.fountainhead.shared.CurrentUser;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

public class LoggedInGatekeeper implements Gatekeeper {

	private final EventBus eventBus;

	private CurrentUser currentUser;

	@Inject
	public LoggedInGatekeeper(final EventBus eventBus) {
		this.eventBus = eventBus;

		this.eventBus.addHandler(LoginAuthenticatedEvent.getType(), new LoginAuthenticatedEventHandler() {
			@Override
			public void onLogin(LoginAuthenticatedEvent event) {

				currentUser = event.getCurrentUser();

				// Log.debug(currentUser.getLogin() +
				// " credentials have been authenticated.");
			}
		});
	}

	@Override
	public boolean canReveal() {
		boolean loggedIn = false;

		if (currentUser != null) {
			loggedIn = currentUser.isLoggedIn();
		}

		return loggedIn;
	}

	/**
	 * @return the currentUser
	 */
	public CurrentUser getCurrentUser() {
		return currentUser;
	}

}
