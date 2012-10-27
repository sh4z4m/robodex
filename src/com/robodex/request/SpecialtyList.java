package com.robodex.request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.util.Log;

public class SpecialtyList extends BaseRequest {
	private static final String LOG_TAG = SpecialtyList.class.getSimpleName();
	
	private int mStartPosition;
	
	public SpecialtyList(int startPosition) {
		mStartPosition = startPosition;
	}
	
	@Override
	protected JSONObject getRequest() {
		JSONObject request = new JSONObject();
		try {
			request.put(Request.KEY_START_POSITION, mStartPosition);
		} 
		catch (JSONException e) {
			Log.e(LOG_TAG, "error forming request", e);
		}
		return request;
	}

	@Override
	protected void processInBackground(JSONObject response) {
		// Represents a row to be inserted into the database.
		ContentValues rowToInsert = new ContentValues();	

		try {
			JSONArray results = response.getJSONArray("response");					
			JSONObject rowFromResponse;
			
			for (int i = 0; i < results.length(); ++i) {
				rowFromResponse = results.getJSONObject(i);
				
				// Column name, value from response
				rowToInsert.put("specialty_id", rowFromResponse.getString("specialty_id"));
				rowToInsert.put("specialty", 	rowFromResponse.getString("specialty"));
				
				// TODO insert into database
			}
			
		}
		catch (JSONException e) {
			Log.e(LOG_TAG, "error parsing response", e);
		}		
	}
}
