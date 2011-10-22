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

package com.fountainhead.shared;

import java.io.Serializable;

/**
 * This is a basic class that holds the privileges of the user currently logged
 * in.
 * 
 * @author Philippe Beaudoin
 */
public class CurrentUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String login;

	private boolean loggedIn = true;
	private boolean administrator;

	// private boolean authenticated = true;
	// private String email;
	// private String nickname;
	// private String realName;
	// private String locale;


	public String getLogin() {
		return login;    
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isAdministrator() {
		return administrator;
	}

	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
}
