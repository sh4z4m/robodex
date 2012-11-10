package com.robodex.request;

import java.util.Map;

import android.content.ContentValues;
import android.net.Uri;

import com.robodex.data.DatabaseContract.Specialty;

public class SpecialtyList extends BaseRequest {

    private int mStartPosition;

    public SpecialtyList(int startPosition) {
        mStartPosition = startPosition;
    }

    @Override
    protected void populateRequest(Map<String, String> request) {
        request.put(Fields.START_POSITION, String.valueOf(mStartPosition));
    }

	@Override
	protected ContentValues processRowForInsertion(Map<String, String> row) {
		ContentValues rowToInsert = new ContentValues();
		rowToInsert.put(Specialty.COL_SPECIALTY_ID, 	row.get(Fields.SPECIALTY_ID));
        rowToInsert.put(Specialty.COL_SPECIALTY,    	row.get(Fields.SPECIALTY));
        rowToInsert.put(Specialty.COL_REMOVE,    		row.get(Fields.REMOVE));
        rowToInsert.put(Specialty.COL_REMOVE_APPROVED,  row.get(Fields.REMOVE_APPROVED));
		return rowToInsert;
	}

	@Override
	protected Uri getContentUri() {
		return Specialty.CONTENT_URI;
	}
}
