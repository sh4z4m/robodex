package com.robodex.request;

import java.util.Map;

import android.content.ContentValues;

public final class DetailLocation extends BaseRequest {

	@Override
	protected void populateRequest(Map<String, String> request) {
		// TODO
	}

	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		// TODO
//		rowToInsert.put(DatabaseContract., rowFromResponse.get(RequestField.));
        return rowToInsert;
	}
}
