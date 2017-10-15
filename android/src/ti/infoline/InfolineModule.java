/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2010 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.infoline;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;

import android.app.Activity;
import android.content.Context;
import de.infonline.lib.IOLEventType;
import de.infonline.lib.IOLSession;

@Kroll.module(name = "Infoline", id = "ti.infoline")
public class InfolineModule extends KrollModule {

	// Standard Debugging variables
	private static final String LCAT = "IVWMod";
	private String offerIdentifier;
	private Boolean isSessionopened = false;
	private Boolean isOptIn = false;
	private Boolean dbg = false;

	// You can define constants with @Kroll.constant, for example:
	@Kroll.constant
	public static final String EVENT_VIEW = "view";
	@Kroll.constant
	public static final String STATE_VIEW_APPEARED = "appeared";
	@Kroll.constant
	public static final String STATE_VIEW_DISAPPEARED = "disappeared";
	@Kroll.constant
	public static final String STATE_VIEW_REFRESHED = "refreshed";

	@Kroll.constant
	public static final String EVENT_ADVERTISEMENT = "advertisement";
	@Kroll.constant
	public static final String STATE_ADVERTISEMENT_OPEN = "open";
	@Kroll.constant
	public static final String STATE_ADVERTISEMENT_CLOSE = "close";

	@Kroll.constant
	public static final String EVENT_LOGIN = "login";
	@Kroll.constant
	public static final String STATE_LOGIN_SUCCEEDED = "succeeded";
	@Kroll.constant
	public static final String STATE_LOGIN_FAILED = "failed";
	@Kroll.constant
	public static final String STATE_LOGIN_LOGOUT = "logout";

	@Kroll.constant
	public static final String EVENT_IAP = "iap";
	@Kroll.constant
	public static final String STATE_IAP_STARTED = "started";
	@Kroll.constant
	public static final String STATE_IAP_FINISHED = "finished";
	@Kroll.constant
	public static final String STATE_IAP_CANCELLED = "cancelled";

	@Kroll.constant
	public static final String EVENT_DATA = "data";
	@Kroll.constant
	public static final String EVENT_DOCUMENT = "document";
	@Kroll.constant
	public static final String EVENT_DOWNLOAD = "download";
	@Kroll.constant
	public static final String EVENT_GAME = "game";

	public InfolineModule() {
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app) {
		Log.d(LCAT, "inside onAppCreate");
		// put module init code that needs to run when the application is
		// created
	}

	@Kroll.method
	public void optIn() {
		isOptIn = true;
	}

	@Kroll.method
	public void optOut() {
		isOptIn = false;
		IOLSession.terminateSession();
	}

	@Kroll.method
	public void sendLoggedEvents() {
		IOLSession.sendLoggedEvents();
	}

	@Kroll.method
	public void logEvent(Object _event, Object _state, Object _code,
			Object _comment) {
		String event = "";
		String state = "";
		String code = "";
		String comment = "";
		if (!isOptIn)
			return;
		if (_event instanceof String) {
			event = (String) _event;
		} else
			Log.e(LCAT, "wrong type for event");
		if (_state instanceof String) {
			state = (String) _state;
		} else
			Log.e(LCAT, "wrong type for state");
		if (_code instanceof String) {
			code = (String) _code;
		} else
			Log.e(LCAT, "wrong type for code");
		if (_comment instanceof String) {
			comment = (String) _comment;
		} else
			Log.e(LCAT, "wrong type for comment");
		if (_event instanceof String) {
			event = (String) _event;
		} else
			Log.e(LCAT, "wrong type for event");
		if (offerIdentifier == null) {
			Log.e(LCAT, "offerIdentifier not set");
		}
		// Converting String event into internal type:
		IOLEventType type = Utils.getEventTypeFromString(event + "." + state);
		if (!isSessionopened)
			IOLSession.startSession();
		IOLSession.logEvent(type, code, comment);

	}

	// Methods
	@Kroll.method
	public void startSession() {
		IOLSession.startSession();
		isSessionopened = true;

	}

	// Methods
	@Kroll.method
	public void stopSession() {
		IOLSession.terminateSession();
		isSessionopened = false;

	}

	@Kroll.setProperty
	public void setOfferIdentifier(String value) {
		offerIdentifier = value;
		Context ctx = TiApplication.getInstance().getApplicationContext();
		IOLSession.initIOLSession(ctx, offerIdentifier, dbg);
	}

	@Kroll.setProperty
	public void setDbg(Boolean dbg) {
		this.dbg = dbg;
		IOLSession.setDebugModeEnabled(dbg);
	}

	@Kroll.method
	public void enableDebug() {
		this.dbg = true;
		IOLSession.setDebugModeEnabled(true);
	}
	
	@Kroll.method
	public void disableDebug() {
		this.dbg = false;
		IOLSession.setDebugModeEnabled(false);
	}
	
	@Kroll.method
	public String getVersion() {
		return IOLSession.getVersion();
	}
	
	@Kroll.method
	public void setDeviceIdEnabled(Boolean enabled) {
		IOLSession.setDeviceIDsEnabled(enabled);
	}
	
	@Kroll.setProperty
	public void setCostumerData(String data) {
		IOLSession.setCustomerData(data);
	}

	public void onStart(Activity activity) {
		super.onStart(activity);
		IOLSession.onActivityStart();
	}

	public void onStop(Activity activity) {
		IOLSession.onActivityStop();
		super.onStop(activity);
	}
}
