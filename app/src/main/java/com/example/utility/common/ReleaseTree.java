package com.example.utility.common;

import android.util.Log;

/**
 * Created by caoyouqiang on 18-3-28.
 */

public class ReleaseTree extends ThreadInfoDebugTree{
	@Override
	protected void log(int priority, String tag, String message, Throwable t) {
		if (!isLoggable(tag, priority)){
			return;
		}
		super.log(priority, tag, message, t);
	}

	@Override
	protected String createStackElementTag(StackTraceElement element) {
		return super.createStackElementTag(element);
	}

	@Override
	protected boolean isLoggable(String tag, int priority) {
		if (Log.DEBUG == priority || Log.VERBOSE == priority || Log.INFO == priority){
			return false;
		}
		return super.isLoggable(tag, priority);
	}
}
