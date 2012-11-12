package com.robodex.request;

import java.util.Map;

import android.content.ContentValues;

import com.robodex.data.DatabaseContract;
import com.robodex.request.ServerContract.RequestField;

public final class Login extends BaseRequest {

	private final String mUsername;
	private final String mPassword;

	public Login(String username, String password) {
		mUsername = username;
		mPassword = password;
	}

	@Override
	protected void populateRequest(Map<String, String> request) {
		request.put(RequestField.USERNAME, mUsername);
		request.put(RequestField.PASSWORD, mPassword);
	}

	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		rowToInsert.put(DatabaseContract.Login.COL_AUTH_KEY, rowFromResponse.get(RequestField.AUTH_KEY));
        return rowToInsert;
	}
}
