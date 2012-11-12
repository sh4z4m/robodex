package com.robodex.request;


public abstract class BaseEndlessListRequest extends BaseRequest {
	public static final int DEFAULT_START_POSITION = 1;

	private int mStartPosition;

	public BaseEndlessListRequest() {
		this(DEFAULT_START_POSITION);
	}

	public BaseEndlessListRequest(int startPosition) {
		mStartPosition = startPosition;
	}

	public final int getStartPosition() {
		return mStartPosition;
	}

	public final void setStartPosition(int pos) {
		mStartPosition = pos;
	}
}
