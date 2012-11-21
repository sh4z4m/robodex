package com.robodex.app;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.robodex.R;
import com.robodex.Robodex;

//import com.example.oats.library.DatabaseHandler;
//import com.example.oats.library.UserLogin;

import com.robodex.data.DatabaseHandler;
import com.robodex.request.UserLogin;

public class LoginActivity extends Activity {
	Button   btnLogin;
	Button   btnLinkToRegister;
	EditText inputUser;
	EditText inputPassword;
	TextView loginErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS    = "success";
	private static String KEY_ERROR      = "error";
	private static String KEY_ERROR_MSG  = "error_msg";
	private static String KEY_UID        = "uid";
	private static String KEY_NAME       = "name";
	private static String KEY_user      = "user";
	private static String KEY_CREATED_AT = "created_at";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		inputUser    = (EditText) findViewById(R.id.loginUser);
		inputPassword = (EditText) findViewById(R.id.loginPassword);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);
		btnLogin      = (Button)   findViewById(R.id.btnLogin);

		// Login button Click Event
		btnLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				String user    = inputUser.getText().toString();
				String password = inputPassword.getText().toString();
				UserLogin userLogin = new UserLogin();
				JSONObject json = userLogin.loginUser(user, password);				
				Log.d("Button", "Login");
				
				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						loginErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS); 
						if(Integer.parseInt(res) == 1){
							
							DatabaseHandler db   = new DatabaseHandler(getApplicationContext());
							JSONObject json_user = json.getJSONObject("user");
														
							userLogin.logoutUser(getApplicationContext());
							db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_user), json.getString(KEY_UID), json_user.getString(KEY_CREATED_AT));						
														
							Intent dashboard = new Intent(getApplicationContext(), MainActivity.class);
														
							dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(dashboard);
							
							// Close Login Screen
							finish();
						}else{
							loginErrorMsg.setText("Please check your username/password");
							
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

	}
}
