package com.robodex.request;

import java.util.Map;

import com.robodex.data.DatabaseContract;
import com.robodex.request.ServerContract.RequestField;
import com.robodex.request.ServerContract.RequestType;

import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

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
	protected String getRequestType() {
		return RequestType.CREATE_FLAG;
	}


	@Override
	protected Uri getContentUri() {
		return DatabaseContract.CreateFlag.CONTENT_URI;
	}

	@Override
	protected void populateRequest(Map<String, String> request) {
		request.put(RequestField.FLAG_COMMENT, mComment);
		request.put(mField, String.valueOf(mRowId));

	}

	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		rowToInsert.put(DatabaseContract.CreateFlag.COL_FLAG_ID, rowFromResponse.get(RequestField.FLAG_ID));
        return rowToInsert;
	}


}
