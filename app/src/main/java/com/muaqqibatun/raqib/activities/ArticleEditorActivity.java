package com.muaqqibatun.raqib.activities;

import androidx.fragment.app.Fragment;

import com.muaqqibatun.raqib.fragments.ArticleEditorFragment;
import com.muaqqibatun.raqib.fragments.ArticleFragment;

import java.util.UUID;


public class ArticleEditorActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {

        UUID articleID = (UUID) getIntent().getSerializableExtra(ArticleFragment.ARTICLE_ID);
        return ArticleEditorFragment.getInstance(articleID);
    }
}
