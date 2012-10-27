package com.robodex.request;

import org.json.JSONException;
import org.json.JSONObject;

public class SpecialtyList extends BaseRequest {
	@Override
	protected JSONObject getRequest() {
		JSONObject request = new JSONObject();
		try {
			request.put(Request.KEY_START, Request.getStartPosition());
		} 
		catch (JSONException e) {
			e.printStackTrace();
		}
		return request;
	}

	@Override
	protected void processInBackground(JSONObject response) {
		// TODO Auto-generated method stub
		
	}
}
