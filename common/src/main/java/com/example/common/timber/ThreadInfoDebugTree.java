package com.example.common.timber;

import timber.log.Timber;

/**
 * Created by caoyouqiang on 18-3-28.
 */

public class ThreadInfoDebugTree extends Timber.DebugTree{

	@Override
	protected void log(int priority, String tag, String message, Throwable t) {
		if (tag != null){
			String threadName = Thread.currentThread().getName();
			long threadId = Thread.currentThread().getId();
			tag = "<" + threadName + "-" + threadId + ">" + tag;

			/*StackTraceElement[] stackTrace = new Throwable().getStackTrace();
			String line = "(Line " + stackTrace[5].getLineNumber() + ")";
			tag += line;*/
		}
		super.log(priority, tag, message, t);
	}

	@Override
	protected String createStackElementTag(StackTraceElement element) {
		return super.createStackElementTag(element) + "(Line " + element.getLineNumber() + ")";
	}
}
