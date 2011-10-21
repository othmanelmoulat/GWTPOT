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

package com.fountainhead.client.gin;

import com.fountainhead.client.IsAdminGatekeeper;
import com.fountainhead.client.presenter.HomePresenter;
import com.fountainhead.client.presenter.MainPagePresenter;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;

/**
 * The base interface of {@link ClientGinjector}. Used mostly to exercise issue
 * 217: http://code.google.com/p/gwt-platform/issues/detail?id=217
 * 
 * @author Philippe Beaudoin
 */
public interface ClientGinjectorBase extends Ginjector {
	EventBus getEventBus();
	AsyncProvider<HomePresenter> getHomePresenter();
	Provider<MainPagePresenter> getMainPagePresenter();
	IsAdminGatekeeper getIsAdminGatekeeper();
}