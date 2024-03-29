package com.muaqqibatun.raqib.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.core.app.NavUtils;

import android.view.MenuItem;

import com.muaqqibatun.raqib.Article;
import com.muaqqibatun.raqib.Library;

import java.util.UUID;

/**
 * Created by abalhassans on 7/14/2015.
 */
public class ArticleFragment extends Fragment {

    public static final String ARTICLE_ID = "ARTICLE_ID";
    private static final String TAG = "ARTICLE_FRAGMENT";
    protected Article myArticle = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID ArticleID = (UUID) getArguments().getSerializable(ARTICLE_ID);
        myArticle = Library.getInstance(getActivity()).getArticle(ArticleID);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                if (NavUtils.getParentActivityName(getActivity()) != null) {
                    NavUtils.navigateUpFromSameTask(getActivity());
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
