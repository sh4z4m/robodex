package com.robodex.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.robodex.R;
import com.robodex.Robodex;

public class ItemListActivity extends BaseActivity implements ItemListFragment.Callbacks {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(ItemListFragment.ARG_MAIN_ITEM_ID,
                    getIntent().getStringExtra(ItemListFragment.ARG_MAIN_ITEM_ID));
            ItemListFragment fragment = new ItemListFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.category_container, fragment)
                    .commit();
        }
    }
	
	    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {    	    	
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


	@Override
	public void onCategoryItemSelected(int position) {
		if (Robodex.DEBUG) {     		
        	Toast.makeText(this,"category item selected, position: " + position, Toast.LENGTH_SHORT).show();        	
        }
		
		Intent details = new Intent(this, DetailActivity.class);
		details.putExtra(DetailFragment.ARG_MAIN_ITEM_ID, getIntent().getStringExtra(ItemListFragment.ARG_MAIN_ITEM_ID));
		details.putExtra(DetailFragment.ARG_CATEGORY_ITEM_ID, position + "");
		startActivity(details);				
	}
}
