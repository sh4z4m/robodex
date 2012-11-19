package com.robodex.request;

import java.util.Map;

import android.content.ContentValues;

public final class SearchPendingFlags extends BaseEndlessListRequest {
	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		// TODO
//		rowToInsert.put(DatabaseContract., rowFromResponse.get(RequestField.));
        return rowToInsert;
	}
}
