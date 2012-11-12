package com.robodex.request;

import java.util.Map;

import com.robodex.data.DatabaseContract;
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
		rowToInsert.put(DatabaseContract.ListOrganizations.COL_ORGANIZATION_ID, rowFromResponse.get(RequestField.ORGANIZATION_ID));
        rowToInsert.put(DatabaseContract.ListOrganizations.COL_ORGANIZATION,    rowFromResponse.get(RequestField.ORGANIZATION));
        rowToInsert.put(DatabaseContract.ListOrganizations.COL_REMOVE,    		rowFromResponse.get(RequestField.REMOVE));
        rowToInsert.put(DatabaseContract.ListOrganizations.COL_REMOVE_APPROVED, rowFromResponse.get(RequestField.REMOVE_APPROVED));
		return rowToInsert;
	}
}
