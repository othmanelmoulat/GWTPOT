package com.fountainhead.server.guice;

import com.gwtplatform.dispatch.server.guice.HandlerModule;
import com.fountainhead.shared.SendTextToServer;
import com.fountainhead.server.SendTextToServerActionHandler;

public class ServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {

		bindHandler(SendTextToServer.class, SendTextToServerActionHandler.class);
	}
}
