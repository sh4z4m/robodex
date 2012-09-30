package com.robodex.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.robodex.R;

public class MainActivity extends SherlockFragmentActivity implements MainListFragment.Callbacks {
	
	private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (findViewById(R.id.detail_container) != null) {
            mTwoPane = true; 
            ((MainListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
        getSupportMenuInflater().inflate(R.menu.main, menu);
        (menu.findItem(R.id.menu_search))
        .setActionView(R.layout.collapsible_edittext)
        .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {    	
    	if (Robodex.DEBUG) {     		
        	Toast.makeText(this,"option: " + item.getTitle(), Toast.LENGTH_SHORT).show();        	
        }

    	return super.onOptionsItemSelected(item);
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
