package com.robodex.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.robodex.R;

public class DetailFragment extends SherlockFragment {
	public static final String ARG_CATEGORY_ITEM_ID = "category_item_id";
	public static final String ARG_MAIN_ITEM_ID = "main_item_id";
	
	private String mMainItem;
    private String mCategoryItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (getArguments().containsKey(ARG_MAIN_ITEM_ID)) {
            mMainItem = getArguments().getString(ARG_MAIN_ITEM_ID);
        }
        
        if (getArguments().containsKey(ARG_CATEGORY_ITEM_ID)) {
            mCategoryItem = getArguments().getString(ARG_CATEGORY_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        if (mMainItem != null && mCategoryItem != null) {
            ((TextView) rootView.findViewById(R.id.detail)).setText(mMainItem + ", " + mCategoryItem);
        }
        return rootView;
    }
}
