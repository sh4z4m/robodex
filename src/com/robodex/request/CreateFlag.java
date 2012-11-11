package com.robodex.request;

import java.util.Map;

import com.robodex.data.DatabaseContract;
import com.robodex.request.ServerContract.RequestField;
import com.robodex.request.ServerContract.RequestType;

import android.content.ContentValues;
import android.util.Log;

public class CreateFlag extends BaseEditRequest {
	private static final String LOG_TAG = CreateFlag.class.getSimpleName();

	public static final int TABLE_LINK 			= 1;
	public static final int TABLE_LOCATION 		= 2;
	public static final int TABLE_MEMBER 		= 3;
	public static final int TABLE_ORGANIZATION 	= 4;
	public static final int TABLE_PERSON 		= 5;
	public static final int TABLE_SPECIALTY 	= 6;

	private final String 	mComment;
	private final int		mTable;
	private final int		mRowId;


	public CreateFlag(String comment, int table, int rowId) {
		mComment = comment;
		mTable = table;
		mRowId = rowId;
	}

	@Override
	protected String getRequestType() {
		return RequestType.CREATE_FLAG;
	}

	@Override
	protected void populateRequest(Map<String, String> request) {
		request.put(RequestField.FLAG_COMMENT, mComment);

		String field = null;
		switch (mTable) {
		case TABLE_LINK:
			field = RequestField.LINK_ID;
			break;
		case TABLE_LOCATION:
			field = RequestField.LOCATION_ID;
			break;
		case TABLE_MEMBER:
			field = RequestField.MEMBER_ID;
			break;
		case TABLE_ORGANIZATION:
			field = RequestField.ORGANIZATION_ID;
			break;
		case TABLE_PERSON:
			field = RequestField.PERSON_ID;
			break;
		case TABLE_SPECIALTY:
			field = RequestField.SPECIALTY_ID;
			break;
		}

		if (field != null) {
			request.put(field, String.valueOf(mRowId));
		}
		else {
			Log.wtf(LOG_TAG, "No table to associate flag with.");
		}
	}

	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		rowToInsert.put(DatabaseContract.EditResult.COL_RESULT, rowFromResponse.get(RequestField.EDIT_RESULT));
        return rowToInsert;
	}

}
