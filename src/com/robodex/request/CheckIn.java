package com.robodex.request;

import java.util.HashMap;
import java.util.Map;

import android.content.ContentValues;
import android.location.Location;
import android.location.LocationManager;

import com.robodex.Robodex;
import com.robodex.data.DatabaseContract;
import com.robodex.location.LocationUpdater;
import com.robodex.location.LocationUpdater.LocationUpdateListener;
import com.robodex.request.ServerContract.RequestField;

public final class CheckIn extends BaseRequest {
	private static final long GPS_TIMEOUT = 1000 * 10;
	private long mStartTime;
	private LocationUpdater mLocationUpdater;

	@Override
	protected void prepareRequest() {
		mLocationUpdater = new LocationUpdater(Robodex.sAppContext, new LocationUpdateListener() {
			@Override
			public void onLocationUpdated(Location location) {
				if (location.getProvider().equals(LocationManager.GPS_PROVIDER)
						|| System.currentTimeMillis() - mStartTime > GPS_TIMEOUT) {
					acceptLocation(location);
				}
			}
		});

		mStartTime = System.currentTimeMillis();
		mLocationUpdater.startListeningToGps();
		mLocationUpdater.startListeningToNetwork();
	}

	private void acceptLocation(Location location) {
		mLocationUpdater.stopListeningToAll();
		Map<String, String> request = new HashMap<String, String>();
		request.put(RequestField.LATITUDE, String.valueOf(location.getLatitude()));
		request.put(RequestField.LONGITUDE, String.valueOf(location.getLongitude()));
		request.put(RequestField.LOCATION_TIME, String.valueOf(location.getTime()));
		request.put(RequestField.LOCATION_ACCURACY, String.valueOf(location.getAccuracy()));
		executeRequest(request);
	}


	@Override
	protected ContentValues processRowForInsertion(Map<String, String> rowFromResponse) {
		ContentValues rowToInsert = new ContentValues();
		rowToInsert.put(DatabaseContract.CheckIn.COL_TIMESTAMP, rowFromResponse.get(RequestField.LOCATION_TIME));
		rowToInsert.put(DatabaseContract.CheckIn.COL_LATITUDE, rowFromResponse.get(RequestField.LATITUDE));
		rowToInsert.put(DatabaseContract.CheckIn.COL_LONGITUDE, rowFromResponse.get(RequestField.LONGITUDE));
		rowToInsert.put(DatabaseContract.CheckIn.COL_ACCURACY, rowFromResponse.get(RequestField.LOCATION_ACCURACY));
        return rowToInsert;
	}


}
