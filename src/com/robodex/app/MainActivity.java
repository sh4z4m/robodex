package com.robodex.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.robodex.R;

public class MainActivity extends BaseActivity implements MainListFragment.Callbacks {
	
	private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (findViewById(R.id.fragment_container) != null) {
            mTwoPane = true; 
            ((MainListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.list))
                    .setActivateOnItemClick(true);
        }
    }

	@Override
	public void onItemSelected(int position) {
		if (Robodex.DEBUG) {     		
        	Toast.makeText(this,"position: " + position, Toast.LENGTH_SHORT).show();        	
        }
		
		if (mTwoPane) {
			Bundle arguments = new Bundle();
            arguments.putString(DetailFragment.ARG_ITEM_ID, position + "");
			DetailFragment fragment = new DetailFragment();
	            fragment.setArguments(arguments);
	            getSupportFragmentManager().beginTransaction()
	                    .replace(R.id.detail_container, fragment)
	                    .commit();
		}
		else {
			Intent details = new Intent(this, DetailActivity.class);
			details.putExtra(DetailFragment.ARG_ITEM_ID, position + "");
			startActivity(details);		
		}		
	}
}
