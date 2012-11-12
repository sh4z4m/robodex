package com.robodex.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import com.robodex.data.DatabaseContract.ListApprovedFlags;
import com.robodex.data.DatabaseContract.Edits;
import com.robodex.data.DatabaseContract.Flag;
import com.robodex.data.DatabaseContract.ListLastEditedByMember;
import com.robodex.data.DatabaseContract.ListLinks;
import com.robodex.data.DatabaseContract.ListLocationsByOrganization;
import com.robodex.data.DatabaseContract.ListMap;
import com.robodex.data.DatabaseContract.Member;
import com.robodex.data.DatabaseContract.ListOrganizations;
import com.robodex.data.DatabaseContract.DetailLocation;
import com.robodex.data.DatabaseContract.ListPendingFlags;
import com.robodex.data.DatabaseContract.ListPeopleBySpecialty;
import com.robodex.data.DatabaseContract.Person;
import com.robodex.data.DatabaseContract.DetailPerson;
import com.robodex.data.DatabaseContract.PersonOrganization;
import com.robodex.data.DatabaseContract.PersonSpecialty;
import com.robodex.data.DatabaseContract.Recent;
import com.robodex.data.DatabaseContract.Requests;
import com.robodex.data.DatabaseContract.ListRoles;
import com.robodex.data.DatabaseContract.SearchAll;
import com.robodex.data.DatabaseContract.SearchApprovedFlags;
import com.robodex.data.DatabaseContract.SearchLinks;
import com.robodex.data.DatabaseContract.SearchLocationsByOrganization;
import com.robodex.data.DatabaseContract.SearchMap;
import com.robodex.data.DatabaseContract.SearchOrganizations;
import com.robodex.data.DatabaseContract.SearchPendingFlags;
import com.robodex.data.DatabaseContract.SearchPeopleBySpecialty;
import com.robodex.data.DatabaseContract.SearchSpecialties;
import com.robodex.data.DatabaseContract.SearchTerms;
import com.robodex.data.DatabaseContract.ListSpecialties;

public class DatabaseContentProvider extends ContentProvider {
    private static final String LOG_TAG = DatabaseContentProvider.class.getSimpleName();

    private DatabaseOpenHelper mDB;

    private static final int APPROVED_FLAGS	 					= 1;
    private static final int EDITS 								= 2;
    private static final int FLAG 								= 3;
    private static final int LAST_EDITED_BY_MEMBER 				= 4;
    private static final int LINK 								= 5;
    private static final int LOCATION_BY_ORGANIZATION 			= 6;
    private static final int MAP_CACHE 							= 7;
    private static final int MEMBER 							= 8;
    private static final int ORGANIZATION 						= 9;
    private static final int ORGANIZATION_LOCATION 				= 10;
    private static final int PENDING_FLAGS 						= 11;
    private static final int PEOPLE_BY_SPECIALTY 				= 12;
    private static final int PERSON 							= 13;
    private static final int PERSON_DETAIL 						= 14;
    private static final int PERSON_ORGANIZATION 				= 15;
    private static final int PERSON_SPECIALTY 					= 16;
    private static final int RECENT 							= 17;
    private static final int REQUESTS 							= 18;
    private static final int ROLE 								= 19;
    private static final int SEARCH_ALL 						= 20;
    private static final int SEARCH_APPROVED_FLAGS 				= 21;
    private static final int SEARCH_LINKS 						= 22;
    private static final int SEARCH_LOCATION_BY_ORGANIZATION	= 23;
    private static final int SEARCH_MAP 						= 24;
    private static final int SEARCH_ORGANIZATIONS 				= 25;
    private static final int SEARCH_PENDING_FLAGS 				= 26;
    private static final int SEARCH_PEOPLE_BY_SPECIALTY 		= 27;
    private static final int SEARCH_SPECIALTIES 				= 28;
    private static final int SEARCH_TERMS						= 29;
    private static final int SPECIALTY		 					= 30;




    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListApprovedFlags.TABLE_NAME, 				APPROVED_FLAGS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Edits.TABLE_NAME, 						EDITS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Flag.TABLE_NAME, 						FLAG);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListLastEditedByMember.TABLE_NAME, 			LAST_EDITED_BY_MEMBER);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListLinks.TABLE_NAME, 						LINK);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListLocationsByOrganization.TABLE_NAME, 		LOCATION_BY_ORGANIZATION);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListMap.TABLE_NAME, 					MAP_CACHE);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Member.TABLE_NAME, 						MEMBER);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListOrganizations.TABLE_NAME, 				ORGANIZATION);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, DetailLocation.TABLE_NAME, 		ORGANIZATION_LOCATION);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListPendingFlags.TABLE_NAME, 				PENDING_FLAGS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListPeopleBySpecialty.TABLE_NAME, 			PEOPLE_BY_SPECIALTY);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Person.TABLE_NAME, 						PERSON);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, DetailPerson.TABLE_NAME, 				PERSON_DETAIL);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, PersonOrganization.TABLE_NAME, 			PERSON_ORGANIZATION);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, PersonSpecialty.TABLE_NAME, 				PERSON_SPECIALTY);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Recent.TABLE_NAME, 						RECENT);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Requests.TABLE_NAME, 					REQUESTS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListRoles.TABLE_NAME, 						ROLE);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchAll.TABLE_NAME, 					SEARCH_ALL);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchApprovedFlags.TABLE_NAME, 			SEARCH_APPROVED_FLAGS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchLinks.TABLE_NAME, 					SEARCH_LINKS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchLocationsByOrganization.TABLE_NAME, SEARCH_LOCATION_BY_ORGANIZATION);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchMap.TABLE_NAME, 					SEARCH_MAP);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchOrganizations.TABLE_NAME, 			SEARCH_ORGANIZATIONS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchPendingFlags.TABLE_NAME, 			SEARCH_PENDING_FLAGS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchPeopleBySpecialty.TABLE_NAME, 		SEARCH_PEOPLE_BY_SPECIALTY);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchSpecialties.TABLE_NAME, 			SEARCH_SPECIALTIES);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchTerms.TABLE_NAME, 					SEARCH_TERMS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListSpecialties.TABLE_NAME, 					SPECIALTY);
    }

    @Override
    public boolean onCreate() {
        mDB = new DatabaseOpenHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
        case APPROVED_FLAGS:					return ListApprovedFlags.CONTENT_TYPE;
        case EDITS: 							return Edits.CONTENT_TYPE;
        case FLAG: 								return Flag.CONTENT_TYPE;
        case LAST_EDITED_BY_MEMBER: 			return ListLastEditedByMember.CONTENT_TYPE;
        case LINK: 								return ListLinks.CONTENT_TYPE;
        case LOCATION_BY_ORGANIZATION: 			return ListLocationsByOrganization.CONTENT_TYPE;
        case MAP_CACHE: 						return ListMap.CONTENT_TYPE;
        case MEMBER: 							return Member.CONTENT_TYPE;
        case ORGANIZATION: 						return ListOrganizations.CONTENT_TYPE;
        case ORGANIZATION_LOCATION: 			return DetailLocation.CONTENT_TYPE;
        case PENDING_FLAGS: 					return ListPendingFlags.CONTENT_TYPE;
        case PEOPLE_BY_SPECIALTY: 				return ListPeopleBySpecialty.CONTENT_TYPE;
        case PERSON: 							return Person.CONTENT_TYPE;
        case PERSON_DETAIL: 					return DetailPerson.CONTENT_TYPE;
        case PERSON_ORGANIZATION: 				return PersonOrganization.CONTENT_TYPE;
        case PERSON_SPECIALTY: 					return PersonSpecialty.CONTENT_TYPE;
        case RECENT: 							return Recent.CONTENT_TYPE;
        case REQUESTS: 							return Requests.CONTENT_TYPE;
        case ROLE: 								return ListRoles.CONTENT_TYPE;
        case SEARCH_ALL: 						return SearchAll.CONTENT_TYPE;
        case SEARCH_APPROVED_FLAGS: 			return SearchApprovedFlags.CONTENT_TYPE;
        case SEARCH_LINKS: 						return SearchLinks.CONTENT_TYPE;
        case SEARCH_LOCATION_BY_ORGANIZATION:	return SearchLocationsByOrganization.CONTENT_TYPE;
        case SEARCH_MAP: 						return SearchMap.CONTENT_TYPE;
        case SEARCH_ORGANIZATIONS: 				return SearchOrganizations.CONTENT_TYPE;
        case SEARCH_PENDING_FLAGS: 				return SearchPendingFlags.CONTENT_TYPE;
        case SEARCH_PEOPLE_BY_SPECIALTY: 		return SearchPeopleBySpecialty.CONTENT_TYPE;
        case SEARCH_SPECIALTIES: 				return SearchSpecialties.CONTENT_TYPE;
        case SEARCH_TERMS: 						return SearchTerms.CONTENT_TYPE;
        case SPECIALTY: 						return ListSpecialties.CONTENT_TYPE;
        default:
            Log.w(LOG_TAG, "getType() - Unknown Uri: " + uri.toString());
            return null;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {

    	SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

    	switch (sUriMatcher.match(uri)) {
        case APPROVED_FLAGS:
        	queryBuilder.setTables(ListApprovedFlags.TABLE_NAME);
        	break;
        case EDITS:
        	queryBuilder.setTables(Edits.TABLE_NAME);
        	break;
        case FLAG:
        	queryBuilder.setTables(Flag.TABLE_NAME);
        	break;
        case LAST_EDITED_BY_MEMBER:
        	queryBuilder.setTables(ListLastEditedByMember.TABLE_NAME);
        	break;
        case LINK:
        	queryBuilder.setTables(ListLinks.TABLE_NAME);
        	break;
        case LOCATION_BY_ORGANIZATION:
        	queryBuilder.setTables(ListLocationsByOrganization.TABLE_NAME);
        	break;
        case MAP_CACHE:
        	queryBuilder.setTables(ListMap.TABLE_NAME);
        	break;
        case MEMBER:
        	queryBuilder.setTables(Member.TABLE_NAME);
        	break;
        case ORGANIZATION:
        	queryBuilder.setTables(ListOrganizations.TABLE_NAME);
        	break;
        case ORGANIZATION_LOCATION:
        	queryBuilder.setTables(DetailLocation.TABLE_NAME);
        	break;
        case PENDING_FLAGS:
        	queryBuilder.setTables(ListPendingFlags.TABLE_NAME);
        	break;
        case PEOPLE_BY_SPECIALTY:
        	queryBuilder.setTables(ListPeopleBySpecialty.TABLE_NAME);
        	break;
        case PERSON:
        	queryBuilder.setTables(Person.TABLE_NAME);
        	break;
        case PERSON_DETAIL:
        	queryBuilder.setTables(DetailPerson.TABLE_NAME);
        	break;
        case PERSON_ORGANIZATION:
        	queryBuilder.setTables(PersonOrganization.TABLE_NAME);
        	break;
        case PERSON_SPECIALTY:
        	queryBuilder.setTables(PersonSpecialty.TABLE_NAME);
        	break;
        case RECENT:
        	queryBuilder.setTables(Recent.TABLE_NAME);
        	break;
        case REQUESTS:
        	queryBuilder.setTables(Requests.TABLE_NAME);
        	break;
        case ROLE:
        	queryBuilder.setTables(ListRoles.TABLE_NAME);
        	break;
        case SEARCH_ALL:
        	queryBuilder.setTables(SearchAll.TABLE_NAME);
        	break;
        case SEARCH_APPROVED_FLAGS:
        	queryBuilder.setTables(SearchApprovedFlags.TABLE_NAME);
        	break;
        case SEARCH_LINKS:
        	queryBuilder.setTables(SearchLinks.TABLE_NAME);
        	break;
        case SEARCH_LOCATION_BY_ORGANIZATION:
        	queryBuilder.setTables(SearchLocationsByOrganization.TABLE_NAME);
        	break;
        case SEARCH_MAP:
        	queryBuilder.setTables(SearchMap.TABLE_NAME);
        	break;
        case SEARCH_ORGANIZATIONS:
        	queryBuilder.setTables(SearchOrganizations.TABLE_NAME);
        	break;
        case SEARCH_PENDING_FLAGS:
        	queryBuilder.setTables(SearchPendingFlags.TABLE_NAME);
        	break;
        case SEARCH_PEOPLE_BY_SPECIALTY:
        	queryBuilder.setTables(SearchPeopleBySpecialty.TABLE_NAME);
        	break;
        case SEARCH_SPECIALTIES:
        	queryBuilder.setTables(SearchSpecialties.TABLE_NAME);
        	break;
        case SEARCH_TERMS:
        	queryBuilder.setTables(SearchTerms.TABLE_NAME);
        	break;
        case SPECIALTY:
        	queryBuilder.setTables(ListSpecialties.TABLE_NAME);
        	break;
        default:
        	Log.w(LOG_TAG, "query() - Unknown Uri: " + uri.toString());
        }


    	Cursor cursor = queryBuilder.query(mDB.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
    	SQLiteDatabase sqlDB = mDB.getWritableDatabase();
        int rowsAffected = 0;

        switch (sUriMatcher.match(uri)) {
        case APPROVED_FLAGS:
        	rowsAffected = sqlDB.delete(ListApprovedFlags.TABLE_NAME, selection, selectionArgs);
        	break;
        case EDITS:
        	rowsAffected = sqlDB.delete(Edits.TABLE_NAME, selection, selectionArgs);
        	break;
        case FLAG:
        	rowsAffected = sqlDB.delete(Flag.TABLE_NAME, selection, selectionArgs);
        	break;
        case LAST_EDITED_BY_MEMBER:
        	rowsAffected = sqlDB.delete(ListLastEditedByMember.TABLE_NAME, selection, selectionArgs);
        	break;
        case LINK:
        	rowsAffected = sqlDB.delete(ListLinks.TABLE_NAME, selection, selectionArgs);
        	break;
        case LOCATION_BY_ORGANIZATION:
        	rowsAffected = sqlDB.delete(ListLocationsByOrganization.TABLE_NAME, selection, selectionArgs);
        	break;
        case MAP_CACHE:
        	rowsAffected = sqlDB.delete(ListMap.TABLE_NAME, selection, selectionArgs);
        	break;
        case MEMBER:
        	rowsAffected = sqlDB.delete(Member.TABLE_NAME, selection, selectionArgs);
        	break;
        case ORGANIZATION:
        	rowsAffected = sqlDB.delete(ListOrganizations.TABLE_NAME, selection, selectionArgs);
        	break;
        case ORGANIZATION_LOCATION:
        	rowsAffected = sqlDB.delete(DetailLocation.TABLE_NAME, selection, selectionArgs);
        	break;
        case PENDING_FLAGS:
        	rowsAffected = sqlDB.delete(ListPendingFlags.TABLE_NAME, selection, selectionArgs);
        	break;
        case PEOPLE_BY_SPECIALTY:
        	rowsAffected = sqlDB.delete(ListPeopleBySpecialty.TABLE_NAME, selection, selectionArgs);
        	break;
        case PERSON:
        	rowsAffected = sqlDB.delete(Person.TABLE_NAME, selection, selectionArgs);
        	break;
        case PERSON_DETAIL:
        	rowsAffected = sqlDB.delete(DetailPerson.TABLE_NAME, selection, selectionArgs);
        	break;
        case PERSON_ORGANIZATION:
        	rowsAffected = sqlDB.delete(PersonOrganization.TABLE_NAME, selection, selectionArgs);
        	break;
        case PERSON_SPECIALTY:
        	rowsAffected = sqlDB.delete(PersonSpecialty.TABLE_NAME, selection, selectionArgs);
        	break;
        case RECENT:
        	rowsAffected = sqlDB.delete(Recent.TABLE_NAME, selection, selectionArgs);
        	break;
        case REQUESTS:
        	rowsAffected = sqlDB.delete(Requests.TABLE_NAME, selection, selectionArgs);
        	break;
        case ROLE:
        	rowsAffected = sqlDB.delete(ListRoles.TABLE_NAME, selection, selectionArgs);
        	break;
        case SEARCH_ALL:
        	rowsAffected = sqlDB.delete(SearchAll.TABLE_NAME, selection, selectionArgs);
        	break;
        case SEARCH_APPROVED_FLAGS:
        	rowsAffected = sqlDB.delete(SearchApprovedFlags.TABLE_NAME, selection, selectionArgs);
        	break;
        case SEARCH_LINKS:
        	rowsAffected = sqlDB.delete(SearchLinks.TABLE_NAME, selection, selectionArgs);
        	break;
        case SEARCH_LOCATION_BY_ORGANIZATION:
        	rowsAffected = sqlDB.delete(SearchLocationsByOrganization.TABLE_NAME, selection, selectionArgs);
        	break;
        case SEARCH_MAP:
        	rowsAffected = sqlDB.delete(SearchMap.TABLE_NAME, selection, selectionArgs);
        	break;
        case SEARCH_ORGANIZATIONS:
        	rowsAffected = sqlDB.delete(SearchOrganizations.TABLE_NAME, selection, selectionArgs);
        	break;
        case SEARCH_PENDING_FLAGS:
        	rowsAffected = sqlDB.delete(SearchPendingFlags.TABLE_NAME, selection, selectionArgs);
        	break;
        case SEARCH_PEOPLE_BY_SPECIALTY:
        	rowsAffected = sqlDB.delete(SearchPeopleBySpecialty.TABLE_NAME, selection, selectionArgs);
        	break;
        case SEARCH_SPECIALTIES:
        	rowsAffected = sqlDB.delete(SearchSpecialties.TABLE_NAME, selection, selectionArgs);
        	break;
        case SEARCH_TERMS:
        	rowsAffected = sqlDB.delete(SearchTerms.TABLE_NAME, selection, selectionArgs);
        	break;
        case SPECIALTY:
        	rowsAffected = sqlDB.delete(ListSpecialties.TABLE_NAME, selection, selectionArgs);
        	break;
        default:
        	Log.w(LOG_TAG, "delete() - Unknown Uri: " + uri.toString());
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowsAffected;
    }



    @Override
    public Uri insert(Uri uri, ContentValues values) {
    	SQLiteDatabase sqlDB = mDB.getWritableDatabase();
    	long newID = 0;

    	try {
	    	switch (sUriMatcher.match(uri)) {
	        case APPROVED_FLAGS:
	        	newID = sqlDB.insertOrThrow(ListApprovedFlags.TABLE_NAME, null, values);
	        	break;
	        case EDITS:
	        	newID = sqlDB.insertOrThrow(Edits.TABLE_NAME, null, values);
	        	break;
	        case FLAG:
	        	newID = sqlDB.insertOrThrow(Flag.TABLE_NAME, null, values);
	        	break;
	        case LAST_EDITED_BY_MEMBER:
	        	newID = sqlDB.insertOrThrow(ListLastEditedByMember.TABLE_NAME, null, values);
	        	break;
	        case LINK:
	        	newID = sqlDB.insertOrThrow(ListLinks.TABLE_NAME, null, values);
	        	break;
	        case LOCATION_BY_ORGANIZATION:
	        	newID = sqlDB.insertOrThrow(ListLocationsByOrganization.TABLE_NAME, null, values);
	        	break;
	        case MAP_CACHE:
	        	newID = sqlDB.insertOrThrow(ListMap.TABLE_NAME, null, values);
	        	break;
	        case MEMBER:
	        	newID = sqlDB.insertOrThrow(Member.TABLE_NAME, null, values);
	        	break;
	        case ORGANIZATION:
	        	newID = sqlDB.insertOrThrow(ListOrganizations.TABLE_NAME, null, values);
	        	break;
	        case ORGANIZATION_LOCATION:
	        	newID = sqlDB.insertOrThrow(DetailLocation.TABLE_NAME, null, values);
	        	break;
	        case PENDING_FLAGS:
	        	newID = sqlDB.insertOrThrow(ListPendingFlags.TABLE_NAME, null, values);
	        	break;
	        case PEOPLE_BY_SPECIALTY:
	        	newID = sqlDB.insertOrThrow(ListPeopleBySpecialty.TABLE_NAME, null, values);
	        	break;
	        case PERSON:
	        	newID = sqlDB.insertOrThrow(Person.TABLE_NAME, null, values);
	        	break;
	        case PERSON_DETAIL:
	        	newID = sqlDB.insertOrThrow(DetailPerson.TABLE_NAME, null, values);
	        	break;
	        case PERSON_ORGANIZATION:
	        	newID = sqlDB.insertOrThrow(PersonOrganization.TABLE_NAME, null, values);
	        	break;
	        case PERSON_SPECIALTY:
	        	newID = sqlDB.insertOrThrow(PersonSpecialty.TABLE_NAME, null, values);
	        	break;
	        case RECENT:
	        	newID = sqlDB.insertOrThrow(Recent.TABLE_NAME, null, values);
	        	break;
	        case REQUESTS:
	        	newID = sqlDB.insertOrThrow(Requests.TABLE_NAME, null, values);
	        	break;
	        case ROLE:
	        	newID = sqlDB.insertOrThrow(ListRoles.TABLE_NAME, null, values);
	        	break;
	        case SEARCH_ALL:
	        	newID = sqlDB.insertOrThrow(SearchAll.TABLE_NAME, null, values);
	        	break;
	        case SEARCH_APPROVED_FLAGS:
	        	newID = sqlDB.insertOrThrow(SearchApprovedFlags.TABLE_NAME, null, values);
	        	break;
	        case SEARCH_LINKS:
	        	newID = sqlDB.insertOrThrow(SearchLinks.TABLE_NAME, null, values);
	        	break;
	        case SEARCH_LOCATION_BY_ORGANIZATION:
	        	newID = sqlDB.insertOrThrow(SearchLocationsByOrganization.TABLE_NAME, null, values);
	        	break;
	        case SEARCH_MAP:
	        	newID = sqlDB.insertOrThrow(SearchMap.TABLE_NAME, null, values);
	        	break;
	        case SEARCH_ORGANIZATIONS:
	        	newID = sqlDB.insertOrThrow(SearchOrganizations.TABLE_NAME, null, values);
	        	break;
	        case SEARCH_PENDING_FLAGS:
	        	newID = sqlDB.insertOrThrow(SearchPendingFlags.TABLE_NAME, null, values);
	        	break;
	        case SEARCH_PEOPLE_BY_SPECIALTY:
	        	newID = sqlDB.insertOrThrow(SearchPeopleBySpecialty.TABLE_NAME, null, values);
	        	break;
	        case SEARCH_SPECIALTIES:
	        	newID = sqlDB.insertOrThrow(SearchSpecialties.TABLE_NAME, null, values);
	        	break;
	        case SEARCH_TERMS:
	        	newID = sqlDB.insertOrThrow(SearchTerms.TABLE_NAME, null, values);
	        	break;
	        case SPECIALTY:
	        	newID = sqlDB.insertOrThrow(ListSpecialties.TABLE_NAME, null, values);
	        	break;
	        default:
	        	Log.w(LOG_TAG, "insert() - Unknown Uri: " + uri.toString());
	        }
    	}
    	catch (SQLException e) {
            Log.w(LOG_TAG, "Error inserting Uri: " + uri.toString(), e);
        }

    	if (newID > 0) {
            Uri newUri = ContentUris.withAppendedId(uri, newID);
            getContext().getContentResolver().notifyChange(uri, null);
            return newUri;
        }
    	else {
    		Log.e(LOG_TAG, "Failed inserting Uri: " + uri.toString());
        }

        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
    	SQLiteDatabase sqlDB = mDB.getWritableDatabase();
        int rowsAffected = 0;

        switch (sUriMatcher.match(uri)) {
        case APPROVED_FLAGS:
        	rowsAffected = sqlDB.update(ListApprovedFlags.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case EDITS:
        	rowsAffected = sqlDB.update(Edits.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case FLAG:
        	rowsAffected = sqlDB.update(Flag.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case LAST_EDITED_BY_MEMBER:
        	rowsAffected = sqlDB.update(ListLastEditedByMember.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case LINK:
        	rowsAffected = sqlDB.update(ListLinks.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case LOCATION_BY_ORGANIZATION:
        	rowsAffected = sqlDB.update(ListLocationsByOrganization.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case MAP_CACHE:
        	rowsAffected = sqlDB.update(ListMap.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case MEMBER:
        	rowsAffected = sqlDB.update(Member.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case ORGANIZATION:
        	rowsAffected = sqlDB.update(ListOrganizations.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case ORGANIZATION_LOCATION:
        	rowsAffected = sqlDB.update(DetailLocation.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case PENDING_FLAGS:
        	rowsAffected = sqlDB.update(ListPendingFlags.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case PEOPLE_BY_SPECIALTY:
        	rowsAffected = sqlDB.update(ListPeopleBySpecialty.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case PERSON:
        	rowsAffected = sqlDB.update(Person.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case PERSON_DETAIL:
        	rowsAffected = sqlDB.update(DetailPerson.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case PERSON_ORGANIZATION:
        	rowsAffected = sqlDB.update(PersonOrganization.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case PERSON_SPECIALTY:
        	rowsAffected = sqlDB.update(PersonSpecialty.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case RECENT:
        	rowsAffected = sqlDB.update(Recent.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case REQUESTS:
        	rowsAffected = sqlDB.update(Requests.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case ROLE:
        	rowsAffected = sqlDB.update(ListRoles.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case SEARCH_ALL:
        	rowsAffected = sqlDB.update(SearchAll.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case SEARCH_APPROVED_FLAGS:
        	rowsAffected = sqlDB.update(SearchApprovedFlags.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case SEARCH_LINKS:
        	rowsAffected = sqlDB.update(SearchLinks.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case SEARCH_LOCATION_BY_ORGANIZATION:
        	rowsAffected = sqlDB.update(SearchLocationsByOrganization.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case SEARCH_MAP:
        	rowsAffected = sqlDB.update(SearchMap.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case SEARCH_ORGANIZATIONS:
        	rowsAffected = sqlDB.update(SearchOrganizations.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case SEARCH_PENDING_FLAGS:
        	rowsAffected = sqlDB.update(SearchPendingFlags.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case SEARCH_PEOPLE_BY_SPECIALTY:
        	rowsAffected = sqlDB.update(SearchPeopleBySpecialty.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case SEARCH_SPECIALTIES:
        	rowsAffected = sqlDB.update(SearchSpecialties.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case SEARCH_TERMS:
        	rowsAffected = sqlDB.update(SearchTerms.TABLE_NAME, values, selection, selectionArgs);
        	break;
        case SPECIALTY:
        	rowsAffected = sqlDB.update(ListSpecialties.TABLE_NAME, values, selection, selectionArgs);
        	break;
        default:
        	Log.w(LOG_TAG, "update() - Unknown Uri: " + uri.toString());
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowsAffected;
    }
}
