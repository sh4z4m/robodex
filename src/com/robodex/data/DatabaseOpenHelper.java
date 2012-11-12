package com.robodex.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.robodex.data.DatabaseContract.ListApprovedFlags;
import com.robodex.data.DatabaseContract.CheckIn;
import com.robodex.data.DatabaseContract.CreateFlag;
import com.robodex.data.DatabaseContract.CreateLink;
import com.robodex.data.DatabaseContract.CreateLocation;
import com.robodex.data.DatabaseContract.CreateOrganization;
import com.robodex.data.DatabaseContract.CreatePerson;
import com.robodex.data.DatabaseContract.CreateSpecialty;
import com.robodex.data.DatabaseContract.EditFlag;
import com.robodex.data.DatabaseContract.EditLink;
import com.robodex.data.DatabaseContract.EditLocation;
import com.robodex.data.DatabaseContract.EditOrganization;
import com.robodex.data.DatabaseContract.EditPerson;
import com.robodex.data.DatabaseContract.EditRole;
import com.robodex.data.DatabaseContract.EditSpecialty;
import com.robodex.data.DatabaseContract.ListLastEditedByMember;
import com.robodex.data.DatabaseContract.ListLinks;
import com.robodex.data.DatabaseContract.ListLocationsByOrganization;
import com.robodex.data.DatabaseContract.Login;
import com.robodex.data.DatabaseContract.ListMap;
import com.robodex.data.DatabaseContract.ListOrganizations;
import com.robodex.data.DatabaseContract.DetailLocation;
import com.robodex.data.DatabaseContract.ListPendingFlags;
import com.robodex.data.DatabaseContract.ListPeopleBySpecialty;
import com.robodex.data.DatabaseContract.DetailPerson;
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

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = DatabaseOpenHelper.class.getSimpleName();

    public DatabaseOpenHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initialize(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(LOG_TAG, "Upgrading database. Existing contents will be lost. ["
                + oldVersion + "]->[" + newVersion + "]");
        initialize(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	Log.w(LOG_TAG, "Downgrading database. Existing contents will be lost. ["
                + oldVersion + "]->[" + newVersion + "]");
        initialize(db);
    }

    private void initialize(SQLiteDatabase db) {
		dropTables(db);
		createTables(db);
    }

    private void dropTables(SQLiteDatabase db) {
    	db.execSQL("DROP TABLE IF EXISTS " + ListApprovedFlags.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + CheckIn.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + CreateFlag.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + CreateLink.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + CreateLocation.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + CreateOrganization.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + CreatePerson.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + CreateSpecialty.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + EditFlag.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + EditLink.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + EditLocation.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + EditOrganization.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + EditPerson.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + EditRole.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + EditSpecialty.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + ListLastEditedByMember.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + ListLocationsByOrganization.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + Login.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + ListMap.TABLE_NAME);
    	db.execSQL("DROP TABLE IF EXISTS " + ListOrganizations.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DetailLocation.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ListPendingFlags.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ListPeopleBySpecialty.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DetailPerson.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ListRoles.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SearchAll.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SearchApprovedFlags.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SearchLinks.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SearchLocationsByOrganization.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SearchMap.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SearchOrganizations.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SearchPendingFlags.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SearchPeopleBySpecialty.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SearchSpecialties.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ListSpecialties.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ListLinks.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + Recent.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Requests.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SearchTerms.TABLE_NAME);
    }

    private void createTables(SQLiteDatabase db) {
    	db.execSQL(ListApprovedFlags.CREATE_TABLE);
    	db.execSQL(CheckIn.CREATE_TABLE);
    	db.execSQL(CreateFlag.CREATE_TABLE);
    	db.execSQL(CreateLink.CREATE_TABLE);
    	db.execSQL(CreateLocation.CREATE_TABLE);
    	db.execSQL(CreateOrganization.CREATE_TABLE);
    	db.execSQL(CreatePerson.CREATE_TABLE);
    	db.execSQL(CreateSpecialty.CREATE_TABLE);
    	db.execSQL(EditFlag.CREATE_TABLE);
    	db.execSQL(EditLink.CREATE_TABLE);
    	db.execSQL(EditLocation.CREATE_TABLE);
    	db.execSQL(EditOrganization.CREATE_TABLE);
    	db.execSQL(EditPerson.CREATE_TABLE);
    	db.execSQL(EditRole.CREATE_TABLE);
    	db.execSQL(EditSpecialty.CREATE_TABLE);
    	db.execSQL(ListLastEditedByMember.CREATE_TABLE);
    	db.execSQL(ListLocationsByOrganization.CREATE_TABLE);
    	db.execSQL(Login.CREATE_TABLE);
    	db.execSQL(ListMap.CREATE_TABLE);
    	db.execSQL(ListOrganizations.CREATE_TABLE);
        db.execSQL(DetailLocation.CREATE_TABLE);
        db.execSQL(ListPendingFlags.CREATE_TABLE);
        db.execSQL(ListPeopleBySpecialty.CREATE_TABLE);
        db.execSQL(DetailPerson.CREATE_TABLE);
        db.execSQL(ListRoles.CREATE_TABLE);
        db.execSQL(SearchAll.CREATE_TABLE);
        db.execSQL(SearchApprovedFlags.CREATE_TABLE);
        db.execSQL(SearchLinks.CREATE_TABLE);
        db.execSQL(SearchLocationsByOrganization.CREATE_TABLE);
        db.execSQL(SearchMap.CREATE_TABLE);
        db.execSQL(SearchOrganizations.CREATE_TABLE);
        db.execSQL(SearchPendingFlags.CREATE_TABLE);
        db.execSQL(SearchPeopleBySpecialty.CREATE_TABLE);
        db.execSQL(SearchSpecialties.CREATE_TABLE);
        db.execSQL(ListSpecialties.CREATE_TABLE);
        db.execSQL(ListLinks.CREATE_TABLE);

        db.execSQL(Recent.CREATE_TABLE);
        db.execSQL(Requests.CREATE_TABLE);
        db.execSQL(SearchTerms.CREATE_TABLE);
    }
}
