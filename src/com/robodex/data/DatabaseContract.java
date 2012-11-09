package com.robodex.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class DatabaseContract {
    private DatabaseContract() { /* Not instantiable */ }

    public static final String	AUTHORITY               = "com.robodex";
    public static final String  DATABASE_NAME           = "robodex.db";
    public static final int     DATABASE_VERSION        = 1;

    public static final class Requests {
        public static final String 	TABLE_NAME 			= "requests";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_REQUEST_TYPE	= "request_type";
        public static final String 	COL_REQUEST_TIME	= "request_time";
        public static final String 	COL_REQUEST_SESSION_ID	= "request_session_id";
        public static final String 	COL_RESPONSE_TIME	= "response_time";
        public static final String 	COL_RESPONSE_CODE	= "response_code";
        public static final String 	COL_RESPONSE_MESSAGE	= "response_message";
        public static final String 	COL_RESPONSE_SESSION_ID	= "response_session_id";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_REQUEST_TYPE 			+ " text NOT NULL, "
                + COL_REQUEST_TIME 			+ " text NOT NULL, "
                + COL_REQUEST_SESSION_ID 	+ " text, "
                + COL_RESPONSE_TIME 		+ " text, "
                + COL_RESPONSE_CODE 		+ " integer, "
                + COL_RESPONSE_MESSAGE 		+ " text, "
                + COL_RESPONSE_SESSION_ID 	+ " text"
                + ");";
    }

    public static final class Organization {
        private Organization() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "organization";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_ORGANIZATION_ID = "organization_id";
        public static final String 	COL_ORGANIZATION 	= "organization";
        public static final String 	COL_REMOVE 			= "remove";
        public static final String 	COL_REMOVE_APPROVED = "remove_approved";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_ORGANIZATION_ID 		+ " integer UNIQUE NOT NULL, "
                + COL_ORGANIZATION 			+ " text UNIQUE NOT NULL, "
                + COL_REMOVE 				+ " integer, "
                + COL_REMOVE_APPROVED 		+ " integer"
                + ");";
    }

    public static final class OrganizationLocation {
        private OrganizationLocation() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "organization_location";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_ORGANIZATION_ID	= "organization_id";
        public static final String 	COL_PRIMARY			= "primary";
        public static final String 	COL_ADDRESS			= "address";
        public static final String 	COL_CITY			= "city";
        public static final String 	COL_STATE			= "state";
        public static final String 	COL_ZIP				= "zip";
        public static final String 	COL_LATITUDE		= "latitude";
        public static final String 	COL_LONGITUDE		= "longitude";
        public static final String 	COL_EMAIL1			= "email1";
        public static final String 	COL_EMAIL2			= "email2";
        public static final String 	COL_PHONE1			= "phone1";
        public static final String 	COL_PHONE2			= "phone2";
        public static final String 	COL_MODIFIED_BY		= "modified_by";
        public static final String 	COL_MODIFIED_TIME	= "modified_time";
        public static final String 	COL_REMOVE 			= "remove";
        public static final String 	COL_REMOVE_APPROVED = "remove_approved";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_LOCATION_ID 			+ " integer UNIQUE NOT NULL, "
                + COL_ORGANIZATION_ID 		+ " integer NOT NULL, "
                + COL_PRIMARY 				+ " integer, "
                + COL_ADDRESS 				+ " text, "
                + COL_CITY 					+ " text, "
                + COL_STATE 				+ " text, "
                + COL_ZIP 					+ " text, "
                + COL_LATITUDE 				+ " real, "
                + COL_LONGITUDE				+ " real, "
                + COL_EMAIL1 				+ " text, "
                + COL_EMAIL2				+ " text, "
                + COL_PHONE1 				+ " text, "
                + COL_PHONE2 				+ " text, "
                + COL_MODIFIED_BY 			+ " integer, "
                + COL_MODIFIED_TIME			+ " text, "
                + COL_REMOVE 				+ " integer, "
                + COL_REMOVE_APPROVED 		+ " integer"
                + ");";
    }

    public static final class Flag {
        private Flag() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "flag";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_FLAG_ID			= "flag_id";
        public static final String 	COL_FLAG_BY			= "flag_by";
        public static final String 	COL_FLAG_TIME		= "flag_time";
        public static final String 	COL_FLAG_COMMENT	= "flag_comment";
        public static final String 	COL_VERDICT			= "verdict";
        public static final String 	COL_VERDICT_BY		= "verdict_by";
        public static final String 	COL_VERDICT_TIME	= "verdict_time";
        public static final String 	COL_VERDICT_COMMENT	= "verdict_comment";
        public static final String 	COL_ORGANIZATION_ID	= "organization_id";
        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_LINK_ID			= "link_id";
        public static final String 	COL_MEMBER_ID		= "member_id";
        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_SPECIALTY_ID	= "specialty_id";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_FLAG_ID 				+ " integer UNIQUE NOT NULL, "
                + COL_FLAG_BY 				+ " integer NOT NULL, "
                + COL_FLAG_TIME 			+ " text, "
                + COL_FLAG_COMMENT 			+ " text, "
                + COL_VERDICT 				+ " integer, "
                + COL_VERDICT_BY 			+ " integer, "
                + COL_VERDICT_TIME 			+ " text, "
                + COL_VERDICT_COMMENT 		+ " text, "
                + COL_ORGANIZATION_ID		+ " integer, "
                + COL_LOCATION_ID 			+ " integer, "
                + COL_LINK_ID				+ " integer, "
                + COL_MEMBER_ID 			+ " integer, "
                + COL_PERSON_ID 			+ " integer, "
                + COL_SPECIALTY_ID 			+ " integer"
                + ");";
    }

    public static final class Link {
        private Link() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "link";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_LINK_ID			= "link_id";
        public static final String 	COL_ROLE_ID			= "role_id";
        public static final String 	COL_LINK			= "link";
        public static final String 	COL_TITLE			= "title";
        public static final String 	COL_TOP_LEVEL		= "top_level";
        public static final String 	COL_ORGANIZATION_ID	= "organization_id";
        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_MEMBER_ID		= "member_id";
        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_SPECIALTY_ID	= "specialty_id";
        public static final String 	COL_REMOVE 			= "remove";
        public static final String 	COL_REMOVE_APPROVED = "remove_approved";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_LINK_ID 				+ " integer UNIQUE NOT NULL, "
                + COL_ROLE_ID 				+ " integer, "
                + COL_LINK 					+ " text, "
                + COL_TITLE 				+ " text, "
                + COL_TOP_LEVEL 			+ " integer, "
                + COL_ORGANIZATION_ID 		+ " integer, "
                + COL_LOCATION_ID 			+ " integer, "
                + COL_MEMBER_ID 			+ " integer, "
                + COL_PERSON_ID				+ " integer, "
                + COL_SPECIALTY_ID 			+ " integer, "
                + COL_REMOVE 				+ " integer, "
                + COL_REMOVE_APPROVED 		+ " integer"
                + ");";
    }

    public static final class Member {
        private Member() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "member";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_MEMBER_ID		= "member_id";
        public static final String 	COL_ROLE_ID			= "role_id";
        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_USERNAME		= "username";
        public static final String 	COL_PASSWORD		= "password";
        public static final String 	COL_EMAIL			= "email";
        public static final String 	COL_LATITUDE		= "latitude";
        public static final String 	COL_LONGITUDE		= "longitude";
        public static final String 	COL_LOCATION_TIME	= "location_time";
        public static final String 	COL_EDITS			= "edits";
        public static final String 	COL_EDITS_TODAY		= "edits_today";
        public static final String 	COL_LOGINS			= "logins";
        public static final String 	COL_LOGIN_TIME		= "login_time";
        public static final String 	COL_EXPIRE_DATE		= "expire_date";
        public static final String 	COL_DISCLAIMER_ACCEPT	= "disclaimer_accept";
        public static final String 	COL_REMOVE 			= "remove";
        public static final String 	COL_REMOVE_APPROVED = "remove_approved";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_MEMBER_ID 			+ " integer UNIQUE NOT NULL, "
                + COL_ROLE_ID 				+ " integer NOT NULL, "
                + COL_PERSON_ID 			+ " integer NOT NULL, "
                + COL_USERNAME 				+ " text UNIQUE NOT NULL, "
                + COL_PASSWORD 				+ " text, "
                + COL_EMAIL 				+ " text, "
                + COL_LATITUDE 				+ " real, "
                + COL_LONGITUDE				+ " real, "
                + COL_LOCATION_TIME 		+ " text, "
                + COL_EDITS					+ " integer, "
                + COL_EDITS_TODAY 			+ " integer, "
                + COL_LOGINS 				+ " integer, "
                + COL_LOGIN_TIME 			+ " text, "
                + COL_EXPIRE_DATE			+ " text, "
                + COL_DISCLAIMER_ACCEPT		+ " integer, "
                + COL_REMOVE 				+ " integer, "
                + COL_REMOVE_APPROVED 		+ " integer"
                + ");";
    }

    public static final class Person {
        private Person() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "person";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_FIRST_NAME		= "first_name";
        public static final String 	COL_LAST_NAME		= "last_name";
        public static final String 	COL_ADDRESS			= "address";
        public static final String 	COL_CITY			= "city";
        public static final String 	COL_STATE			= "state";
        public static final String 	COL_ZIP				= "zip";
        public static final String 	COL_LATITUDE		= "latitude";
        public static final String 	COL_LONGITUDE		= "longitude";
        public static final String 	COL_EMAIL1			= "email1";
        public static final String 	COL_PHONE1			= "phone1";
        public static final String 	COL_PHONE2			= "phone2";
        public static final String 	COL_NOTES			= "notes";
        public static final String 	COL_MODIFIED_BY		= "modified_by";
        public static final String 	COL_MODIFIED_TIME	= "modified_time";
        public static final String 	COL_REMOVE 			= "remove";
        public static final String 	COL_REMOVE_APPROVED = "remove_approved";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_PERSON_ID 			+ " integer UNIQUE NOT NULL, "
                + COL_FIRST_NAME 			+ " text, "
                + COL_LAST_NAME 			+ " text, "
                + COL_ADDRESS 				+ " text, "
                + COL_CITY 					+ " text, "
                + COL_STATE 				+ " text, "
                + COL_ZIP 					+ " text, "
                + COL_LATITUDE 				+ " real, "
                + COL_LONGITUDE				+ " real, "
                + COL_EMAIL1 				+ " text, "
                + COL_PHONE1 				+ " text, "
                + COL_PHONE2 				+ " text, "
                + COL_NOTES 				+ " text, "
                + COL_MODIFIED_BY 			+ " integer, "
                + COL_MODIFIED_TIME			+ " text, "
                + COL_REMOVE 				+ " integer, "
                + COL_REMOVE_APPROVED 		+ " integer"
                + ");";
    }

    public static final class PersonOrganization {
        private PersonOrganization() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "person_organization";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_ORGANIZATION_ID	= "organization_id";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_PERSON_ID 			+ " integer, "
                + COL_ORGANIZATION_ID 		+ " integer"
                + ");";
    }

    public static final class PersonSpecialty {
        private PersonSpecialty() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "person_specialty";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_SPECIALTY_ID	= "specialty_id";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_PERSON_ID 			+ " integer, "
                + COL_SPECIALTY_ID 			+ " integer"
                + ");";
    }

    public static final class Role {
        private Role() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "role";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_ROLE_ID			= "role_id";
        public static final String 	COL_ROLE			= "role";
        public static final String 	COL_DAILY_EDITS		= "daily_edits";
        public static final String 	COL_EDIT_TOP_LINKS	= "edit_top_links";
        public static final String 	COL_FLAG_FOR_REMOVAL= "flag_for_removal";
        public static final String 	COL_APPROVE_REMOVAL	= "approve_removal";
        public static final String 	COL_UNREMOVE		= "unremove";
        public static final String 	COL_CHANGE_MEMBER_ROLES	= "change_member_roles";
        public static final String 	COL_MANAGE_ROLES	= "manage_roles";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_ROLE_ID 				+ " integer UNIQUE NOT NULL, "
                + COL_ROLE 					+ " text UNIQUE NOT NULL, "
                + COL_DAILY_EDITS 			+ " integer, "
                + COL_EDIT_TOP_LINKS 		+ " integer, "
                + COL_FLAG_FOR_REMOVAL 		+ " integer, "
                + COL_APPROVE_REMOVAL 		+ " integer, "
                + COL_UNREMOVE 				+ " integer, "
                + COL_CHANGE_MEMBER_ROLES 	+ " integer, "
                + COL_MANAGE_ROLES			+ " integer"
                + ");";
    }

    public static final class Specialty {
        private Specialty() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "specialty";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_SPECIALTY_ID	= "specialty_id";
        public static final String 	COL_SPECIALTY		= "specialty";
        public static final String 	COL_REMOVE 			= "remove";
        public static final String 	COL_REMOVE_APPROVED = "remove_approved";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_SPECIALTY_ID 			+ " integer UNIQUE NOT NULL, "
                + COL_SPECIALTY 			+ " text UNIQUE NOT NULL, "
                + COL_REMOVE 				+ " integer, "
                + COL_REMOVE_APPROVED 		+ " integer"
                + ");";
    }


    /*
     * ==============================================================
     *
     * Above are basically copies of what is on the server side.
     * Some can be used but the app's data is more correlated with
     * the server interactions. Caching results and user activity
     * is its main purpose.
     *
     * ==============================================================
     */


    public static final class Edits {
        private Edits() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "edits";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_EDIT_ID 		= "edit_id";			// Can be the id of the first row in this set of edits
        public static final String 	COL_EDIT_TIME 		= "edit_time";
        public static final String 	COL_TABLE_NAME		= "table_name";
        public static final String 	COL_ROW_ID			= "row_id";
        public static final String 	COL_FIELD_NAME		= "field_name";
        public static final String 	COL_OLD_VALUE 		= "old_value";
        public static final String 	COL_NEW_VALUE 		= "new_value";
        public static final String 	COL_RESULT 			= "result";
        public static final String 	COL_RESULT_TIME 	= "result_time";

        public static final int 	RESULT_UNKNOWN		= 0;
        public static final int 	RESULT_PENDING		= 1;
        public static final int 	RESULT_ACCEPTED		= 2;
        public static final int 	RESULT_REJECTED		= 3;

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_EDIT_ID 				+ " integer NOT NULL, "
                + COL_EDIT_TIME 			+ " text NOT NULL, "
                + COL_TABLE_NAME 			+ " text NOT NULL, "
                + COL_ROW_ID 				+ " integer NOT NULL, "
                + COL_FIELD_NAME 			+ " text NOT NULL, "
                + COL_OLD_VALUE 			+ " text NOT NULL, "
                + COL_NEW_VALUE 			+ " text, "
                + COL_RESULT 				+ " integer, "
                + COL_RESULT_TIME 			+ " text"
                + ");";
    }


    public static final class MapCache {
        private MapCache() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "map_cache";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        // Meta fields
        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_TIMESTAMP 		= "timestamp";

        // Common/shared fields
        public static final String 	COL_LATITUDE		= "latitude";
        public static final String 	COL_LONGITUDE		= "longitude";
        public static final String 	COL_ADDRESS			= "address";
        public static final String 	COL_CITY			= "city";
        public static final String 	COL_STATE			= "state";
        public static final String 	COL_ZIP				= "zip";
        public static final String 	COL_EMAIL1			= "email1";
        public static final String 	COL_PHONE1			= "phone1";
        public static final String 	COL_PHONE2			= "phone2";

        // Person/Member fields
        public static final String 	COL_FIRST_NAME		= "first_name";
        public static final String 	COL_LAST_NAME		= "last_name";

        // Person fields
        public static final String 	COL_PERSON_ID		= "person_id";

        // Member fields
        public static final String 	COL_MEMBER_ID		= "member_id";
        public static final String 	COL_LOCATION_TIME	= "location_time";

        // OrganizationLocation fields
        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_ORGANIZATION	= "organization";
        public static final String 	COL_PRIMARY			= "primary";
        public static final String 	COL_EMAIL2			= "email2";



        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_TIMESTAMP 			+ " text NOT NULL, "

                + COL_LATITUDE 				+ " real, "
                + COL_LONGITUDE 			+ " real, "
                + COL_ADDRESS 				+ " text, "
                + COL_CITY 					+ " text, "
                + COL_STATE 				+ " text, "
                + COL_ZIP 					+ " text, "
                + COL_EMAIL1 				+ " text, "
                + COL_PHONE1 				+ " text, "
                + COL_PHONE2 				+ " text, "

                + COL_FIRST_NAME 			+ " text, "
                + COL_LAST_NAME 			+ " text, "

                + COL_PERSON_ID 			+ " integer, "

                + COL_MEMBER_ID 			+ " integer, "
                + COL_LOCATION_TIME 		+ " text, "

                + COL_LOCATION_ID 			+ " integer, "
                + COL_ORGANIZATION 			+ " text, "
                + COL_PRIMARY 				+ " integer, "
                + COL_EMAIL2 				+ " text"
                + ");";
    }


    /** Person or OrganizationLocation */
    public static final class Recent {
        private Recent() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "recent";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_TIMESTAMP 		= "timestamp";

        public static final String 	COL_CITY			= "city";
        public static final String 	COL_STATE			= "state";

        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_FIRST_NAME		= "first_name";
        public static final String 	COL_LAST_NAME		= "last_name";

        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_ORGANIZATION	= "organization";



        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_TIMESTAMP 			+ " text NOT NULL, "

                + COL_CITY 					+ " text, "
                + COL_STATE 				+ " text, "

                + COL_PERSON_ID 			+ " integer, "
                + COL_FIRST_NAME 			+ " text, "
                + COL_LAST_NAME 			+ " text, "

                + COL_LOCATION_ID 			+ " integer, "
                + COL_ORGANIZATION 			+ " text"
                + ");";
    }


    public static final class PeopleBySpecialty {
        private PeopleBySpecialty() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "people_by_specialty";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_TIMESTAMP 		= "timestamp";

        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_FIRST_NAME		= "first_name";
        public static final String 	COL_LAST_NAME		= "last_name";
        public static final String 	COL_CITY			= "city";
        public static final String 	COL_STATE			= "state";


        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_TIMESTAMP 			+ " text NOT NULL, "

                + COL_PERSON_ID 			+ " integer NOT NULL, "
                + COL_FIRST_NAME 			+ " text, "
                + COL_LAST_NAME 			+ " text, "
                + COL_CITY 					+ " text, "
                + COL_STATE 				+ " text"
                + ");";
    }


    public static final class LocationByOrganization {
        private LocationByOrganization() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "location_by_organization";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_TIMESTAMP 		= "timestamp";
        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_ADDRESS			= "address";
        public static final String 	COL_CITY			= "city";
        public static final String 	COL_STATE			= "state";
        public static final String 	COL_ZIP				= "zip";


        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_TIMESTAMP 			+ " text NOT NULL, "
                + COL_LOCATION_ID 			+ " integer, "
                + COL_ADDRESS 				+ " text, "
                + COL_CITY 					+ " text, "
                + COL_STATE 				+ " text, "
                + COL_ZIP 					+ " text"
                + ");";
    }




    public static final class PersonDetail {
        private PersonDetail() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "person_detail";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;

        // Person/Member fields
        public static final String 	COL_PERSON_ID		= "person_id";

        // Person fields
        public static final String 	COL_FIRST_NAME		= "first_name";
        public static final String 	COL_LAST_NAME		= "last_name";
        public static final String 	COL_ADDRESS			= "address";
        public static final String 	COL_CITY			= "city";
        public static final String 	COL_STATE			= "state";
        public static final String 	COL_ZIP				= "zip";
        public static final String 	COL_LATITUDE		= "latitude";
        public static final String 	COL_LONGITUDE		= "longitude";
        public static final String 	COL_EMAIL1			= "email1";
        public static final String 	COL_PHONE1			= "phone1";
        public static final String 	COL_PHONE2			= "phone2";
        public static final String 	COL_NOTES			= "notes";
        public static final String 	COL_MODIFIED_BY		= "modified_by";
        public static final String 	COL_MODIFIED_TIME	= "modified_time";
        // Removing a person will hide them from lists
        // If they are a member, it will effectively make them a restricted user (login, but no edits)
        public static final String 	COL_REMOVE 			= "remove";
        public static final String 	COL_REMOVE_APPROVED = "remove_approved";

        // Member fields
        public static final String 	COL_MEMBER_ID		= "member_id";
        public static final String 	COL_ROLE			= "role";
        public static final String 	COL_USERNAME		= "username";
        public static final String 	COL_EMAIL			= "email";
        public static final String 	COL_MEMBER_LATITUDE	= "member_latitude";
        public static final String 	COL_MEMBER_LONGITUDE	= "member_longitude";
        public static final String 	COL_LOCATION_TIME	= "location_time";
        public static final String 	COL_EDITS			= "edits";
        public static final String 	COL_EDITS_TODAY		= "edits_today";
        public static final String 	COL_LOGINS			= "logins";
        public static final String 	COL_LOGIN_TIME		= "login_time";
        public static final String 	COL_EXPIRE_DATE		= "expire_date";
        public static final String 	COL_DISCLAIMER_ACCEPT	= "disclaimer_accept";
        // Removing a member disable's their login
        public static final String 	COL_MEMBER_REMOVE 	= "member_remove";
        public static final String 	COL_MEMBER_REMOVE_APPROVED = "member_remove_approved";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_PERSON_ID 			+ " integer UNIQUE NOT NULL, "

                + COL_FIRST_NAME 			+ " text, "
                + COL_LAST_NAME 			+ " text, "
                + COL_ADDRESS 				+ " text, "
                + COL_CITY 					+ " text, "
                + COL_STATE 				+ " text, "
                + COL_ZIP 					+ " text, "
                + COL_LATITUDE 				+ " real, "
                + COL_LONGITUDE				+ " real, "
                + COL_EMAIL1 				+ " text, "
                + COL_PHONE1 				+ " text, "
                + COL_PHONE2 				+ " text, "
                + COL_NOTES 				+ " text, "
                + COL_MODIFIED_BY 			+ " integer, "
                + COL_MODIFIED_TIME			+ " text, "
                + COL_REMOVE 				+ " integer, "
                + COL_REMOVE_APPROVED 		+ " integer, "

                + COL_MEMBER_ID 			+ " integer, "
                + COL_ROLE 					+ " text, "
                + COL_USERNAME 				+ " text, "
                + COL_EMAIL 				+ " text, "
                + COL_MEMBER_LATITUDE 		+ " real, "
                + COL_MEMBER_LONGITUDE		+ " real, "
                + COL_LOCATION_TIME 		+ " text, "
                + COL_EDITS					+ " integer, "
                + COL_EDITS_TODAY 			+ " integer, "
                + COL_LOGINS 				+ " integer, "
                + COL_LOGIN_TIME 			+ " text, "
                + COL_EXPIRE_DATE			+ " text, "
                + COL_DISCLAIMER_ACCEPT		+ " integer, "
                + COL_MEMBER_REMOVE 		+ " integer, "
                + COL_MEMBER_REMOVE_APPROVED	+ " integer"
                + ");";
    }


    public static final class LastEditedByMember {
        private LastEditedByMember() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "last_edited_by_member";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;

        public static final String 	COL_CITY			= "city";
        public static final String 	COL_STATE			= "state";

        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_FIRST_NAME		= "first_name";
        public static final String 	COL_LAST_NAME		= "last_name";

        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_ORGANIZATION	= "organization";



        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "

                + COL_CITY 					+ " text, "
                + COL_STATE 				+ " text, "

                + COL_PERSON_ID 			+ " integer, "
                + COL_FIRST_NAME 			+ " text, "
                + COL_LAST_NAME 			+ " text, "

                + COL_LOCATION_ID 			+ " integer, "
                + COL_ORGANIZATION 			+ " text"
                + ");";
    }


    public static final class PendingFlags {
        private PendingFlags() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "pending_flags";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_FLAG_ID			= "flag_id";
        public static final String 	COL_FLAG_BY			= "flag_by";
        public static final String 	COL_FLAG_TIME		= "flag_time";
        public static final String 	COL_FLAG_COMMENT	= "flag_comment";
        public static final String 	COL_ORGANIZATION_ID	= "organization_id";
        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_LINK_ID			= "link_id";
        public static final String 	COL_MEMBER_ID		= "member_id";
        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_SPECIALTY_ID	= "specialty_id";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_FLAG_ID 				+ " integer UNIQUE NOT NULL, "
                + COL_FLAG_BY 				+ " integer NOT NULL, "
                + COL_FLAG_TIME 			+ " text, "
                + COL_FLAG_COMMENT 			+ " text, "
                + COL_ORGANIZATION_ID		+ " integer, "
                + COL_LOCATION_ID 			+ " integer, "
                + COL_LINK_ID				+ " integer, "
                + COL_MEMBER_ID 			+ " integer, "
                + COL_PERSON_ID 			+ " integer, "
                + COL_SPECIALTY_ID 			+ " integer"
                + ");";
    }

    public static final class ApprovedFlags {
        private ApprovedFlags() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "approved_flags";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_FLAG_ID			= "flag_id";
        public static final String 	COL_FLAG_BY			= "flag_by";
        public static final String 	COL_FLAG_TIME		= "flag_time";
        public static final String 	COL_FLAG_COMMENT	= "flag_comment";
        public static final String 	COL_VERDICT			= "verdict";
        public static final String 	COL_VERDICT_BY		= "verdict_by";
        public static final String 	COL_VERDICT_TIME	= "verdict_time";
        public static final String 	COL_VERDICT_COMMENT	= "verdict_comment";
        public static final String 	COL_ORGANIZATION_ID	= "organization_id";
        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_LINK_ID			= "link_id";
        public static final String 	COL_MEMBER_ID		= "member_id";
        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_SPECIALTY_ID	= "specialty_id";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_FLAG_ID 				+ " integer UNIQUE NOT NULL, "
                + COL_FLAG_BY 				+ " integer NOT NULL, "
                + COL_FLAG_TIME 			+ " text, "
                + COL_FLAG_COMMENT 			+ " text, "
                + COL_VERDICT 				+ " integer, "
                + COL_VERDICT_BY 			+ " integer, "
                + COL_VERDICT_TIME 			+ " text, "
                + COL_VERDICT_COMMENT 		+ " text, "
                + COL_ORGANIZATION_ID		+ " integer, "
                + COL_LOCATION_ID 			+ " integer, "
                + COL_LINK_ID				+ " integer, "
                + COL_MEMBER_ID 			+ " integer, "
                + COL_PERSON_ID 			+ " integer, "
                + COL_SPECIALTY_ID 			+ " integer"
                + ");";
    }


    public static final class SearchAll {
        private SearchAll() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "search_all";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_SEARCH			= "search";

        public static final String 	COL_ORGANIZATION_ID	= "organization_id";
        public static final String 	COL_ORGANIZATION	= "organization";
        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_LOCATION		= "location";
        public static final String 	COL_MEMBER_ID		= "member_id";
        public static final String 	COL_PERSON_ID		= "person_id";

        public static final String 	COL_FIRST_NAME		= "first_name";
        public static final String 	COL_LAST_NAME		= "last_name";
        public static final String 	COL_ADDRESS			= "address";
        public static final String 	COL_CITY			= "city";
        public static final String 	COL_STATE			= "state";

        public static final String 	COL_SPECIALTY_ID	= "specialty_id";
        public static final String 	COL_SPECIALTY	= "specialty";
        public static final String 	COL_LINK_ID			= "link_id";
        public static final String 	COL_LINK			= "link";
        public static final String 	COL_LINK_TITLE		= "link_title";

        // Not searching flags from general search


        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_SEARCH				+ " text NOT NULL, "
                + COL_ORGANIZATION_ID		+ " integer, "
                + COL_ORGANIZATION			+ " text, "
                + COL_LOCATION_ID 			+ " integer, "
                + COL_LOCATION 				+ " text, "
                + COL_MEMBER_ID 			+ " integer, "
                + COL_PERSON_ID 			+ " integer, "

                + COL_FIRST_NAME 			+ " text, "
                + COL_LAST_NAME 			+ " text, "

                + COL_ADDRESS 				+ " text, "
                + COL_CITY 					+ " text, "
                + COL_STATE 				+ " text, "

                + COL_SPECIALTY_ID 			+ " integer, "
                + COL_SPECIALTY 			+ " text, "
                + COL_LINK_ID				+ " integer, "
                + COL_LINK 					+ " text, "
                + COL_LINK_TITLE 			+ " text"
                + ");";
    }


    public static final class SearchSpecialties {
        private SearchSpecialties() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "search_specialties";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_SEARCH			= "search";
        public static final String 	COL_SPECIALTY_ID	= "specialty_id";
        public static final String 	COL_SPECIALTY		= "specialty";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_SEARCH				+ " text NOT NULL, "
                + COL_SPECIALTY_ID 			+ " integer, "
                + COL_SPECIALTY 			+ " text"
                + ");";
    }

    public static final class SearchOrganizations {
        private SearchOrganizations() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "search_organizations";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_SEARCH			= "search";
        public static final String 	COL_ORGANIZATION_ID	= "organization_id";
        public static final String 	COL_ORGANIZATION	= "organization";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_SEARCH				+ " text NOT NULL, "
                + COL_ORGANIZATION_ID 		+ " integer, "
                + COL_ORGANIZATION 			+ " text"
                + ");";
    }

    public static final class SearchLinks {
        private SearchLinks() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "search_links";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_SEARCH			= "search";
        public static final String 	COL_LINK_ID			= "link_id";
        public static final String 	COL_LINK			= "link";
        public static final String 	COL_TITLE			= "title";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_SEARCH				+ " text NOT NULL, "
                + COL_LINK_ID 				+ " integer, "
                + COL_LINK 					+ " text, "
                + COL_TITLE					+ " text"
                + ");";
    }


    public static final class SearchPeopleBySpecialty {
        private SearchPeopleBySpecialty() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "search_people_by_specialty";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_SEARCH			= "search";

        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_FIRST_NAME		= "first_name";
        public static final String 	COL_LAST_NAME		= "last_name";
        public static final String 	COL_CITY			= "city";
        public static final String 	COL_STATE			= "state";


        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_SEARCH 				+ " text NOT NULL, "

                + COL_PERSON_ID 			+ " integer, "
                + COL_FIRST_NAME 			+ " text, "
                + COL_LAST_NAME 			+ " text, "
                + COL_CITY 					+ " text, "
                + COL_STATE 				+ " text"
                + ");";
    }


    public static final class SearchLocationByOrganization {
        private SearchLocationByOrganization() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "search_location_by_organization";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_SEARCH			= "search";
        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_ADDRESS			= "address";
        public static final String 	COL_CITY			= "city";
        public static final String 	COL_STATE			= "state";
        public static final String 	COL_ZIP				= "zip";


        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_SEARCH 				+ " text NOT NULL, "
                + COL_LOCATION_ID 			+ " integer, "
                + COL_ADDRESS 				+ " text, "
                + COL_CITY 					+ " text, "
                + COL_STATE 				+ " text, "
                + COL_ZIP 					+ " text"
                + ");";
    }


    public static final class SearchPendingFlags {
        private SearchPendingFlags() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "search_pending_flags";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_SEARCH			= "search";
        public static final String 	COL_FLAG_ID			= "flag_id";
        public static final String 	COL_FLAG_BY			= "flag_by";
        public static final String 	COL_FLAG_TIME		= "flag_time";
        public static final String 	COL_FLAG_COMMENT	= "flag_comment";
        public static final String 	COL_ORGANIZATION_ID	= "organization_id";
        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_LINK_ID			= "link_id";
        public static final String 	COL_MEMBER_ID		= "member_id";
        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_SPECIALTY_ID	= "specialty_id";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_SEARCH 				+ " text NOT NULL, "
                + COL_FLAG_ID 				+ " integer, "
                + COL_FLAG_BY 				+ " integer, "
                + COL_FLAG_TIME 			+ " text, "
                + COL_FLAG_COMMENT 			+ " text, "
                + COL_ORGANIZATION_ID		+ " integer, "
                + COL_LOCATION_ID 			+ " integer, "
                + COL_LINK_ID				+ " integer, "
                + COL_MEMBER_ID 			+ " integer, "
                + COL_PERSON_ID 			+ " integer, "
                + COL_SPECIALTY_ID 			+ " integer"
                + ");";
    }

    public static final class SearchApprovedFlags {
        private SearchApprovedFlags() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "search_approved_flags";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_SEARCH			= "search";
        public static final String 	COL_FLAG_ID			= "flag_id";
        public static final String 	COL_FLAG_BY			= "flag_by";
        public static final String 	COL_FLAG_TIME		= "flag_time";
        public static final String 	COL_FLAG_COMMENT	= "flag_comment";
        public static final String 	COL_VERDICT			= "verdict";
        public static final String 	COL_VERDICT_BY		= "verdict_by";
        public static final String 	COL_VERDICT_TIME	= "verdict_time";
        public static final String 	COL_VERDICT_COMMENT	= "verdict_comment";
        public static final String 	COL_ORGANIZATION_ID	= "organization_id";
        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_LINK_ID			= "link_id";
        public static final String 	COL_MEMBER_ID		= "member_id";
        public static final String 	COL_PERSON_ID		= "person_id";
        public static final String 	COL_SPECIALTY_ID	= "specialty_id";

        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_SEARCH 				+ " text NOT NULL, "
                + COL_FLAG_ID 				+ " integer, "
                + COL_FLAG_BY 				+ " integer, "
                + COL_FLAG_TIME 			+ " text, "
                + COL_FLAG_COMMENT 			+ " text, "
                + COL_VERDICT 				+ " integer, "
                + COL_VERDICT_BY 			+ " integer, "
                + COL_VERDICT_TIME 			+ " text, "
                + COL_VERDICT_COMMENT 		+ " text, "
                + COL_ORGANIZATION_ID		+ " integer, "
                + COL_LOCATION_ID 			+ " integer, "
                + COL_LINK_ID				+ " integer, "
                + COL_MEMBER_ID 			+ " integer, "
                + COL_PERSON_ID 			+ " integer, "
                + COL_SPECIALTY_ID 			+ " integer"
                + ");";
    }


    public static final class SearchMap {
        private SearchMap() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "search_map";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        // Meta fields
        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_SEARCH			= "search";
        public static final String 	COL_TIMESTAMP 		= "timestamp";

        // Common/shared fields
        public static final String 	COL_LATITUDE		= "latitude";
        public static final String 	COL_LONGITUDE		= "longitude";
        public static final String 	COL_ADDRESS			= "address";
        public static final String 	COL_CITY			= "city";
        public static final String 	COL_STATE			= "state";
        public static final String 	COL_ZIP				= "zip";
        public static final String 	COL_EMAIL1			= "email1";
        public static final String 	COL_PHONE1			= "phone1";
        public static final String 	COL_PHONE2			= "phone2";

        // Person/Member fields
        public static final String 	COL_FIRST_NAME		= "first_name";
        public static final String 	COL_LAST_NAME		= "last_name";

        // Person fields
        public static final String 	COL_PERSON_ID		= "person_id";

        // Member fields
        public static final String 	COL_MEMBER_ID		= "member_id";
        public static final String 	COL_LOCATION_TIME	= "location_time";

        // OrganizationLocation fields
        public static final String 	COL_LOCATION_ID		= "location_id";
        public static final String 	COL_ORGANIZATION	= "organization";
        public static final String 	COL_PRIMARY			= "primary";
        public static final String 	COL_EMAIL2			= "email2";



        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 					+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_SEARCH 				+ " text NOT NULL, "
                + COL_TIMESTAMP 			+ " text NOT NULL, "

                + COL_LATITUDE 				+ " real, "
                + COL_LONGITUDE 			+ " real, "
                + COL_ADDRESS 				+ " text, "
                + COL_CITY 					+ " text, "
                + COL_STATE 				+ " text, "
                + COL_ZIP 					+ " text, "
                + COL_EMAIL1 				+ " text, "
                + COL_PHONE1 				+ " text, "
                + COL_PHONE2 				+ " text, "

                + COL_FIRST_NAME 			+ " text, "
                + COL_LAST_NAME 			+ " text, "

                + COL_PERSON_ID 			+ " integer, "

                + COL_MEMBER_ID 			+ " integer, "
                + COL_LOCATION_TIME 		+ " text, "

                + COL_LOCATION_ID 			+ " integer, "
                + COL_ORGANIZATION 			+ " text, "
                + COL_PRIMARY 				+ " integer, "
                + COL_EMAIL2 				+ " text"
                + ");";
    }




    public static final class SearchTerms {
        private SearchTerms() { /* Not instantiable */ }

        public static final String 	TABLE_NAME 			= "search_terms";
        public static final Uri 	CONTENT_URI 		= Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

        public static final String 	CONTENT_TYPE 		= ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + TABLE_NAME;
        public static final String 	CONTENT_ITEM_TYPE	= ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + TABLE_NAME;

        public static final String 	COL_ID 				= BaseColumns._ID;
        public static final String 	COL_TIMESTAMP		= "timestamp";
        public static final String 	COL_SEARCH			= "search";
        public static final String 	COL_SCOPE			= "scope";


        public static final int		SCOPE_UNKNOWN				= 0;
        public static final int		SCOPE_ALL					= 1;
        public static final int		SCOPE_SPECIALTIES			= 2;
        public static final int		SCOPE_MAP					= 3;
        public static final int		SCOPE_ORGANIZATIONS			= 4;
        public static final int		SCOPE_LINKS					= 5;
        public static final int		SCOPE_PEOPLE_BY_SPECIALTY	= 6;
        public static final int		SCOPE_LOCATIONS				= 7;
        public static final int		SCOPE_PENDING_FLAGS			= 8;
        public static final int		SCOPE_APPROVED_FLAGS		= 9;



        static final String	CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COL_ID 				+ " integer PRIMARY KEY AUTOINCREMENT, "
                + COL_TIMESTAMP 		+ " text NOT NULL, "
                + COL_SEARCH 			+ " text NOT NULL, "
                + COL_SCOPE 			+ " integer NOT NULL"
                + ");";
    }

}
