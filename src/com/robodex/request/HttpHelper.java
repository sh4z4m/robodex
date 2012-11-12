package com.robodex.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
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
        public static interface Callback {
        	/** Called on UI thread */
        	void onPreExecuteForegroundProcessing(HttpPostTask task);
        	/** Called on background thread */
        	void onPreExecuteBackgroundProcessing(HttpPostTask task);
        	/** Called on background thread */
            void onPostExecuteBackgroundProcessing(HttpPostTask task);
            /** Called on UI thread */
            void onPostExecuteForegroundProcessing(HttpPostTask task);
        }

        private final HttpClient   mClient;
        private final HttpPost     mPost;
        private final Callback     mCallback;
        private       HttpResponse mResponse;
        private       List<String> mResponseLines;
        private       Throwable    mConnectionError;
        private       Throwable    mParseError;


        public synchronized HttpResponse        getResponse() {         return mResponse; }
        public synchronized List<String>        getResponseLines() {    return mResponseLines; }
        public synchronized Throwable           getConnectionError() {  return mConnectionError; }
        public synchronized Throwable           getParseError() {       return mParseError; }


        public HttpPostTask(HttpPost post, Callback callback) {
            this(HttpHelper.getClient(), post, callback);
        }

        public HttpPostTask(HttpClient client, HttpPost post, Callback callback) {
            mClient                 = client;
            mPost                   = post;
            mCallback               = callback;
            mResponse               = null;
            mResponseLines          = new ArrayList<String>();
            mConnectionError        = null;
            mParseError             = null;
        }

        @Override
        protected void onPreExecute() {
        	super.onPreExecute();
        	if (mCallback != null) mCallback.onPreExecuteForegroundProcessing(this);
        }

        @Override
        protected Void doInBackground(Void... params) {
            if (mClient == null || mPost == null) return null;

            if (mCallback != null) mCallback.onPreExecuteBackgroundProcessing(this);

            try { mResponse = mClient.execute(mPost); }
            catch (ConnectTimeoutException e) { mConnectionError = e; }
            catch (SocketTimeoutException e) {  mConnectionError = e; }
            catch (ClientProtocolException e) { mConnectionError = e; }
            catch (IOException e) {             mConnectionError = e; }
            catch (Exception e) {               mConnectionError = e; }

            parseResponseLines();

            if (mCallback != null) mCallback.onPostExecuteBackgroundProcessing(this);

            return null;
        }

        private void parseResponseLines() {
            if (mResponse == null) return;

            HttpEntity entity = mResponse.getEntity();
            if (entity == null) return;

            InputStream stream = null;
            BufferedReader reader = null;
            String line = null;

            // Not a repeatable entity, only one call to getContent() allowed
            try {
                stream = entity.getContent();
                reader = new BufferedReader(new InputStreamReader(stream,"iso-8859-1"), 8);
                while ((line = reader.readLine()) != null) {
                    mResponseLines.add(line);
                }
                reader.close();
                stream.close();
            }
            catch (IllegalStateException e) { 	mParseError = e; }
            catch (IOException e) {             mParseError = e; }
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            mClient.getConnectionManager().shutdown();
            if (mCallback != null) mCallback.onPostExecuteForegroundProcessing(this);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            mClient.getConnectionManager().shutdown();
        }
    }
}
