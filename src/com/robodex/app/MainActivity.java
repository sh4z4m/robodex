package com.robodex.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.robodex.R;

public class MainActivity extends BaseActivity implements MainListFragment.Callbacks,
		CategoryListFragment.Callbacks {
	private static final String LOG_TAG = MainActivity.class.getSimpleName();
	
	private boolean mTwoPane;
	
	private String mMainItem;
	private String mCategoryItem;

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
    public void onMainItemSelected(int position) {
    	if (Robodex.DEBUG) {     		
        	Toast.makeText(this,"main item selected, position: " + position, Toast.LENGTH_SHORT).show();        	
        }
    	
    	mMainItem = String.valueOf(position);
		
		if (mTwoPane) {
			Bundle arguments = new Bundle();
            arguments.putString(CategoryListFragment.ARG_MAIN_ITEM_ID, mMainItem);
            CategoryListFragment fragment = new CategoryListFragment();
	            fragment.setArguments(arguments);
	            getSupportFragmentManager().beginTransaction()
	                    .replace(R.id.fragment_container, fragment)
	                    .commit();
		}
		else {
			Intent category = new Intent(this, CategoryListActivity.class);
			category.putExtra(CategoryListFragment.ARG_MAIN_ITEM_ID, mMainItem);
			startActivity(category);		
		}	
    }
    

	@Override
	public void onCategoryItemSelected(int position) {
		if (Robodex.DEBUG) {     		
        	Toast.makeText(this,"category item selected, position: " + position, Toast.LENGTH_SHORT).show();        	
        }
		
		mCategoryItem = String.valueOf(position);
		
		if (mTwoPane) {
			Bundle arguments = new Bundle();
			arguments.putString(DetailFragment.ARG_MAIN_ITEM_ID, mMainItem);
            arguments.putString(DetailFragment.ARG_CATEGORY_ITEM_ID, mCategoryItem);
            DetailFragment fragment = new DetailFragment();
	            fragment.setArguments(arguments);
	            getSupportFragmentManager().beginTransaction()
	                    .replace(R.id.fragment_container, fragment)
	                    .commit();
		}
		else {
			// This should never happen
			Log.e(LOG_TAG, "Going from main activity to detail activity");		
			
			Intent details = new Intent(this, DetailActivity.class);
			details.putExtra(DetailFragment.ARG_MAIN_ITEM_ID, mMainItem);
            details.putExtra(DetailFragment.ARG_CATEGORY_ITEM_ID, mCategoryItem);			
			startActivity(details);
		}		
	}
}
