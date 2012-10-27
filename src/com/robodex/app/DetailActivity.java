package com.robodex.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.robodex.R;
import com.robodex.Robodex;

public class DetailActivity extends BaseActivity {
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle arguments = new Bundle();
            arguments.putString(DetailFragment.ARG_MAIN_ITEM_ID,
                    getIntent().getStringExtra(ItemListFragment.ARG_MAIN_ITEM_ID));
            arguments.putString(DetailFragment.ARG_CATEGORY_ITEM_ID,
                    getIntent().getStringExtra(ItemListFragment.ARG_CATEGORY_ITEM_ID));
            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.detail_container, fragment)
                    .commit();
        }
    }
	
	    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {    	
    	if (Robodex.DEBUG) {     		
        	Toast.makeText(this,"detail option: " + item.getTitle(), Toast.LENGTH_SHORT).show();        	
        }
    	
    	switch (item.getItemId()) {
    	case android.R.id.home:
    		Intent parentActivityIntent = new Intent(this, ItemListActivity.class);
    		parentActivityIntent.putExtra(ItemListFragment.ARG_MAIN_ITEM_ID, getIntent().getStringExtra(ItemListFragment.ARG_MAIN_ITEM_ID));
    		parentActivityIntent.putExtra(ItemListFragment.ARG_CATEGORY_ITEM_ID, getIntent().getStringExtra(ItemListFragment.ARG_CATEGORY_ITEM_ID));
    		parentActivityIntent.addFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP /* |
                    Intent.FLAG_ACTIVITY_NEW_TASK  */ );
            startActivity(parentActivityIntent);
            finish();
            return true;
    	}

    	return super.onOptionsItemSelected(item);
    }
}
