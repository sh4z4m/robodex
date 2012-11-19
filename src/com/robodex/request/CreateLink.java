package com.robodex.request;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;

import com.robodex.data.DatabaseContract;
import com.robodex.request.ServerContract.RequestField;

public final class CreateLink extends BaseRequest {
	private final int mRoleId;
	private final String mUrl;
	private final String mTitle;
	private final String mField;
	private final int mValue;

	public CreateLink(String url, String title, int roleId, String field, int value) {
		mRoleId = roleId;
		mUrl = url;
		mTitle = title;
		mField = field;
		mValue = value;
	}

	@Override
	protected void prepareRequest() {
		Map<String, String> request = new HashMap<String, String>();
		request.put(RequestField.ROLE_ID, String.valueOf(mRoleId));
		request.put(RequestField.LINK, mUrl);
		request.put(RequestField.LINK_TEXT, mTitle);
		request.put(mField, String.valueOf(mValue));
		executeRequest(request);
	}

	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		rowToInsert.put(DatabaseContract.CreateLink.COL_LINK_ID, rowFromResponse.get(RequestField.LINK_ID));
        return rowToInsert;
	}
}
