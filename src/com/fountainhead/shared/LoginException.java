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

package com.fountainhead.shared;

import com.gwtplatform.dispatch.shared.ActionException;

public class LoginException extends ActionException {

	private static final long serialVersionUID = -6517677039932882817L;

	public LoginException() {
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(String message, Throwable cause) {
		super(message + " (" + cause.getMessage() + ")");
	}

	public LoginException(Throwable cause) {
		super(cause.getMessage());
	}
}
