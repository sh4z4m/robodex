package com.robodex.request;

import java.util.Map;

import com.robodex.data.DatabaseContract;
import com.robodex.request.ServerContract.RequestField;

import android.content.ContentValues;

public final class ListLinks extends BaseEndlessListRequest {

	@Override
	protected void populateRequest(Map<String, String> request) {
		request.put(RequestField.START_POSITION, String.valueOf(getStartPosition()));
	}

	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		rowToInsert.put(DatabaseContract.ListLinks.COL_LINK_ID, 		rowFromResponse.get(RequestField.LINK_ID));
        rowToInsert.put(DatabaseContract.ListLinks.COL_TITLE,    	rowFromResponse.get(RequestField.LINK_TEXT));
        rowToInsert.put(DatabaseContract.ListLinks.COL_LINK,    	rowFromResponse.get(RequestField.LINK));
        rowToInsert.put(DatabaseContract.ListLinks.COL_REMOVE,    		rowFromResponse.get(RequestField.REMOVE));
        rowToInsert.put(DatabaseContract.ListLinks.COL_REMOVE_APPROVED,  rowFromResponse.get(RequestField.REMOVE_APPROVED));
		return rowToInsert;
	}
}
