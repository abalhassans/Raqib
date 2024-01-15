package com.muaqqibatun.raqib;

import androidx.fragment.app.Fragment;

import java.util.UUID;

/**
 * Created by abalhassans on 7/15/2015.
 */
public class ArticleViewerActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        UUID articleID = (UUID)getIntent().getSerializableExtra(ArticleFragment.ARTICLE_ID);
        return ArticleViewerFragment.getInstance(articleID);
    }
}

