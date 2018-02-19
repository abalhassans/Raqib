package com.muaqqibatun.raqib;

import android.content.Context;
import android.provider.ContactsContract;

import com.muaqqibatun.raqib.serializer.DataException;
import com.muaqqibatun.raqib.serializer.SerializerFactory;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by abalhassans on 7/12/2015.
 */
public class Library {

    private static Library myInstance = null;

    ArrayList<Article> mArticles;
    private Context mContext;
    private SerializerFactory mListSerializer;

    private Library(Context mContext) {
        this.mContext = mContext;


        try {
            loadArticles();
        } catch (DataException e) {
            if(mArticles == null)
                mArticles = new ArrayList<Article>();
            e.printStackTrace();
        }

    }

    public static Library getInstance(Context mContext) {
        if (myInstance == null) {
            myInstance = new Library(mContext);
        }
        return myInstance;
    }

    public ArrayList<Article> getArticles(){
        //TODO determine if you want to return the list itself or a shallow copy

        return mArticles;
    }

    public Article getArticle(UUID uuid) {
        for(Article article: mArticles) {
            if (article.getId().equals(uuid)) {
                return article;
            }
        }
        return null;
    }

    public void addArticle(Article mArticle) {
        mArticles.add(mArticle);
    }

    public void removeArticle(UUID uuid) {

        mArticles.remove(getArticle(uuid));
    }

    public void saveArticles() throws DataException {
        SerializerFactory.getSerializer(mContext).saveArticles(mArticles);

    }

    public void loadArticles() throws DataException{
        mArticles = SerializerFactory.getSerializer(mContext).loadArticles();
    }


}
