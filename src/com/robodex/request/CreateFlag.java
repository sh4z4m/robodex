package com.robodex.request;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;

import com.robodex.data.DatabaseContract;
import com.robodex.request.ServerContract.RequestField;

public class CreateFlag extends BaseRequest {
	private static final String LOG_TAG = CreateFlag.class.getSimpleName();

	private final String	mField;
	private final int		mRowId;
	private final String 	mComment;

	public CreateFlag(String field, int rowId, String comment) {
		mField = field;
		mRowId = rowId;
		mComment = comment;
	}

	@Override
	protected void prepareRequest() {
		Map<String, String> request = new HashMap<String, String>();
		request.put(RequestField.FLAG_COMMENT, mComment);
		request.put(mField, String.valueOf(mRowId));
		executeRequest(request);
	}

	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		rowToInsert.put(DatabaseContract.CreateFlag.COL_FLAG_ID, rowFromResponse.get(RequestField.FLAG_ID));
        return rowToInsert;
	}


}
