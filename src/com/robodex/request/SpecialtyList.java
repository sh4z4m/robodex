package com.robodex.request;

import java.util.Map;

import android.content.ContentValues;
import android.net.Uri;

import com.robodex.data.DatabaseContract;
import com.robodex.data.DatabaseContract.Specialty;
import com.robodex.request.ServerContract.RequestField;

public class SpecialtyList extends BaseRequest {

    private final int mStartPosition;

    public SpecialtyList(int startPosition) {
        mStartPosition = startPosition;
    }

    @Override
	protected String getRequestType() {
		return ServerContract.RequestType.LIST_SPECIALTIES;
	}

    @Override
	protected Uri getContentUri() {
		return DatabaseContract.Specialty.CONTENT_URI;
	}

    @Override
    protected void populateRequest(Map<String, String> request) {
        request.put(RequestField.START_POSITION, String.valueOf(mStartPosition));
    }

	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		rowToInsert.put(Specialty.COL_SPECIALTY_ID, 	rowFromResponse.get(RequestField.SPECIALTY_ID));
        rowToInsert.put(Specialty.COL_SPECIALTY,    	rowFromResponse.get(RequestField.SPECIALTY));
        rowToInsert.put(Specialty.COL_REMOVE,    		rowFromResponse.get(RequestField.REMOVE));
        rowToInsert.put(Specialty.COL_REMOVE_APPROVED,  rowFromResponse.get(RequestField.REMOVE_APPROVED));
		return rowToInsert;
	}
}
