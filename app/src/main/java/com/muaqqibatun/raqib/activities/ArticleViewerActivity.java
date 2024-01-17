package com.muaqqibatun.raqib.activities;

import androidx.fragment.app.Fragment;

import com.muaqqibatun.raqib.fragments.ArticleFragment;
import com.muaqqibatun.raqib.fragments.ArticleViewerFragment;

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

