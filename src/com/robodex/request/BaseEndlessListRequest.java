package com.robodex.request;

import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.robodex.Robodex;
import com.robodex.request.ServerContract.ResponseCode;


public abstract class BaseEndlessListRequest extends BaseRequest {
	public static final String LOG_TAG = BaseEndlessListRequest.class.getSimpleName();
	public static final int DEFAULT_START_POSITION = 1;

	private final AtomicInteger mStartPosition;

	public BaseEndlessListRequest() {
		this(DEFAULT_START_POSITION);
	}

	public BaseEndlessListRequest(int startPosition) {
		mStartPosition = new AtomicInteger(startPosition);
	}

	public final int getStartPosition() {
		return mStartPosition.get();
	}

	public final void setStartPosition(int startPosition) {
		mStartPosition.set(startPosition);
	}


	@Override
	protected final int handleResponseCodes(JSONObject response) {
		final int code = super.handleResponseCodes(response);

		if (code == ResponseCode.OK) {
			int rows = Robodex.sAppContext.getContentResolver().delete(getContenUri(), null, null);
			if (Robodex.DEBUG) {
				Log.i(LOG_TAG, "Truncated " + rows + " rows from " + getContenUri().getLastPathSegment());
			}
		}
		return code;
	}

	@Override
	protected final int processResponseResults(JSONArray results) {
		final int numRows = super.processResponseResults(results);
		mStartPosition.getAndAdd(numRows);
		if (Robodex.DEBUG) {
			Log.i(LOG_TAG, "New start pos = " + getStartPosition() + " for " + getContenUri().getLastPathSegment());
		}
		return numRows;
	}
}
