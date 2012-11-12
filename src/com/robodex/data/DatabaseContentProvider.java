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

import com.robodex.data.DatabaseContract.CheckIn;
import com.robodex.data.DatabaseContract.CreateFlag;
import com.robodex.data.DatabaseContract.CreateLink;
import com.robodex.data.DatabaseContract.CreateLocation;
import com.robodex.data.DatabaseContract.CreateOrganization;
import com.robodex.data.DatabaseContract.CreatePerson;
import com.robodex.data.DatabaseContract.CreateSpecialty;
import com.robodex.data.DatabaseContract.DetailLocation;
import com.robodex.data.DatabaseContract.DetailPerson;
import com.robodex.data.DatabaseContract.EditFlag;
import com.robodex.data.DatabaseContract.EditLink;
import com.robodex.data.DatabaseContract.EditLocation;
import com.robodex.data.DatabaseContract.EditOrganization;
import com.robodex.data.DatabaseContract.EditPerson;
import com.robodex.data.DatabaseContract.EditRole;
import com.robodex.data.DatabaseContract.EditSpecialty;
import com.robodex.data.DatabaseContract.ListApprovedFlags;
import com.robodex.data.DatabaseContract.ListLastEditedByMember;
import com.robodex.data.DatabaseContract.ListLinks;
import com.robodex.data.DatabaseContract.ListLocationsByOrganization;
import com.robodex.data.DatabaseContract.ListMap;
import com.robodex.data.DatabaseContract.ListOrganizations;
import com.robodex.data.DatabaseContract.ListPendingFlags;
import com.robodex.data.DatabaseContract.ListPeopleBySpecialty;
import com.robodex.data.DatabaseContract.ListRoles;
import com.robodex.data.DatabaseContract.ListSpecialties;
import com.robodex.data.DatabaseContract.Login;
import com.robodex.data.DatabaseContract.Recent;
import com.robodex.data.DatabaseContract.Requests;
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

public class DatabaseContentProvider extends ContentProvider {
    private static final String LOG_TAG = DatabaseContentProvider.class.getSimpleName();

    private DatabaseOpenHelper mDB;

    private static final int CHECK_IN	 						= 1;
    private static final int CREATE_FLAG	 					= 2;
    private static final int CREATE_LINK	 					= 3;
    private static final int CREATE_LOCATION	 				= 4;
    private static final int CREATE_ORGANIZATION	 			= 5;
    private static final int CREATE_PERSON	 					= 6;
    private static final int CREATE_SPECIALTY	 				= 7;
    private static final int DETAIL_LOCATION	 				= 8;
    private static final int DETAIL_PERSON	 					= 9;
    private static final int EDIT_FLAG	 						= 10;
    private static final int EDIT_LINK	 						= 11;
    private static final int EDIT_LOCATION	 					= 12;
    private static final int EDIT_ORGANIZATION	 				= 13;
    private static final int EDIT_PERSON	 					= 14;
    private static final int EDIT_ROLE	 						= 15;
    private static final int EDIT_SPECIALTY	 					= 16;
    private static final int LIST_APPROVED_FLAGS 				= 17;
    private static final int LIST_LAST_EDITED_BY_MEMBER 		= 18;
    private static final int LIST_LINKS 						= 19;
    private static final int LIST_LOCATIONS_BY_ORGANIZATION 	= 20;
    private static final int LIST_MAP 							= 21;
    private static final int LIST_ORGANIZATIONS 				= 22;
    private static final int LIST_PENDING_FLAGS 				= 23;
    private static final int LIST_PEOPLE_BY_SPECIALTY 			= 24;
    private static final int LIST_ROLES 						= 25;
    private static final int LIST_SPECIALTIES 					= 26;
    private static final int LOGIN 								= 27;
    private static final int RECENT 							= 28;
    private static final int REQUESTS 							= 29;
    private static final int SEARCH_ALL 						= 30;
    private static final int SEARCH_APPROVED_FLAGS 				= 31;
    private static final int SEARCH_LINKS 						= 32;
    private static final int SEARCH_LOCATIONS_BY_ORGANIZATION	= 33;
    private static final int SEARCH_MAP 						= 34;
    private static final int SEARCH_ORGANIZATIONS 				= 35;
    private static final int SEARCH_PENDING_FLAGS 				= 36;
    private static final int SEARCH_PEOPLE_BY_SPECIALTY 		= 37;
    private static final int SEARCH_SPECIALTIES 				= 38;
    private static final int SEARCH_TERMS 						= 39;




    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
    	sUriMatcher.addURI(DatabaseContract.AUTHORITY, CheckIn.TABLE_NAME, 							CHECK_IN);
    	sUriMatcher.addURI(DatabaseContract.AUTHORITY, CreateFlag.TABLE_NAME, 						CREATE_FLAG);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, CreateLink.TABLE_NAME, 						CREATE_LINK);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, CreateLocation.TABLE_NAME, 					CREATE_LOCATION);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, CreateOrganization.TABLE_NAME, 				CREATE_ORGANIZATION);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, CreatePerson.TABLE_NAME, 					CREATE_PERSON);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, CreateSpecialty.TABLE_NAME, 					CREATE_SPECIALTY);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, DetailLocation.TABLE_NAME, 					DETAIL_LOCATION);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, DetailPerson.TABLE_NAME, 					DETAIL_PERSON);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, EditFlag.TABLE_NAME, 						EDIT_FLAG);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, EditLink.TABLE_NAME, 						EDIT_LINK);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, EditLocation.TABLE_NAME, 					EDIT_LOCATION);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, EditOrganization.TABLE_NAME, 				EDIT_ORGANIZATION);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, EditPerson.TABLE_NAME, 						EDIT_PERSON);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, EditRole.TABLE_NAME, 						EDIT_ROLE);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, EditSpecialty.TABLE_NAME, 					EDIT_SPECIALTY);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListApprovedFlags.TABLE_NAME, 				LIST_APPROVED_FLAGS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListLastEditedByMember.TABLE_NAME, 			LIST_LAST_EDITED_BY_MEMBER);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListLinks.TABLE_NAME, 						LIST_LINKS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListLocationsByOrganization.TABLE_NAME, 		LIST_LOCATIONS_BY_ORGANIZATION);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListMap.TABLE_NAME, 							LIST_MAP);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListOrganizations.TABLE_NAME, 				LIST_ORGANIZATIONS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListPendingFlags.TABLE_NAME, 				LIST_PENDING_FLAGS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListPeopleBySpecialty.TABLE_NAME, 			LIST_PEOPLE_BY_SPECIALTY);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListRoles.TABLE_NAME, 						LIST_ROLES);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, ListSpecialties.TABLE_NAME, 					LIST_SPECIALTIES);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Login.TABLE_NAME, 							LOGIN);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Recent.TABLE_NAME, 							RECENT);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Requests.TABLE_NAME, 						REQUESTS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchAll.TABLE_NAME, 						SEARCH_ALL);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchApprovedFlags.TABLE_NAME, 				SEARCH_APPROVED_FLAGS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchLinks.TABLE_NAME, 						SEARCH_LINKS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchLocationsByOrganization.TABLE_NAME,	SEARCH_LOCATIONS_BY_ORGANIZATION);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchMap.TABLE_NAME, 						SEARCH_MAP);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchOrganizations.TABLE_NAME, 				SEARCH_ORGANIZATIONS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchPendingFlags.TABLE_NAME, 				SEARCH_PENDING_FLAGS);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchPeopleBySpecialty.TABLE_NAME, 			SEARCH_PEOPLE_BY_SPECIALTY);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchSpecialties.TABLE_NAME, 				SEARCH_SPECIALTIES);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SearchTerms.TABLE_NAME, 						SEARCH_TERMS);

    }

    @Override
    public boolean onCreate() {
        mDB = new DatabaseOpenHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
        case CHECK_IN:							return CheckIn.CONTENT_TYPE;
        case CREATE_FLAG:						return CreateFlag.CONTENT_TYPE;
        case CREATE_LINK:						return CreateLink.CONTENT_TYPE;
        case CREATE_LOCATION:					return CreateLocation.CONTENT_TYPE;
        case CREATE_ORGANIZATION:				return CreateOrganization.CONTENT_TYPE;
        case CREATE_PERSON:						return CreatePerson.CONTENT_TYPE;
        case CREATE_SPECIALTY:					return CreateSpecialty.CONTENT_TYPE;
        case DETAIL_LOCATION:					return DetailLocation.CONTENT_TYPE;
        case DETAIL_PERSON:						return DetailPerson.CONTENT_TYPE;
        case EDIT_FLAG:							return EditFlag.CONTENT_TYPE;
        case EDIT_LINK:							return EditLink.CONTENT_TYPE;
        case EDIT_LOCATION:						return EditLocation.CONTENT_TYPE;
        case EDIT_ORGANIZATION:					return EditOrganization.CONTENT_TYPE;
        case EDIT_PERSON:						return EditPerson.CONTENT_TYPE;
        case EDIT_ROLE:							return EditRole.CONTENT_TYPE;
        case EDIT_SPECIALTY:					return EditSpecialty.CONTENT_TYPE;
        case LIST_APPROVED_FLAGS:				return ListApprovedFlags.CONTENT_TYPE;
        case LIST_LAST_EDITED_BY_MEMBER:		return ListLastEditedByMember.CONTENT_TYPE;
        case LIST_LINKS:						return ListLinks.CONTENT_TYPE;
        case LIST_LOCATIONS_BY_ORGANIZATION:	return ListLocationsByOrganization.CONTENT_TYPE;
        case LIST_MAP:							return ListMap.CONTENT_TYPE;
        case LIST_ORGANIZATIONS:				return ListOrganizations.CONTENT_TYPE;
        case LIST_PENDING_FLAGS:				return ListPendingFlags.CONTENT_TYPE;
        case LIST_PEOPLE_BY_SPECIALTY:			return ListPeopleBySpecialty.CONTENT_TYPE;
        case LIST_ROLES:						return ListRoles.CONTENT_TYPE;
        case LIST_SPECIALTIES:					return ListSpecialties.CONTENT_TYPE;
        case LOGIN:								return Login.CONTENT_TYPE;
        case RECENT:							return Recent.CONTENT_TYPE;
        case REQUESTS:							return Requests.CONTENT_TYPE;
        case SEARCH_ALL:						return SearchAll.CONTENT_TYPE;
        case SEARCH_APPROVED_FLAGS:				return SearchApprovedFlags.CONTENT_TYPE;
        case SEARCH_LINKS:						return SearchLinks.CONTENT_TYPE;
        case SEARCH_LOCATIONS_BY_ORGANIZATION:	return SearchLocationsByOrganization.CONTENT_TYPE;
        case SEARCH_MAP:						return SearchMap.CONTENT_TYPE;
        case SEARCH_ORGANIZATIONS:				return SearchOrganizations.CONTENT_TYPE;
        case SEARCH_PENDING_FLAGS:				return SearchPendingFlags.CONTENT_TYPE;
        case SEARCH_PEOPLE_BY_SPECIALTY:		return SearchPeopleBySpecialty.CONTENT_TYPE;
        case SEARCH_SPECIALTIES:				return SearchSpecialties.CONTENT_TYPE;
        case SEARCH_TERMS:						return SearchTerms.CONTENT_TYPE;
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
    	case CHECK_IN:
    		queryBuilder.setTables(CheckIn.TABLE_NAME);
    		break;
    	case CREATE_FLAG:
    		queryBuilder.setTables(CreateFlag.TABLE_NAME);
    		break;
    	case CREATE_LINK:
    		queryBuilder.setTables(CreateLink.TABLE_NAME);
    		break;
    	case CREATE_LOCATION:
    		queryBuilder.setTables(CreateLocation.TABLE_NAME);
    		break;
    	case CREATE_ORGANIZATION:
    		queryBuilder.setTables(CreateOrganization.TABLE_NAME);
    		break;
    	case CREATE_PERSON:
    		queryBuilder.setTables(CreatePerson.TABLE_NAME);
    		break;
    	case CREATE_SPECIALTY:
    		queryBuilder.setTables(CreateSpecialty.TABLE_NAME);
    		break;
    	case DETAIL_LOCATION:
    		queryBuilder.setTables(DetailLocation.TABLE_NAME);
    		break;
    	case DETAIL_PERSON:
    		queryBuilder.setTables(DetailPerson.TABLE_NAME);
    		break;
    	case EDIT_FLAG:
    		queryBuilder.setTables(EditFlag.TABLE_NAME);
    		break;
    	case EDIT_LINK:
    		queryBuilder.setTables(EditLink.TABLE_NAME);
    		break;
    	case EDIT_LOCATION:
    		queryBuilder.setTables(EditLocation.TABLE_NAME);
    		break;
    	case EDIT_ORGANIZATION:
    		queryBuilder.setTables(EditOrganization.TABLE_NAME);
    		break;
    	case EDIT_PERSON:
    		queryBuilder.setTables(EditPerson.TABLE_NAME);
    		break;
    	case EDIT_ROLE:
    		queryBuilder.setTables(EditRole.TABLE_NAME);
    		break;
    	case EDIT_SPECIALTY:
    		queryBuilder.setTables(EditSpecialty.TABLE_NAME);
    		break;
    	case LIST_APPROVED_FLAGS:
    		queryBuilder.setTables(ListApprovedFlags.TABLE_NAME);
    		break;
    	case LIST_LAST_EDITED_BY_MEMBER:
    		queryBuilder.setTables(ListLastEditedByMember.TABLE_NAME);
    		break;
    	case LIST_LINKS:
    		queryBuilder.setTables(ListLinks.TABLE_NAME);
    		break;
    	case LIST_LOCATIONS_BY_ORGANIZATION:
    		queryBuilder.setTables(ListLocationsByOrganization.TABLE_NAME);
    		break;
    	case LIST_MAP:
    		queryBuilder.setTables(ListMap.TABLE_NAME);
    		break;
    	case LIST_ORGANIZATIONS:
    		queryBuilder.setTables(ListOrganizations.TABLE_NAME);
    		break;
    	case LIST_PENDING_FLAGS:
    		queryBuilder.setTables(ListPendingFlags.TABLE_NAME);
    		break;
    	case LIST_PEOPLE_BY_SPECIALTY:
    		queryBuilder.setTables(ListPeopleBySpecialty.TABLE_NAME);
    		break;
    	case LIST_ROLES:
    		queryBuilder.setTables(ListRoles.TABLE_NAME);
    		break;
    	case LIST_SPECIALTIES:
    		queryBuilder.setTables(ListSpecialties.TABLE_NAME);
    		break;
    	case LOGIN:
    		queryBuilder.setTables(Login.TABLE_NAME);
    		break;
    	case RECENT:
    		queryBuilder.setTables(Recent.TABLE_NAME);
    		break;
    	case REQUESTS:
    		queryBuilder.setTables(Requests.TABLE_NAME);
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
    	case SEARCH_LOCATIONS_BY_ORGANIZATION:
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
        case CHECK_IN:
    		rowsAffected = sqlDB.delete(CheckIn.TABLE_NAME, selection, selectionArgs);
    		break;
    	case CREATE_FLAG:
    		rowsAffected = sqlDB.delete(CreateFlag.TABLE_NAME, selection, selectionArgs);
    		break;
    	case CREATE_LINK:
    		rowsAffected = sqlDB.delete(CreateLink.TABLE_NAME, selection, selectionArgs);
    		break;
    	case CREATE_LOCATION:
    		rowsAffected = sqlDB.delete(CreateLocation.TABLE_NAME, selection, selectionArgs);
    		break;
    	case CREATE_ORGANIZATION:
    		rowsAffected = sqlDB.delete(CreateOrganization.TABLE_NAME, selection, selectionArgs);
    		break;
    	case CREATE_PERSON:
    		rowsAffected = sqlDB.delete(CreatePerson.TABLE_NAME, selection, selectionArgs);
    		break;
    	case CREATE_SPECIALTY:
    		rowsAffected = sqlDB.delete(CreateSpecialty.TABLE_NAME, selection, selectionArgs);
    		break;
    	case DETAIL_LOCATION:
    		rowsAffected = sqlDB.delete(DetailLocation.TABLE_NAME, selection, selectionArgs);
    		break;
    	case DETAIL_PERSON:
    		rowsAffected = sqlDB.delete(DetailPerson.TABLE_NAME, selection, selectionArgs);
    		break;
    	case EDIT_FLAG:
    		rowsAffected = sqlDB.delete(EditFlag.TABLE_NAME, selection, selectionArgs);
    		break;
    	case EDIT_LINK:
    		rowsAffected = sqlDB.delete(EditLink.TABLE_NAME, selection, selectionArgs);
    		break;
    	case EDIT_LOCATION:
    		rowsAffected = sqlDB.delete(EditLocation.TABLE_NAME, selection, selectionArgs);
    		break;
    	case EDIT_ORGANIZATION:
    		rowsAffected = sqlDB.delete(EditOrganization.TABLE_NAME, selection, selectionArgs);
    		break;
    	case EDIT_PERSON:
    		rowsAffected = sqlDB.delete(EditPerson.TABLE_NAME, selection, selectionArgs);
    		break;
    	case EDIT_ROLE:
    		rowsAffected = sqlDB.delete(EditRole.TABLE_NAME, selection, selectionArgs);
    		break;
    	case EDIT_SPECIALTY:
    		rowsAffected = sqlDB.delete(EditSpecialty.TABLE_NAME, selection, selectionArgs);
    		break;
    	case LIST_APPROVED_FLAGS:
    		rowsAffected = sqlDB.delete(ListApprovedFlags.TABLE_NAME, selection, selectionArgs);
    		break;
    	case LIST_LAST_EDITED_BY_MEMBER:
    		rowsAffected = sqlDB.delete(ListLastEditedByMember.TABLE_NAME, selection, selectionArgs);
    		break;
    	case LIST_LINKS:
    		rowsAffected = sqlDB.delete(ListLinks.TABLE_NAME, selection, selectionArgs);
    		break;
    	case LIST_LOCATIONS_BY_ORGANIZATION:
    		rowsAffected = sqlDB.delete(ListLocationsByOrganization.TABLE_NAME, selection, selectionArgs);
    		break;
    	case LIST_MAP:
    		rowsAffected = sqlDB.delete(ListMap.TABLE_NAME, selection, selectionArgs);
    		break;
    	case LIST_ORGANIZATIONS:
    		rowsAffected = sqlDB.delete(ListOrganizations.TABLE_NAME, selection, selectionArgs);
    		break;
    	case LIST_PENDING_FLAGS:
    		rowsAffected = sqlDB.delete(ListPendingFlags.TABLE_NAME, selection, selectionArgs);
    		break;
    	case LIST_PEOPLE_BY_SPECIALTY:
    		rowsAffected = sqlDB.delete(ListPeopleBySpecialty.TABLE_NAME, selection, selectionArgs);
    		break;
    	case LIST_ROLES:
    		rowsAffected = sqlDB.delete(ListRoles.TABLE_NAME, selection, selectionArgs);
    		break;
    	case LIST_SPECIALTIES:
    		rowsAffected = sqlDB.delete(ListSpecialties.TABLE_NAME, selection, selectionArgs);
    		break;
    	case LOGIN:
    		rowsAffected = sqlDB.delete(Login.TABLE_NAME, selection, selectionArgs);
    		break;
    	case RECENT:
    		rowsAffected = sqlDB.delete(Recent.TABLE_NAME, selection, selectionArgs);
    		break;
    	case REQUESTS:
    		rowsAffected = sqlDB.delete(Requests.TABLE_NAME, selection, selectionArgs);
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
    	case SEARCH_LOCATIONS_BY_ORGANIZATION:
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
	    	case CHECK_IN:
	    		newID = sqlDB.insertOrThrow(CheckIn.TABLE_NAME, null, values);
	    		break;
	    	case CREATE_FLAG:
	    		newID = sqlDB.insertOrThrow(CreateFlag.TABLE_NAME, null, values);
	    		break;
	    	case CREATE_LINK:
	    		newID = sqlDB.insertOrThrow(CreateLink.TABLE_NAME, null, values);
	    		break;
	    	case CREATE_LOCATION:
	    		newID = sqlDB.insertOrThrow(CreateLocation.TABLE_NAME, null, values);
	    		break;
	    	case CREATE_ORGANIZATION:
	    		newID = sqlDB.insertOrThrow(CreateOrganization.TABLE_NAME, null, values);
	    		break;
	    	case CREATE_PERSON:
	    		newID = sqlDB.insertOrThrow(CreatePerson.TABLE_NAME, null, values);
	    		break;
	    	case CREATE_SPECIALTY:
	    		newID = sqlDB.insertOrThrow(CreateSpecialty.TABLE_NAME, null, values);
	    		break;
	    	case DETAIL_LOCATION:
	    		newID = sqlDB.insertOrThrow(DetailLocation.TABLE_NAME, null, values);
	    		break;
	    	case DETAIL_PERSON:
	    		newID = sqlDB.insertOrThrow(DetailPerson.TABLE_NAME, null, values);
	    		break;
	    	case EDIT_FLAG:
	    		newID = sqlDB.insertOrThrow(EditFlag.TABLE_NAME, null, values);
	    		break;
	    	case EDIT_LINK:
	    		newID = sqlDB.insertOrThrow(EditLink.TABLE_NAME, null, values);
	    		break;
	    	case EDIT_LOCATION:
	    		newID = sqlDB.insertOrThrow(EditLocation.TABLE_NAME, null, values);
	    		break;
	    	case EDIT_ORGANIZATION:
	    		newID = sqlDB.insertOrThrow(EditOrganization.TABLE_NAME, null, values);
	    		break;
	    	case EDIT_PERSON:
	    		newID = sqlDB.insertOrThrow(EditPerson.TABLE_NAME, null, values);
	    		break;
	    	case EDIT_ROLE:
	    		newID = sqlDB.insertOrThrow(EditRole.TABLE_NAME, null, values);
	    		break;
	    	case EDIT_SPECIALTY:
	    		newID = sqlDB.insertOrThrow(EditSpecialty.TABLE_NAME, null, values);
	    		break;
	    	case LIST_APPROVED_FLAGS:
	    		newID = sqlDB.insertOrThrow(ListApprovedFlags.TABLE_NAME, null, values);
	    		break;
	    	case LIST_LAST_EDITED_BY_MEMBER:
	    		newID = sqlDB.insertOrThrow(ListLastEditedByMember.TABLE_NAME, null, values);
	    		break;
	    	case LIST_LINKS:
	    		newID = sqlDB.insertOrThrow(ListLinks.TABLE_NAME, null, values);
	    		break;
	    	case LIST_LOCATIONS_BY_ORGANIZATION:
	    		newID = sqlDB.insertOrThrow(ListLocationsByOrganization.TABLE_NAME, null, values);
	    		break;
	    	case LIST_MAP:
	    		newID = sqlDB.insertOrThrow(ListMap.TABLE_NAME, null, values);
	    		break;
	    	case LIST_ORGANIZATIONS:
	    		newID = sqlDB.insertOrThrow(ListOrganizations.TABLE_NAME, null, values);
	    		break;
	    	case LIST_PENDING_FLAGS:
	    		newID = sqlDB.insertOrThrow(ListPendingFlags.TABLE_NAME, null, values);
	    		break;
	    	case LIST_PEOPLE_BY_SPECIALTY:
	    		newID = sqlDB.insertOrThrow(ListPeopleBySpecialty.TABLE_NAME, null, values);
	    		break;
	    	case LIST_ROLES:
	    		newID = sqlDB.insertOrThrow(ListRoles.TABLE_NAME, null, values);
	    		break;
	    	case LIST_SPECIALTIES:
	    		newID = sqlDB.insertOrThrow(ListSpecialties.TABLE_NAME, null, values);
	    		break;
	    	case LOGIN:
	    		newID = sqlDB.insertOrThrow(Login.TABLE_NAME, null, values);
	    		break;
	    	case RECENT:
	    		newID = sqlDB.insertOrThrow(Recent.TABLE_NAME, null, values);
	    		break;
	    	case REQUESTS:
	    		newID = sqlDB.insertOrThrow(Requests.TABLE_NAME, null, values);
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
	    	case SEARCH_LOCATIONS_BY_ORGANIZATION:
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
	        default:
	        	Log.w(LOG_TAG, "insert() - Unknown Uri: " + uri.toString());
	        }
    	}
    	catch (SQLException e) {
            Log.w(LOG_TAG, "Error inserting to " + uri.toString() + ": " + e.getMessage());
        }

    	if (newID > 0) {
            Uri newUri = ContentUris.withAppendedId(uri, newID);
            getContext().getContentResolver().notifyChange(uri, null);
            return newUri;
        }
    	else {
//    		Log.e(LOG_TAG, "Failed inserting Uri: " + uri.toString());
        }

        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
    	SQLiteDatabase sqlDB = mDB.getWritableDatabase();
        int rowsAffected = 0;

        switch (sUriMatcher.match(uri)) {
        case CHECK_IN:
    		rowsAffected = sqlDB.update(CheckIn.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case CREATE_FLAG:
    		rowsAffected = sqlDB.update(CreateFlag.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case CREATE_LINK:
    		rowsAffected = sqlDB.update(CreateLink.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case CREATE_LOCATION:
    		rowsAffected = sqlDB.update(CreateLocation.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case CREATE_ORGANIZATION:
    		rowsAffected = sqlDB.update(CreateOrganization.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case CREATE_PERSON:
    		rowsAffected = sqlDB.update(CreatePerson.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case CREATE_SPECIALTY:
    		rowsAffected = sqlDB.update(CreateSpecialty.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case DETAIL_LOCATION:
    		rowsAffected = sqlDB.update(DetailLocation.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case DETAIL_PERSON:
    		rowsAffected = sqlDB.update(DetailPerson.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case EDIT_FLAG:
    		rowsAffected = sqlDB.update(EditFlag.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case EDIT_LINK:
    		rowsAffected = sqlDB.update(EditLink.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case EDIT_LOCATION:
    		rowsAffected = sqlDB.update(EditLocation.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case EDIT_ORGANIZATION:
    		rowsAffected = sqlDB.update(EditOrganization.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case EDIT_PERSON:
    		rowsAffected = sqlDB.update(EditPerson.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case EDIT_ROLE:
    		rowsAffected = sqlDB.update(EditRole.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case EDIT_SPECIALTY:
    		rowsAffected = sqlDB.update(EditSpecialty.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case LIST_APPROVED_FLAGS:
    		rowsAffected = sqlDB.update(ListApprovedFlags.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case LIST_LAST_EDITED_BY_MEMBER:
    		rowsAffected = sqlDB.update(ListLastEditedByMember.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case LIST_LINKS:
    		rowsAffected = sqlDB.update(ListLinks.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case LIST_LOCATIONS_BY_ORGANIZATION:
    		rowsAffected = sqlDB.update(ListLocationsByOrganization.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case LIST_MAP:
    		rowsAffected = sqlDB.update(ListMap.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case LIST_ORGANIZATIONS:
    		rowsAffected = sqlDB.update(ListOrganizations.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case LIST_PENDING_FLAGS:
    		rowsAffected = sqlDB.update(ListPendingFlags.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case LIST_PEOPLE_BY_SPECIALTY:
    		rowsAffected = sqlDB.update(ListPeopleBySpecialty.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case LIST_ROLES:
    		rowsAffected = sqlDB.update(ListRoles.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case LIST_SPECIALTIES:
    		rowsAffected = sqlDB.update(ListSpecialties.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case LOGIN:
    		rowsAffected = sqlDB.update(Login.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case RECENT:
    		rowsAffected = sqlDB.update(Recent.TABLE_NAME, values, selection, selectionArgs);
    		break;
    	case REQUESTS:
    		rowsAffected = sqlDB.update(Requests.TABLE_NAME, values, selection, selectionArgs);
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
    	case SEARCH_LOCATIONS_BY_ORGANIZATION:
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
        default:
        	Log.w(LOG_TAG, "update() - Unknown Uri: " + uri.toString());
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowsAffected;
    }
}
