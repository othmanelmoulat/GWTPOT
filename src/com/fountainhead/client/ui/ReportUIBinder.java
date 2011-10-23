package com.fountainhead.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ReportUIBinder extends Composite {

	private static final Binder binder = GWT.create(Binder.class);

	interface Binder extends UiBinder<Widget, ReportUIBinder> {
	}

	public ReportUIBinder() {
		initWidget(binder.createAndBindUi(this));
	}

}
