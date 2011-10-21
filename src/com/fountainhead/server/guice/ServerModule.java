package com.fountainhead.server.guice;

import com.fountainhead.server.LoginHandler;
import com.fountainhead.server.SendTextToServerActionHandler;
import com.fountainhead.shared.LoginAction;
import com.fountainhead.shared.SendTextToServer;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {

		bindHandler(SendTextToServer.class, SendTextToServerActionHandler.class);
		bindHandler(LoginAction.class, LoginHandler.class);
	}
}
