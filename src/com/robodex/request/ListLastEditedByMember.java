package com.robodex.request;

import java.util.HashMap;
import java.util.Map;

import com.robodex.request.ServerContract.RequestField;

import android.content.ContentValues;

public final class ListLastEditedByMember extends BaseEndlessListRequest {
	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		// TODO
//		rowToInsert.put(DatabaseContract., rowFromResponse.get(RequestField.));
        return rowToInsert;
	}
}
