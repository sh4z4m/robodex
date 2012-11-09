package com.robodex.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.robodex.R;
import com.robodex.Robodex;

public class MainActivity extends BaseActivity implements MainListFragment.Callbacks,
ItemListFragment.Callbacks {
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

        if (position == 1) {
            startActivity(new Intent(this, MyMapActivity.class));
        }
        else if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(ItemListFragment.ARG_MAIN_ITEM_ID, mMainItem);
            ItemListFragment fragment = new ItemListFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit();
        }
        else {
            Intent category = new Intent(this, ItemListActivity.class);
            category.putExtra(ItemListFragment.ARG_MAIN_ITEM_ID, mMainItem);
            startActivity(category);
        }
    }


    @Override
    public void onItemSelected(int position) {
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
            Log.wtf(LOG_TAG, "Going from main activity to detail activity");

            Intent details = new Intent(this, DetailActivity.class);
            details.putExtra(DetailFragment.ARG_MAIN_ITEM_ID, mMainItem);
            details.putExtra(DetailFragment.ARG_CATEGORY_ITEM_ID, mCategoryItem);
            startActivity(details);
        }
    }
}
