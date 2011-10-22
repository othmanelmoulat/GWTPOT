/**
 * Copyright 2010 Philippe Beaudoin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fountainhead.server;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.fountainhead.shared.CurrentUser;
import com.fountainhead.shared.FieldVerifier;
import com.fountainhead.shared.LoginAction;
import com.fountainhead.shared.LoginResult;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoginHandler implements ActionHandler<LoginAction, LoginResult> {

	private final ServletContext servletContext;
	private final Provider<HttpServletRequest> requestProvider;

	@Inject
	LoginHandler(ServletContext servletContext,
			Provider<HttpServletRequest> requestProvider) {
		this.servletContext = servletContext;
		this.requestProvider = requestProvider;
	}

	@Override
	public LoginResult execute(LoginAction action, ExecutionContext context)
			throws ActionException {

		String username = action.getUsername();
		String password = action.getPassword();
		// EventBus eventBus = action.getEventBus();

		// Verify that the input is valid.
		if (!FieldVerifier.isValidUserName(username)) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			System.out.println("Exception!");
			throw new ActionException("Name must be at least 4 characters long");
		}
		if (!FieldVerifier.isValidPassword(password)) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			System.out.println("Exception!");
			throw new ActionException(
					" Passwords must contain at least 8 characters with at least one digit,one upper case letter, one lower case letter and one special symbol ");
		}

		CurrentUser user = isUserValid(username, password);
		if (user == null) {
			// If the input is not valid, throw an IllegalArgumentException back
			// to
			// the client.
			System.out.println("Exception!");
			throw new ActionException(
					"Invalid username and/or Password");
		}
		return new LoginResult(user);
	}

	/* This method should be using spring-security or something like that */
	private CurrentUser isUserValid(String username, String password) {

		CurrentUser user = null;

		if ("administrator".equals(username)
				&& "administrator".equals(password)) {
			user = new CurrentUser();
			user.setLogin(username);
			user.setLoggedIn(true);
			user.setAdministrator(true);

		} else if ("user".equals(username) && "user".equals(password)) {
			user = new CurrentUser();
			user.setLogin(username);
			user.setLoggedIn(true);
			user.setAdministrator(false);

		}

		return user;

	}

	@Override
	public void undo(LoginAction action, LoginResult result,
			ExecutionContext context) throws ActionException {
		// Not undoable
	}

	@Override
	public Class<LoginAction> getActionType() {
		return LoginAction.class;
	}

}
