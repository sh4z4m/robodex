package com.robodex.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import com.robodex.HttpHelper;
import com.robodex.HttpHelper.HttpPostTask;
import com.robodex.HttpHelper.HttpPostTask.Callback;
import com.robodex.Private;
import com.robodex.Robodex;
import com.robodex.data.DatabaseContract;
import com.robodex.data.DatabaseContract.CheckIn;
import com.robodex.data.DatabaseContract.ResponseCache;
import com.robodex.request.ServerContract.RequestField;
import com.robodex.request.ServerContract.RequestType;
import com.robodex.request.ServerContract.ResponseCode;
import com.robodex.request.ServerContract.ResponseField;

// TODO EditRequest abstract subclass?

public abstract class BaseRequest {
    private static final String LOG_TAG = BaseRequest.class.getSimpleName();

    private static final String URL		= "http://businesshours.net/oatia/android_listener.php";
//    private static final String URL		= "http://businesshours.net/oatia/json/test.php";

    public static final int RESPONSE_CODE_NOT_GIVEN = -1;
    public static final int RESPONSE_CODE_UNKNOWN = -2;

    private final Callback mCallback;

    private int mResponseCode;


    protected BaseRequest() {
    	this(null);
    }

    protected BaseRequest(final HttpPostTask.Callback callback) {
    	mResponseCode = RESPONSE_CODE_UNKNOWN;

        mCallback = new HttpPostTask.Callback() {
        	@Override
			public void onPreExecuteForegroundProcessing(HttpPostTask task) {
				if (callback != null) callback.onPreExecuteForegroundProcessing(task);
			}

        	@Override
			public void onPreExecuteBackgroundProcessing(HttpPostTask task) {
				if (callback != null) callback.onPreExecuteBackgroundProcessing(task);
			}

        	@Override
            public void onPostExecuteBackgroundProcessing(HttpPostTask task) {
        		// Process the response
        		processResponse(task);

        		if (callback != null) callback.onPostExecuteBackgroundProcessing(task);
            }

            @Override
            public void onPostExecuteForegroundProcessing(HttpPostTask task) {
            	if (callback != null) callback.onPostExecuteForegroundProcessing(task);
            }
        };
    }

    public final void execute() {
    	Map<String, String> requestMap = new HashMap<String, String>();

        // Child classes populate the request object passed to them.
        populateRequest(requestMap);

        String requestString = encodeRequest(requestMap);
    	HttpPost post = createHttpPost(requestString);
        new HttpPostTask(post, mCallback).execute();
    }

    private String encodeRequest(Map<String, String> request) {
    	JSONObject json = new JSONObject();

    	try {
	    	json.put(RequestField.REQUEST_TYPE, getRequestType());
	        json.put(RequestField.AUTH_KEY, 	getAuthKey());

	        if (request != null) {
		        for (Entry<String, String> pair : request.entrySet()) {
		        	json.put(pair.getKey(), pair.getValue());
		        }
	        }
    	}
    	catch (JSONException e) {
    		Log.e(LOG_TAG, "Error encoding request.", e);
		}

        return json.toString();
    }

    private HttpPost createHttpPost(String request) {
        String hash = Private.calculateHash(request);

        List<NameValuePair> postArgs = new ArrayList<NameValuePair>();
        postArgs.add(new BasicNameValuePair(RequestField.REQUEST, request));
        postArgs.add(new BasicNameValuePair(RequestField.HASH, 	hash));

        HttpPost post = null;
        try { post = HttpHelper.getPost(URL, postArgs); }
        catch (UnsupportedEncodingException e) {
        	Log.e(LOG_TAG, "Error creating HTTP POST.", e);
        }

        return post;
    }


    private void processResponse(HttpPostTask task) {

    	JSONObject response = decodeResponse(task);
    	mResponseCode = handleResponseCodes(response);
    	JSONArray results = getResponseResults(response);
    	processResponseResults(results);
    }


    private JSONObject decodeResponse(HttpPostTask task) {
    	String 		responseString	= null;
        String 		hash        	= null;
        int			size        	= task.getResponseLines().size();
        JSONObject	responseJson	= null;

        if (size >= 2) {
            responseString 	= task.getResponseLines().get(0);
            hash            = task.getResponseLines().get(1);
        }
        else {
            Throwable t = task.getConnectionError();
            if (t == null) t = task.getParseError();
            Log.e(LOG_TAG, "Response and hash are not defined.", t);

            return null;
        }


        if (!hash.equals(Private.calculateHash(responseString))) {
            Log.e(LOG_TAG, "Failed hash check.");

            return null;
        }


        try {
            responseString 	= URLDecoder.decode(responseString, "UTF-8");
            responseJson 	= new JSONObject(responseString);
        }
        catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "Failed URL-decoding response.", e);
        }
        catch (JSONException e) {
            Log.e(LOG_TAG, "Failed converting response to JSONObject.", e);
        }

        return responseJson;
    }


    private int handleResponseCodes(JSONObject response) {
    	if (response == null) return RESPONSE_CODE_NOT_GIVEN;

    	int     code = ResponseCode.OK;
        String  msg  = null;

        try {
            code = response.getInt(		ResponseField.RESPONSE_CODE);
            msg  = response.getString(	ResponseField.RESPONSE_MESSAGE);
        }
        catch (JSONException e) {
            Log.e(LOG_TAG, "Failed parsing response code or message.", e);
        }

        if (code != ResponseCode.OK) {
            Log.e(LOG_TAG, "Error " + code + ": " + msg);
        }

        return code;
    }

    private JSONArray getResponseResults(JSONObject response) {
    	if (response == null) return null;

    	JSONArray results = null;
    	try {
    		results = response.getJSONArray(ResponseField.RESULTS);
    	}
    	catch (JSONException e) {
            Log.e(LOG_TAG, "Error getting results from response.");
        }

    	return results;
    }

    private void processResponseResults(JSONArray results) {
    	if (results == null) return;

    	JSONObject resultRow = null;
    	Map<String, String> rowToSave = null;

    	for (int i = 0; i < results.length(); ++i) {
    		try {
    			resultRow = results.getJSONObject(i);
    			rowToSave = convertResultRow(resultRow);
    			processConvertedResultRow(rowToSave);
    		}
    		catch(JSONException e) {
    			Log.e(LOG_TAG, "Error processing result row " + i);
    		}
    	}
    }

    private Map<String, String> convertResultRow(JSONObject resultRow) {
    	Map<String, String> rowToSave = new HashMap<String, String>();

    	Iterator keys = resultRow.keys();
    	String key;
        while (keys.hasNext()) {
        	key = (String) keys.next();
        	try {
				rowToSave.put(key, resultRow.getString(key));
			}
        	catch (JSONException e) {
        		Log.e(LOG_TAG, "Cannot parse value of result field '" + key + "'", e);
			}
        }

        return rowToSave;
    }

	private void processConvertedResultRow(Map<String, String> rowToSave) {
		ContentValues dbRow = processRowForInsertion(rowToSave);

        try {
        	Robodex.sAppContext.getContentResolver().insert(
        		getContentUri(), dbRow);
        }
        catch (Throwable t) {
        	Log.e(LOG_TAG, "Error saving row to database.", t);
        }

    }






    private String getAuthKey() {
    	// TODO
    	return "";
    }

    public synchronized int getResponseCode() {
    	return mResponseCode;
    }


    /** The request being implemented as defined in the ServerContract.RequestType class. */
	protected abstract String 			getRequestType();

	/** The CONTENT_URI for the database table related to the request */
	protected abstract Uri 				getContentUri();

    /** Fields to send to server as defined by the ServerContract.RequestField class.*/
    protected abstract void				populateRequest(Map<String, String> request);

    /** Parse a row from the results so that it can be stored in the database */
    protected abstract ContentValues	processRowForInsertion(Map<String, String> rowFromResponse);





}
