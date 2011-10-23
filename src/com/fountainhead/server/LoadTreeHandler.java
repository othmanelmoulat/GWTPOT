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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.fountainhead.shared.LoadTreeAction;
import com.fountainhead.shared.LoadTreeResult;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoadTreeHandler
implements
ActionHandler<LoadTreeAction, LoadTreeResult> {

	private final ServletContext servletContext;
	private final Provider<HttpServletRequest> requestProvider;

	@Inject
	LoadTreeHandler(ServletContext servletContext,
			Provider<HttpServletRequest> requestProvider) {
		this.servletContext = servletContext;
		this.requestProvider = requestProvider;
	}

	@Override
	public LoadTreeResult execute(LoadTreeAction action,
			ExecutionContext context)
					throws ActionException {

		String username = action.getJsonFile();
		InputStream in = servletContext
				.getResourceAsStream("/WEB-INF/tree-data.json");


		JSONValue jsonValue = JSONParser.parse(getStringFromStream(in));
		return new LoadTreeResult(jsonValue);

	}
	private String getStringFromStream(InputStream is){
		InputStreamReader input = new InputStreamReader(is/*, "UTF-8"*/);
		final int CHARS_PER_PAGE = 5000; //counting spaces
		final char[] buffer = new char[CHARS_PER_PAGE];
		StringBuilder output = new StringBuilder(CHARS_PER_PAGE);
		try {
			for(int read = input.read(buffer, 0, buffer.length);
					read != -1;
					read = input.read(buffer, 0, buffer.length)) {
				output.append(buffer, 0, read);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return output.toString();

	}

	@Override
	public Class<LoadTreeAction> getActionType() {
		// TODO Auto-generated method stub
		return LoadTreeAction.class;
	}

	@Override
	public void undo(LoadTreeAction arg0, LoadTreeResult arg1,
			ExecutionContext arg2) throws ActionException {
		// TODO Auto-generated method stub

	}



}
