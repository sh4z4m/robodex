package com.robodex.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


import com.robodex.data.DatabaseHandler;

import android.content.Context;

public class UserLogin {
	
	private JSONParser jsonParser;
	
	private static String loginURL = "http://shazam.avatarsyn.com/andtest/index.php";
	
	private static String login_tag = "login";
	
	// constructor
	public UserLogin(){
		jsonParser = new JSONParser();
	}

	public JSONObject loginUser(String user, String password){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", login_tag));
		params.add(new BasicNameValuePair("user", user));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
		// return json
		// Log.e("JSON", json.toString());
		return json;
	}
	

	public boolean isUserLoggedIn(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if(count > 0){
			// user logged in
			return true;
		}
		return false;
	}
	

	public boolean logoutUser(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTables();
		return true;
	}
	
}
