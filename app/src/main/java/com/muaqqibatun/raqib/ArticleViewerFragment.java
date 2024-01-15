package com.muaqqibatun.raqib;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NavUtils;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by abalhassans on 7/15/2015.
 */
public class ArticleViewerFragment extends ArticleFragment {

    private String TAG = "ArticleViewerFragment";
    private View view = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle(R.string.app_name);
        setHasOptionsMenu(true);
    }

    public static ArticleFragment getInstance(UUID ArticleID) {
        Bundle args = new Bundle();
        args.putSerializable(ARTICLE_ID, ArticleID);

        ArticleFragment frag = new ArticleViewerFragment();
        frag.setArguments(args);

        return frag;
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.frg_article_view, container, false);

        Log.d(TAG, "In OnCreateView - ArticleViewerFragment");

        getActivity().setTitle(R.string.app_name);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(myArticle.getTitle());

        ((AppCompatActivity)getActivity()).getSupportActionBar().setSubtitle(myArticle.getAuthor());

        populateView(view);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            try {
                if (NavUtils.getParentActivityName(getActivity()) != null) {

                    getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);

                }
            } catch (NullPointerException e) {

            }

        }

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        populateView(view);
    }

    private void populateView(View view){


        //TextView titleTextView = (TextView) view.findViewById(R.id.article_title_edittext);
        //titleTextView.setText(myArticle.getTitle()+"\n"+myArticle.getAuthor());
        //titleTextView.setTypeface(typeface);  //Arabic Font

        TextView mBodyTextView = (TextView) view.findViewById(R.id.article_body_textview);
        mBodyTextView.setText(myArticle.getBody());
        mBodyTextView.setTypeface(TextFormatter.getFormatter(getActivity()).getTypeface());  //Arabic Font
        mBodyTextView.setTextSize(TextFormatter.getFormatter(getActivity()).getTextSize());


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_article_viewer, menu);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id == R.id.menu_item_article_viewer_editarticle) {
            editArticleActivity(myArticle);
            displayDetailsDialog(myArticle);
            return true;
        } else if (id == R.id.menu_item_article_details) {
            displayDetailsDialog(myArticle);
            return true;
        } else if (id == R.id.action_settings ) {
            openPreferencePanel();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }

    }

    private void editArticleActivity(Article myArticle) {


        Intent intent = new Intent(getActivity(), ArticleEditorActivity.class);
        intent.putExtra(ArticleFragment.ARTICLE_ID, myArticle.getId());
        startActivity(intent);

    }


    private void displayDetailsDialog(Article myArticle) {
        /*
        TextView mTitleTextView = (TextView) view.findViewById(R.id.article_title_textview);
        mTitleTextView.setText(myArticle.getTitle());

        TextView mAuthorTextView = (TextView) view.findViewById(R.id.article_author_textview);
        mAuthorTextView.setText(myArticle.getAuthor());

        TextView mDescriptionTextView = (TextView) view.findViewById(R.id.article_description_textview);
        mDescriptionTextView.setText(myArticle.getDescription());
        */

    }

    private void openPreferencePanel() {

        Intent intent = new Intent(getActivity(), MyPreferenceActivity.class);
        startActivity(intent);
    }

}
