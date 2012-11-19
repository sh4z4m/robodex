package com.robodex.request;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

public final class HttpHelper {
    private static int CONNECTION_TIMEOUT = 10000;
    private static int SOCKET_TIMEOUT = 10000;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
        return (ni != null && ni.isConnectedOrConnecting());
    }


    public static HttpClient getClient() {
        return getClient(CONNECTION_TIMEOUT, SOCKET_TIMEOUT);
    }

    public static HttpClient getClient(int connectionTimeout, int socketTimeout) {
        HttpParams params = new BasicHttpParams();

        HttpConnectionParams.setConnectionTimeout(params, connectionTimeout);
        HttpConnectionParams.setSoTimeout(params, socketTimeout);

        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.DEFAULT_CONTENT_CHARSET);
        HttpProtocolParams.setUseExpectContinue(params, false);

        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);

        return new DefaultHttpClient(conMgr, params);
    }


    public static HttpPost getPost(String url, List<NameValuePair> entityList) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost(url);
        post.setEntity(new UrlEncodedFormEntity(entityList));
        return post;
    }


    public static final class HttpPostTask extends AsyncTask<Void, Void, Void> {
    	private static final String LOG_TAG = HttpPostTask.class.getSimpleName();

        public static interface Callback {
        	/** Called on background thread */
        	HttpPost onCreateRequest();
        	/** Called on background thread */
            void onResponseReceived(HttpResponse response);
        }

        private final HttpClient   mClient;
        private final Callback     mCallback;

        public HttpPostTask(Callback callback) {
            this(HttpHelper.getClient(), callback);
        }

        public HttpPostTask(HttpClient client, Callback callback) {
            mClient                 = client;
            mCallback               = callback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            if (mClient == null || mCallback == null) return null;

            HttpPost post = mCallback.onCreateRequest();
            HttpResponse response = null;

            try { response = mClient.execute(post); }
            catch (ConnectTimeoutException e) {
            	Log.i(LOG_TAG, "ConnectTimeoutException", e);
            }
            catch (SocketTimeoutException e) {
            	Log.i(LOG_TAG, "SocketTimeoutException", e);
            }
            catch (ClientProtocolException e) {
            	Log.i(LOG_TAG, "ClientProtocolException", e);
            }
            catch (IOException e) {
            	Log.i(LOG_TAG, "IOException", e);
            }
            catch (Exception e) {
            	Log.i(LOG_TAG, "Exception", e);
            }

            if (mCallback != null) mCallback.onResponseReceived(response);

            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            mClient.getConnectionManager().shutdown();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            mClient.getConnectionManager().shutdown();
        }
    }
}
