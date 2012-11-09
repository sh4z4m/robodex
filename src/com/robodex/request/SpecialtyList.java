package com.robodex.request;

import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.widget.GridLayout.Spec;

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
    protected void processInBackground(List<Map<String, String>> results) {
    	// Represents a row to be inserted into the database.
        ContentValues rowToInsert;

        for (Map<String, String> rowFromResponse : results) {
        	rowToInsert = new ContentValues();

        	// Column name, value from response
            rowToInsert.put(Specialty.COL_SPECIALTY_ID, 	rowFromResponse.get(Fields.SPECIALTY_ID));
            rowToInsert.put(Specialty.COL_SPECIALTY,    	rowFromResponse.get(Fields.SPECIALTY));
            rowToInsert.put(Specialty.COL_REMOVE,    		rowFromResponse.get(Fields.SPECIALTY));
            rowToInsert.put(Specialty.COL_REMOVE_APPROVED,  rowFromResponse.get(Fields.SPECIALTY));

            // TODO insert into database
        }
    }
}
