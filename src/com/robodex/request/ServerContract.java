package com.robodex.request;

/**
 * Specifies the data that the server and app understand
 * so they can communicate it between one another.
 */
final class ServerContract {

	/**
	 * The types of requests the app can make
	 */
	static final class RequestType {

		static final String LOGIN       				= "login";
	    static final String CHECK_IN       				= "check_in";

	    static final String DETAIL_PERSON   			= "detail_person";
	    static final String DETAIL_LOCATION    			= "detail_location";

	    static final String LIST_SPECIALTIES 			= "list_specialties";
	    static final String LIST_MAP    				= "list_coordinates";
	    static final String LIST_ORGANIZATIONS   		= "list_organizations";
	    static final String LIST_LINKS    				= "list_links";
	    static final String LIST_PENDING_FLAGS   	 	= "list_pending_flags";
	    static final String LIST_ROLES    				= "list_roles";
	    static final String LIST_PEOPLE_BY_SPECIALTY 	= "list_people_by_specialty";
	    static final String LIST_LOCATIONS_BY_ORGANIZATION     			= "list_locations";
	    static final String LIST_APPROVED_FLAGS  		= "list_approved_flags";
	    // TODO update documentation
	    static final String LIST_LAST_EDITED_BY_MEMBER	= "list_last_edited_by_member";

	    static final String SEARCH_ALL      			= "search_all";
	    static final String SEARCH_SPECIALTIES 			= "search_specialties";
	    static final String SEARCH_MAP      			= "search_map";
	    static final String SEARCH_ORGANIZATIONS    	= "search_organizations";
	    static final String SEARCH_LINKS               	= "search_links";
	    static final String SEARCH_PEOPLE_BY_SPECIALTY	= "search_people_by_specialty";
	    static final String SEARCH_LOCATIONS_BY_ORGANIZATION	= "search_locations";
	    static final String SEARCH_PENDING_FLAGS    	= "search_pending_flags";
	    static final String SEARCH_APPROVED_FLAGS 		= "search_approved_flags";

	    static final String CREATE_PERSON    			= "create_person";
	    static final String CREATE_SPECIALTY  			= "create_specialty";
	    static final String CREATE_ORGANIZATION 		= "create_organization";
	    static final String CREATE_LINK   				= "create_link";
	    static final String CREATE_LOCATION   			= "create_location";
	    static final String CREATE_FLAG   				= "create_flag";

	    static final String EDIT_SPECIALTY  			= "edit_specialty";
	    static final String EDIT_ORGANIZATION 			= "edit_organization";
	    static final String EDIT_LINK   				= "edit_link";
	    static final String EDIT_PERSON   				= "edit_person";
	    static final String EDIT_LOCATION    			= "edit_location";
	    static final String EDIT_FLAG     				= "edit_flag";
	    static final String EDIT_ROLE      	 			= "edit_role";
	}


	/**
	 * Fields that can be in an HTTP request understood by the server.
	 */
	static final class RequestField {

		/*
		 * HTTP POST fields
		 */

		// Except for the hash, everything else is embedded inside the request field.
	    static final String REQUEST 					= "request";
	    static final String HASH   			 			= "hash";


	    /*
	     * Fields shared between request types
	     */

	    // All requests share these
	    // The auth key may be empty for login requests
	    static final String REQUEST_TYPE 				= "request_type";
	    static final String AUTH_KEY     				= "auth_key";

	    // Endless list types of requests use this to get the next x results.
	    static final String	START_POSITION 				= "start_position";

	    // Address/location fields
	    static final String ADDRESS  					= "address";
	    static final String CITY  						= "city";
	    static final String STATE  						= "state";
	    static final String ZIP  						= "zip";
	    static final String LATITUDE  					= "latitude";
	    static final String LONGITUDE  					= "longitude";

	    // Common contact fields.
	    static final String EMAIL1  					= "email1";
	    static final String EMAIL2  					= "email2";
	    static final String PHONE1  					= "phone1";
	    static final String PHONE2  					= "phone2";

	    // Other common fields
	    static final String REMOVE  					= "remove";
	    static final String REMOVE_APPROVED 			= "remove_approved";
	    static final String MODIFIED_BY					= "modified_by";
	    static final String MODIFIED_TIME				= "modified_time";


	    /*
         * Request-type-specific fields
         */

	    // Specialty fields
	    static final String SPECIALTY_ID 				= "specialty_id";
	    static final String SPECIALTY 					= "specialty";

	    // Organization
	    static final String ORGANIZATION_ID 			= "organization_id";
        static final String ORGANIZATION 				= "organization";

        // Organization Location
        static final String LOCATION_ID					= "location_id";
        static final String PRIMARY						= "primary_location";

        // User Flags
        static final String FLAG_ID						= "flag_id";
        static final String FLAG_BY						= "flag_by";
        static final String FLAG_TIME					= "flag_time";
        static final String FLAG_COMMENT				= "flag_comment";
        static final String VERDICT						= "verdict";
        static final String VERDICT_BY					= "verdict_by";
        static final String VERDICT_TIME				= "verdict_time";
        static final String VERDICT_COMMENT				= "verdict_comment";

        // Hyperlinks
        static final String LINK_ID						= "link_id";
        static final String LINK						= "link";
        static final String LINK_TEXT					= "title";
        static final String TOP_LEVEL					= "top_level";

        // Member
        static final String MEMBER_ID					= "member_id";
        static final String MEMBER_EMAIL				= "member_email";
        static final String USERNAME					= "username";
        static final String PASSWORD					= "password";
        static final String LOCATION_TIME				= "location_time";
        static final String EDITS						= "edits";
        static final String EDITS_TODAY					= "edits_today";
        static final String LOGINS						= "logins";
        static final String LOGIN_TIME					= "login_time";
        static final String EXPIRE_DATE					= "expire_date";
        static final String DISCLAIMER_ACCEPT			= "disclaimer_accept";

        // Person
        static final String PERSON_ID					= "person_id";
        static final String FIRST_NAME					= "first_name";
        static final String LAST_NAME					= "last_name";
        static final String NOTES						= "notes";

        // Role
        static final String ROLE_ID						= "role_id";
        static final String ROLE						= "role";
        static final String DAILY_EDITS					= "daily_edits";
        static final String EDIT_TOP_LINKS				= "edit_top_links";
        static final String FLAG_FOR_REMOVAL			= "flag_for_removal";
        static final String APPROVE_REMOVAL				= "approve_removal";
        static final String UNREMOVE					= "unremove";
        static final String CHANGE_MEMBER_ROLES			= "change_member_roles";
        static final String MANAGE_ROLES				= "manage_roles";

        // Search
        static final String SEARCH_TERMS				= "search_terms";
	}


	/**
	 * Fields that can be in a response understood by the app.
	 */
	static final class ResponseField {
		 // All responses must have these
	    static final String RESPONSE_CODE  				= "response_code";
	    static final String RESPONSE_MESSAGE   			= "response_message";
	    // Send this array even if results are empty
	    static final String RESULTS   					= "results";
	}


	/**
	 * Response codes given by the server
	 */
	static final class ResponseCode {
		static final int OK								= 0;

		// Business logic errors
		static final int INVALID_USERNAME				= 10;
		static final int INVALID_EMAIL					= 11;
		static final int INVALID_PASSWORD				= 12;
		static final int INVALID_REQUEST_TYPE			= 13;
		static final int INVALID_COORD_BOX				= 14;
		static final int PERMISSION_DENIED				= 15;
		static final int INVALID_AUTH_KEY				= 16;
		static final int EXPIRED_AUTH_KEY				= 17;
		static final int INVALID_SEARCH_TYPE			= 18;
		static final int EDIT_LIMIT_REACHED				= 19;
		static final int ACCOUNT_SUSPENDED				= 20;

		// General database errors
		static final int INVALID_ID						= 100;

		// Database read errors
		static final int NO_RESULTS						= 110;
		static final int START_PAST_END					= 111;
		static final int PERSON_WITHOUT_MEMBER			= 112;

		// Database write errors
		static final int UNKNOWN_FIELD					= 120;
		static final int INSUFFICIENT_DATA				= 121;
		static final int INVALID_DATA_TYPE				= 122;
		static final int INVALID_DATA_COMBINATION		= 123;

		// Other errors
		static final int UNKNOWN_ERROR					= 900;
		static final int OUT_OF_MEMORY					= 901;
	}
}
