package com.robodex.request;


final class Fields {
    // HTTP Post variable names
    static final String REQUEST 						= "request";
    static final String HASH   			 				= "hash";

    // All requests share these
    static final String REQUEST_TYPE 					= "request_type";
    static final String SESSION_ID     					= "session_id";

    // Valid request types
    static final String TYPE_LOGIN       				= "login";
    static final String TYPE_CHECK_IN       			= "check_in";

    static final String TYPE_DETAIL_PERSON   			= "detail_person";
    static final String TYPE_DETAIL_LOCATION    		= "detail_location";

    static final String TYPE_LIST_SPECIALTIES 			= "list_specialties";
    static final String TYPE_LIST_COORDINATES    		= "list_coordinates";
    static final String TYPE_LIST_ORGANIZATIONS   		= "list_organizations";
    static final String TYPE_LIST_LINKS    				= "list_links";
    static final String TYPE_LIST_PENDING_FLAGS   	 	= "list_pending_flags";
    static final String TYPE_LIST_ROLES    				= "list_roles";
    static final String TYPE_LIST_PEOPLE_BY_SPECIALTY 	= "list_people_by_specialty";
    static final String TYPE_LIST_LOCATIONS     		= "list_locations";
    static final String TYPE_LIST_APPROVED_FLAGS  		= "list_approved_flags";

    static final String TYPE_SEARCH_ALL      			= "search_all";
    static final String TYPE_SEARCH_SPECIALTIES 		= "search_specialties";
    static final String TYPE_SEARCH_MAP      			= "search_map";
    static final String TYPE_SEARCH_ORGANIZATIONS    	= "search_organizations";
    static final String TYPE_SEARCH_LINKS               = "search_links";
    static final String TYPE_SEARCH_PEOPLE_BY_SPECIALTY	= "search_people_by_specialty";
    static final String TYPE_SEARCH_locations     		= "search_locations";
    static final String TYPE_SEARCH_PENDING_FLAGS    	= "search_pending_flags";
    static final String TYPE_SEARCH_APPROVED_FLAGS 		= "search_approved_flags";

    static final String TYPE_CREATE_PERSON    			= "create_person";
    static final String TYPE_CREATE_SPECIALTY  			= "create_specialty";
    static final String TYPE_CREATE_ORGANIZATION 		= "create_organization";
    static final String TYPE_CREATE_LINK   				= "create_link";
    static final String TYPE_CREATE_LOCATION   			= "create_location";
    static final String TYPE_CREATE_FLAG   				= "create_flag";

    static final String TYPE_EDIT_SPECIALTY  			= "edit_specialty";
    static final String TYPE_EDIT_ORGANIZATION 			= "edit_organization";
    static final String TYPE_EDIT_LINK   				= "edit_link";
    static final String TYPE_EDIT_PERSON   				= "edit_person";
    static final String TYPE_EDIT_LOCATION    			= "edit_location";
    static final String TYPE_EDIT_FLAG     				= "edit_flag";
    static final String TYPE_EDIT_ROLE      	 		= "edit_role";


    // Several request types use this
    static final String	START_POSITION 					= "start_position";





    // Address/location fields
    static final String ADDRESS  						= "address";
    static final String CITY  							= "city";
    static final String STATE  							= "state";
    static final String ZIP  							= "zip";
    static final String LATITUDE  						= "latitude";
    static final String LONGITUDE  						= "longitude";

    // Common contact fields. Watch out for numbered vs not-numbered (email vs email1)
    static final String EMAIL							= "email";
    static final String EMAIL1  						= "email1";
    static final String EMAIL2  						= "email2";
    static final String PHONE  							= "phone";
    static final String PHONE1  						= "phone1";
    static final String PHONE2  						= "phone2";

    // Common flag fields
    static final String REMOVE  						= "remove";
    static final String REMOVE_APPROVED 				= "remove_approved";

    // Other fields
    static final String SPECIALTY_ID 					= "specialty_id";
    static final String SPECIALTY 						= "specialty";



    // Shared response variable names
    static final String RESPONSE_CODE  					= "response_code";
    static final String RESPONSE_MESSAGE   				= "response_message";
    static final String RESULTS   						= "results";

    static final int 	ERROR_CODE_OK					= 0;




    // TODO complete this as each class is made
    static String getRequestType(Class<? extends BaseRequest> cls) {
        if (cls == Login.class)         return TYPE_LOGIN;
        if (cls == SpecialtyList.class) return TYPE_LIST_SPECIALTIES;

        return null;
    }

    // TODO figure out storage
    static String getSessionId() {

        return "";
    }
}
