package com.robodex.request;

import java.util.Map;

import com.robodex.request.ServerContract.RequestField;

import android.content.ContentValues;

public final class ListOrganizations extends BaseEndlessListRequest {

	@Override
	protected void populateRequest(Map<String, String> request) {
		request.put(RequestField.START_POSITION, String.valueOf(getStartPosition()));
	}

	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		// TODO
//		rowToInsert.put(DatabaseContract., rowFromResponse.get(RequestField.));
        return rowToInsert;
	}
}
