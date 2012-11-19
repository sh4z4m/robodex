package com.robodex.request;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;

public final class EditLocation extends BaseRequest {

	@Override
	protected void prepareRequest() {
		Map<String, String> request = new HashMap<String, String>();
		// TODO
		executeRequest(request);
	}

	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		// TODO
//		rowToInsert.put(DatabaseContract., rowFromResponse.get(RequestField.));
        return rowToInsert;
	}
}
