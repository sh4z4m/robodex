package com.robodex.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.robodex.HttpHelper;
import com.robodex.Robodex;
import com.robodex.HttpHelper.HttpPostTask;
import com.robodex.HttpHelper.HttpPostTask.Callback;
import com.robodex.Private;

public abstract class BaseRequest {
	private static final String LOG_TAG = BaseRequest.class.getSimpleName();
	
	protected static final class Request {
		private static final String URL 			 		= "http://businesshours.net/oatia/json/test.php";
		
		// HTTP Post variable names
		private static final String POST_REQUEST	 		= "request";
		private static final String POST_HASH 				= "hash";		
		
		// All requests share these
		private static final String KEY_REQUEST_TYPE 		= "request_type";
		private static final String KEY_SESSION_ID	 		= "session_id";
		
		// Some requests share these
		static final String 		KEY_START 				= "start_position";
		
		
		// Valid request types
		static final String TYPE_LOGIN 						= "login";
		static final String TYPE_CHECK_IN 					= "check_in";
		
		static final String TYPE_DETAIL_PERSON 				= "detail_person";
		static final String TYPE_DETAIL_LOCATION 			= "detail_location";
		
		static final String TYPE_LIST_SPECIALTIES 			= "list_specialties";
		static final String TYPE_LIST_COORDINATES 			= "list_coordinates";
		static final String TYPE_LIST_ORGANIZATIONS 		= "list_organizations";
		static final String TYPE_LIST_LINKS 				= "list_links";
		static final String TYPE_LIST_PENDING_FLAGS 		= "list_pending_flags";
		static final String TYPE_LIST_ROLES					= "list_roles";
		static final String TYPE_LIST_PEOPLE_BY_SPECIALTY 	= "list_people_by_specialty";
		static final String TYPE_LIST_LOCATIONS 			= "list_locations";
		static final String TYPE_LIST_APPROVED_FLAGS 		= "list_approved_flags";
	
		static final String TYPE_SEARCH_ALL 				= "search_all";
		static final String TYPE_SEARCH_SPECIALTIES 		= "search_specialties";
		static final String TYPE_SEARCH_MAP 				= "search_map";
		static final String TYPE_SEARCH_ORGANIZATIONS 		= "search_organizations";
		static final String TYPE_SEARCH_LINKS			 	= "search_links";
		static final String TYPE_SEARCH_PEOPLE_BY_SPECIALTY = "search_people_by_specialty";
		static final String TYPE_SEARCH_locations 			= "search_locations";
		static final String TYPE_SEARCH_PENDING_FLAGS 		= "search_pending_flags";
		static final String TYPE_SEARCH_APPROVED_FLAGS 		= "search_approved_flags";
		
		static final String TYPE_CREATE_PERSON 				= "create_person";
		static final String TYPE_CREATE_SPECIALTY 			= "create_specialty";
		static final String TYPE_CREATE_ORGANIZATION 		= "create_organization";
		static final String TYPE_CREATE_LINK 				= "create_link";
		static final String TYPE_CREATE_LOCATION 			= "create_location";
		static final String TYPE_CREATE_FLAG 				= "create_flag";	
		
		static final String TYPE_EDIT_SPECIALTY 			= "edit_specialty";
		static final String TYPE_EDIT_ORGANIZATION 			= "edit_organization";
		static final String TYPE_EDIT_LINK 					= "edit_link";
		static final String TYPE_EDIT_PERSON 				= "edit_person";
		static final String TYPE_EDIT_LOCATION 				= "edit_location";
		static final String TYPE_EDIT_FLAG 					= "edit_flag";
		static final String TYPE_EDIT_ROLE 					= "edit_role";
		
		
		
		
		// TODO complete this as each class is made
		static String getType(Class<? extends BaseRequest> cls) {
			if (cls == Login.class) return TYPE_LOGIN;
			if (cls == SpecialtyList.class) return TYPE_LIST_SPECIALTIES;
			
			return null;
		}

		// TODO figure out storage
		static String getSessionId() {
			
			return "";
		}
		
		// TODO figure out storage
		static String getStartPosition() {
			
			return "1";
		}
	}
	
	protected static final class Response {
		// Shared response variable names
		private static final String KEY_ERROR_CODE		= "error_code";
		private static final String KEY_ERROR_MESSAGE	= "error_message";
	}

	
	
	
	private final Callback mCallback;
	
	protected BaseRequest() {
		mCallback = new Callback() {
			@Override
			public void onExtraBackgroundProcessing(HttpPostTask task) {				
				String 		response 	= null;				
				String	 	hash 		= null;
				JSONObject 	json 		= null;
								
				int 		size 		= task.getResponseLines().size();
				
				if (size >= 2) {
					response 	= task.getResponseLines().get(0);					
					hash 		= task.getResponseLines().get(1);	
				}
				else {
					Throwable t = task.getConnectionError();
					if (t == null) t = task.getParseError();
					Log.e(LOG_TAG, "Response and hash are not defined.", t);
					return;
				}
				
				
				if (!hash.equals(Private.calculateHash(response))) {
					Log.e(LOG_TAG, "Failed hash check.");
					if (Robodex.ENFORCE_HASH_CHECK) return;
				}
				
				
				try { 
					response = URLDecoder.decode(response, "UTF-8");
					json 	 = new JSONObject(response); 
				}			
				catch (UnsupportedEncodingException e) {
					Log.e(LOG_TAG, "Failed URL-decoding response.", e);
				}
				catch (JSONException e) { 
					Log.e(LOG_TAG, "Failed converting response to JSONObject.", e);
				}
				
				if (json != null) {
					int 	code = 0;
					String  msg  = null;					
					
					try {
						code = json.getInt(	  Response.KEY_ERROR_CODE);
						msg  = json.getString(Response.KEY_ERROR_MESSAGE);
					} 
					catch (JSONException e) {
						Log.e(LOG_TAG, "Failed parsing response error code or message.", e);
					}
					
					if (code != 0) {
						Log.e(LOG_TAG, "Error " + code + ": " + msg);
					}
					
					processInBackground(json);
				}
			}
			
			@Override
			public void onCompleted(HttpPostTask task) {
				// blank
			}
		};
	}
	
	public final void execute() {
		JSONObject json = getRequest();
		if (json == null) json = new JSONObject();
		
		try {
			json.put(Request.KEY_REQUEST_TYPE, Request.getType(this.getClass()));
			json.put(Request.KEY_SESSION_ID, Request.getSessionId());
		}
		catch (JSONException ignored) {}
		
		String request = json.toString();
		String hash = Private.calculateHash(request);
		
		List<NameValuePair> postArgs = new ArrayList<NameValuePair>();		
		postArgs.add(new BasicNameValuePair(Request.POST_REQUEST, request));
		postArgs.add(new BasicNameValuePair(Request.POST_HASH, hash));
		
		HttpPost post = null;
		try { post = HttpHelper.getPost(Request.URL, postArgs); } 
		catch (UnsupportedEncodingException ignored) {}
		
		new HttpPostTask(post, mCallback).execute();
	}	
	
	/** Fields to send to server */
	protected abstract JSONObject 	getRequest();
	/** 
	 * Do background processing (save response). 
	 * Should be <b><u>synchronized</u></b> in implementing classes.<br />
	 * <p>For example:</p>
	 * {@code @Override}<br /> 
	 * <b>synchronized</b> protected void processInBackground(JSONObject response) {<br />
	 *     // code<br />
	 * }<hr />
	 */
	protected abstract void 		processInBackground(JSONObject response);
}
