package com.muaqqibatun.raqib;

import androidx.fragment.app.Fragment;

/**
 * Created by abalhassans on 7/14/2015.
 */
public class ArticleListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ArticleListFragment();
    }
}
