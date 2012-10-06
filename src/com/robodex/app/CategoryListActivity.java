package com.robodex.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.robodex.R;

public class CategoryListActivity extends SherlockFragmentActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(CategoryListFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(CategoryListFragment.ARG_ITEM_ID));
            CategoryListFragment fragment = new CategoryListFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.category_container, fragment)
                    .commit();
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
	    	
	    	switch (item.getItemId()) {
	    	case android.R.id.home:
	    		Intent parentActivityIntent = new Intent(this, MainActivity.class);
	    		parentActivityIntent.addFlags(
	                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
	                    Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(parentActivityIntent);
	            finish();
	            return true;
	    	}

	    	return super.onOptionsItemSelected(item);
	    }
}
