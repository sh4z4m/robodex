package com.robodex.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.robodex.data.DatabaseContract.Organization;

public class DatabaseContentProvider extends ContentProvider {
    private static final String LOG_TAG = DatabaseContentProvider.class.getSimpleName();

    private DatabaseOpenHelper mDB;

    /** Get all the groups */
    public static final int GROUP = 1;
    /** Get a group's subgroups using its ID */
    public static final int GROUP_ID = 2;
    /** Get a group's subgroups using its name */
    public static final int GROUP_NAME = 7;
    /** Get all subgroups */
    public static final int SUBGROUP = 3;
    /** Get a subgroup by ID */
    public static final int SUBGROUP_ID = 4;
    /** Get a subgroup by name */
    public static final int SUBGROUP_NAME = 8;
    /** Get all the mappings of subgroups to groups */
    public static final int SUBGROUPGROUP = 5;
    /** Get a custom list of groups and subgroups */
    public static final int COMBINATION = 9;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Organization.TABLE_NAME, GROUP);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Organization.TABLE_NAME + "/#", GROUP_ID);
        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Organization.TABLE_NAME + "/*", GROUP_NAME);
//        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Subgroup.TABLE_NAME, SUBGROUP);
//        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Subgroup.TABLE_NAME + "/#", SUBGROUP_ID);
//        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Subgroup.TABLE_NAME + "/*", SUBGROUP_NAME);
//        sUriMatcher.addURI(DatabaseContract.AUTHORITY, SubgroupGroup.TABLE_NAME, SUBGROUPGROUP);
//        sUriMatcher.addURI(DatabaseContract.AUTHORITY, Combination.TABLE_NAME + "/*", COMBINATION);
    }

    @Override
    public boolean onCreate() {
        mDB = new DatabaseOpenHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
        case GROUP:
            return Organization.CONTENT_TYPE;
        case GROUP_ID:
            return Organization.CONTENT_ITEM_TYPE;
        case GROUP_NAME:
            return Organization.CONTENT_ITEM_TYPE;
//        case SUBGROUP:
//            return Subgroup.CONTENT_TYPE;
//        case SUBGROUP_ID:
//            return Subgroup.CONTENT_ITEM_TYPE;
//        case SUBGROUP_NAME:
//            return Subgroup.CONTENT_ITEM_TYPE;
//        case SUBGROUPGROUP:
//            return SubgroupGroup.CONTENT_TYPE;
//        case COMBINATION:
//            return Combination.CONTENT_TYPE;
        default:
            return null;
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {

        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        return 0;
    }



    @Override
    public Uri insert(Uri uri, ContentValues values) {

        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {

        return 0;
    }
}
