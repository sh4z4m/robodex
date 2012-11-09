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

import android.util.Log;

import com.robodex.HttpHelper;
import com.robodex.HttpHelper.HttpPostTask;
import com.robodex.HttpHelper.HttpPostTask.Callback;
import com.robodex.Private;
import com.robodex.Robodex;

public abstract class BaseRequest {
    private static final String LOG_TAG = BaseRequest.class.getSimpleName();

    private static final String URL		= "http://businesshours.net/oatia/json/test.php";

    private final Callback mCallback;

    protected BaseRequest() {
        mCallback = new Callback() {
            @Override
            public void onPostExecuteBackgroundProcessing(HttpPostTask task) {
                String          response        = null;
                String          hash            = null;
                JSONObject      json            = null;

                int             size            = task.getResponseLines().size();

                if (size >= 2) {
                    response        = task.getResponseLines().get(0);
                    hash            = task.getResponseLines().get(1);
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
                    json     = new JSONObject(response);
                }
                catch (UnsupportedEncodingException e) {
                    Log.e(LOG_TAG, "Failed URL-decoding response.", e);
                }
                catch (JSONException e) {
                    Log.e(LOG_TAG, "Failed converting response to JSONObject.", e);
                }

                if (json != null) {
                    int     code = Fields.ERROR_CODE_OK;
                    String  msg  = null;

                    try {
                        code = json.getInt(		Fields.RESPONSE_CODE);
                        msg  = json.getString(	Fields.RESPONSE_MESSAGE);
                    }
                    catch (JSONException e) {
                        Log.e(LOG_TAG, "Failed parsing response code or message.", e);
                    }

                    if (code != Fields.ERROR_CODE_OK) {
                        Log.e(LOG_TAG, "Error " + code + ": " + msg);
                    }


                    try {
                    	Map<String, String> rowToSave;
                    	List<Map<String, String>> resultsList = new ArrayList<Map<String, String>>();

                        JSONArray results = json.getJSONArray(Fields.RESULTS);
                        JSONObject rowFromResponse;

                        String field;
                        String value;

                        for (int i = 0; i < results.length(); ++i) {
                            rowFromResponse = results.getJSONObject(i);
                            rowToSave = new HashMap<String, String>();

                            Iterator keys = rowFromResponse.keys();

                            while (keys.hasNext()) {
                            	field = (String) keys.next();
                            	value = rowFromResponse.getString(field);
                            	rowToSave.put(field, value);
                            }

                            resultsList.add(rowToSave);
                        }

                        processInBackground(resultsList);
                    }
                    catch (JSONException e) {
                        Log.e(LOG_TAG, "Error creating results list.", e);
                    }
                }
            }

            @Override
            public void onPostExecuteForegroundProcessing(HttpPostTask task) {
                // blank
            }

			@Override
			public void onPreExecuteForegroundProcessing(HttpPostTask task) {
				// blank
			}

			@Override
			public void onPreExecuteBackgroundProcessing(HttpPostTask task) {
				// blank
			}
        };
    }

    public final void execute() {
    	Map<String, String> request = new HashMap<String, String>();
        JSONObject json = new JSONObject();

        // Child classes populate the request object passed to them.
        populateRequest(request);

        try {
        	json.put(Fields.REQUEST_TYPE, Fields.getRequestType(this.getClass()));
            json.put(Fields.SESSION_ID, Fields.getSessionId());

	        if (request != null) {
		        for (Entry<String, String> pair : request.entrySet()) {
		        	json.put(pair.getKey(), pair.getValue());
		        }
	        }
        }
        catch (JSONException e) {
    		Log.e(LOG_TAG, "Error encoding request.", e);
		}

        String requestString = json.toString();
        String hash = Private.calculateHash(requestString);

        List<NameValuePair> postArgs = new ArrayList<NameValuePair>();
        postArgs.add(new BasicNameValuePair(Fields.REQUEST, requestString));
        postArgs.add(new BasicNameValuePair(Fields.HASH, hash));

        HttpPost post = null;
        try { post = HttpHelper.getPost(URL, postArgs); }
        catch (UnsupportedEncodingException e) {
        	Log.e(LOG_TAG, "Error creating HTTP POST.", e);
        }

        new HttpPostTask(post, mCallback).execute();
    }

    /** Fields to send to server */
    protected abstract void	populateRequest(Map<String, String> request);

    /** Save the results of the request */
    protected abstract void	processInBackground(List<Map<String, String>> results);
}
