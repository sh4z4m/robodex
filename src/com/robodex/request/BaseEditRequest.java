package com.robodex.request;

import android.net.Uri;

import com.robodex.HttpHelper.HttpPostTask;
import com.robodex.data.DatabaseContract;

public abstract class BaseEditRequest extends BaseRequest {

	public BaseEditRequest() {
		this(null);
	}

	public BaseEditRequest(HttpPostTask.Callback callback) {
		super(callback);
	}

	@Override
	protected final Uri getContentUri() {
		return DatabaseContract.EditResult.CONTENT_URI;
	}
}
