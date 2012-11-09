package com.robodex.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.robodex.data.DatabaseContract.Flag;
import com.robodex.data.DatabaseContract.Link;
import com.robodex.data.DatabaseContract.Member;
import com.robodex.data.DatabaseContract.Organization;
import com.robodex.data.DatabaseContract.OrganizationLocation;
import com.robodex.data.DatabaseContract.Person;
import com.robodex.data.DatabaseContract.PersonOrganization;
import com.robodex.data.DatabaseContract.PersonSpecialty;
import com.robodex.data.DatabaseContract.Role;
import com.robodex.data.DatabaseContract.Specialty;

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

    private void initialize(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + Organization.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + OrganizationLocation.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Flag.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Link.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Member.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Person.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PersonOrganization.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PersonSpecialty.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Role.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Specialty.TABLE_NAME);


        db.execSQL(Organization.CREATE_TABLE);
        db.execSQL(OrganizationLocation.CREATE_TABLE);
        db.execSQL(Flag.CREATE_TABLE);
        db.execSQL(Link.CREATE_TABLE);
        db.execSQL(Member.CREATE_TABLE);
        db.execSQL(Person.CREATE_TABLE);
        db.execSQL(PersonOrganization.CREATE_TABLE);
        db.execSQL(PersonSpecialty.CREATE_TABLE);
        db.execSQL(Role.CREATE_TABLE);
        db.execSQL(Specialty.CREATE_TABLE);
    }
}
