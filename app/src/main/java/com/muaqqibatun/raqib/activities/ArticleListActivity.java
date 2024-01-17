package com.muaqqibatun.raqib.activities;

import androidx.fragment.app.Fragment;

import com.muaqqibatun.raqib.fragments.ArticleListFragment;

/**
 * Created by abalhassans on 7/14/2015.
 */
public class ArticleListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ArticleListFragment();
    }
}
