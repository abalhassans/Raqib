package com.muaqqibatun.raqib;

import android.support.v4.app.Fragment;

import java.util.UUID;


public class ArticleEditorActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {

        UUID articleID = (UUID) getIntent().getSerializableExtra(ArticleFragment.ARTICLE_ID);
        return ArticleEditorFragment.getInstance(articleID);
    }
}
