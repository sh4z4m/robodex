package com.robodex.request;

import java.util.Map;

import android.content.ContentValues;

import com.robodex.data.DatabaseContract;
import com.robodex.request.ServerContract.RequestField;

public final class ListSpecialties extends BaseEndlessListRequest {
    @Override
    protected void populateRequest(Map<String, String> request) {
        request.put(RequestField.START_POSITION, String.valueOf(getStartPosition()));
    }

	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		rowToInsert.put(DatabaseContract.ListSpecialties.COL_SPECIALTY_ID, 	rowFromResponse.get(RequestField.SPECIALTY_ID));
        rowToInsert.put(DatabaseContract.ListSpecialties.COL_SPECIALTY,    	rowFromResponse.get(RequestField.SPECIALTY));
        rowToInsert.put(DatabaseContract.ListSpecialties.COL_REMOVE,    		rowFromResponse.get(RequestField.REMOVE));
        rowToInsert.put(DatabaseContract.ListSpecialties.COL_REMOVE_APPROVED,  rowFromResponse.get(RequestField.REMOVE_APPROVED));
		return rowToInsert;
	}
}
