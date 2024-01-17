package com.muaqqibatun.raqib.fragments;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.ListFragment;

import com.muaqqibatun.raqib.Article;
import com.muaqqibatun.raqib.Library;
import com.muaqqibatun.raqib.activities.MyPreferenceActivity;
import com.muaqqibatun.raqib.R;
import com.muaqqibatun.raqib.utils.TextFormatter;
import com.muaqqibatun.raqib.activities.ArticleEditorActivity;
import com.muaqqibatun.raqib.activities.ArticleViewerActivity;
import com.muaqqibatun.raqib.serializer.DataException;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by abalhassans on 7/14/2015.
 */
public class ArticleListFragment extends ListFragment {


    private static final String TAG = ArticleListFragment.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getActivity()).setTitle(R.string.app_name);
        setHasOptionsMenu(true);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.frg_article_listview, container, false);

        ListView lview = (ListView) view.findViewById(android.R.id.list);
        lview.setAdapter(new ArticleAdapter(Library.getInstance(getActivity()).getArticles()));
        lview.setEmptyView(view.findViewById(android.R.id.empty));

        Button addButton = (Button) view.findViewById(R.id.add_article_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArticleActivity();


            }
        });

        lview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

            }

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_article_list_context, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {


                Log.d(TAG, "In OnActionItemClicked " + item.getItemId());
                //Toast.makeText(getActivity(), "Within OnActionItemClicked", Toast.LENGTH_SHORT).show();

                int id = item.getItemId();

                if(id == R.id.menu_item_article_delete) {

                    SparseBooleanArray selected = getListView().getCheckedItemPositions();
                    for (int i = getListView().getCount(); i > 0; i--) {
                        if (selected.get(i)) {
                            Article article = (Article) getListView().getAdapter().getItem(i);
                            Toast.makeText(getActivity(), "Removing " + article.getTitle(), Toast.LENGTH_SHORT).show();
                            Library.getInstance(getActivity()).removeArticle(article.getId());
                            ((ArticleAdapter) getListView().getAdapter()).notifyDataSetChanged();
                        }
                    }
                    mode.finish();
                    return true;
                } else if (id == R.id.menu_item_article_edit) {

                    Article selectedArticle = null;

                    SparseBooleanArray selected = getListView().getCheckedItemPositions();
                    for (int i = getListView().getCount(); i > 0; i--) {
                        if (selected.get(i)) {
                            selectedArticle = (Article) getListView().getAdapter().getItem(i);
                            break;
                        }
                    }
                    mode.finish();

                    Toast.makeText(getActivity(), "Editing " + selectedArticle.getTitle(), Toast.LENGTH_SHORT).show();
                    editArticleActivity(selectedArticle);


                    return true;
                } else {
                    return false;
                }

            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {


            }
        });

        return view;
    }

    private void editArticleActivity(Article article) {

        Intent i = new Intent(getActivity(), ArticleEditorActivity.class);
        i.putExtra(ArticleFragment.ARTICLE_ID, article.getId());
        startActivity(i);
    }

    private void addArticleActivity() {
        Article article = new Article();
        Library.getInstance(getActivity()).addArticle(article);

        Intent i = new Intent(getActivity(), ArticleEditorActivity.class);
        i.putExtra(ArticleFragment.ARTICLE_ID, article.getId());
        startActivity(i);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_article_list, menu);

        return;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id == R.id.action_settings) {
            openPreferencePanel();
            return true;
        }else if(id == R.id.menu_item_article_list_addarticle) {
            addArticleActivity();
            return true;
        }else{
            return super.onOptionsItemSelected(item);
        }

    }

    private void openPreferencePanel() {

        Intent intent = new Intent(getActivity(), MyPreferenceActivity.class);
        startActivity(intent);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Article article = ((ArticleAdapter) getListView().getAdapter()).getItem(position);
        Intent intent = new Intent(getActivity(), ArticleViewerActivity.class);
        intent.putExtra(ArticleFragment.ARTICLE_ID, article.getId());
        startActivity(intent);
    }

    private class ArticleAdapter extends ArrayAdapter<Article> {

        public ArticleAdapter(ArrayList<Article> articles) {
            super(getActivity(), 0, articles);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = Objects.requireNonNull(getActivity()).getLayoutInflater().inflate(R.layout.frg_article_listitem, null);
            }
            Article article = getItem(position);

            TextView titleTextView = (TextView) convertView.findViewById(R.id.list_item_article_title_textview);
            titleTextView.setText(article.getTitle());
            titleTextView.setTypeface(TextFormatter.getFormatter(getActivity()).getTypeface());

            TextView authorTextView = (TextView) convertView.findViewById(R.id.list_item_article_author_textview);
            authorTextView.setText(article.getAuthor());
            authorTextView.setTypeface(TextFormatter.getFormatter(getActivity()).getTypeface());

            return convertView;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ArticleAdapter) getListView().getAdapter()).notifyDataSetChanged();
    }


    @Override
    public void onPause() {
        super.onPause();

        Library myLibrary = Library.getInstance(getActivity());

        try {
            myLibrary.saveArticles();
            Toast.makeText(getActivity(), "Library Saved: " + Objects.requireNonNull(getActivity()).getExternalFilesDir(null).toString(), Toast.LENGTH_SHORT).show();
        } catch (DataException e) {
            Toast.makeText(getActivity(), "Problems Saving Library" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        Objects.requireNonNull(getActivity()).getMenuInflater().inflate(R.menu.menu_article_list_context, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_article_edit) {
            Log.d(TAG, "edit item selected");
            return true;
        }else if(id == R.id.menu_item_article_delete) {
            Log.d(TAG, "delete item selected");
            return true;
        }else{
            return super.onContextItemSelected(item);
        }
    }
}
