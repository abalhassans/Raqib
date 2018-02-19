package com.muaqqibatun.raqib;

import android.annotation.TargetApi;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import java.util.UUID;

/**
 * Created by abalhassans on 7/15/2015.
 */
public class ArticleEditorFragment extends ArticleFragment {

    public static ArticleFragment getInstance(UUID ArticleID) {
        Bundle args = new Bundle();
        args.putSerializable(ARTICLE_ID, ArticleID);

        ArticleFragment frag = new ArticleEditorFragment();
        frag.setArguments(args);

        return frag;
    }

    @TargetApi(11)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TextFormatter.getFormatter(getActivity()).getTypeface();

        View view = inflater.inflate(R.layout.frg_article_edit, container, false);

        EditText mTitleEditText = (EditText) view.findViewById(R.id.article_title_edittext);
        mTitleEditText.setText(myArticle.getTitle());
        mTitleEditText.addTextChangedListener(new myTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myArticle.setTitle(s.toString());
            }
        });
        mTitleEditText.setTypeface(TextFormatter.getFormatter(getActivity()).getTypeface());


        EditText mAuthorEditText = (EditText) view.findViewById(R.id.article_author_edittext);
        mAuthorEditText.setText(myArticle.getAuthor());
        mAuthorEditText.addTextChangedListener(new myTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myArticle.setAuthor(s.toString());
            }
        });
        mAuthorEditText.setTypeface(TextFormatter.getFormatter(getActivity()).getTypeface());



        EditText mDescriptionEditText = (EditText) view.findViewById(R.id.article_description_edittext);
        mDescriptionEditText.setText(myArticle.getDescription());
        mDescriptionEditText.addTextChangedListener(new myTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myArticle.setDescription(s.toString());
            }
        });
        mDescriptionEditText.setTypeface(TextFormatter.getFormatter(getActivity()).getTypeface());

        EditText mBodyEditText = (EditText) view.findViewById(R.id.article_body_edittext);
        mBodyEditText.setText(myArticle.getBody());
        mBodyEditText.setTypeface(TextFormatter.getFormatter(getActivity()).getTypeface());  //Arabic Font
        mBodyEditText.addTextChangedListener(new myTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myArticle.setBody(s.toString());
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            try {
                if(NavUtils.getParentActivityName(getActivity())!=null)

                    getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
            } catch (NullPointerException e) {

            }
        }

        return view;

    }
}

abstract class myTextWatcher implements TextWatcher{

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }



    @Override
    public void afterTextChanged(Editable s) {

    }
}

