package com.muaqqibatun.raqib.serializer;

import android.content.Context;

import com.muaqqibatun.raqib.Article;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * This Class abstracts the loading / saving of source data.
 * Subclass implementation can be
 *  1. Database Local
 *  2. Database Remote
 *  3. FileSystem (Internal Storage)
 *  4. FileSystem (SD Card)
 *  5. Google Drive / DropBox/..etc
 *  .... etc
 *
 *  A Preference File can be utilized to select what method of storage is available.
 *  http://developer.android.com/guide/topics/ui/settings.html
 */
public abstract class SerializerFactory {
    private static SerializerFactory myFactory = null;

    public static final SerializerFactory getSerializer(Context context){

        if(myFactory == null) {

            String className = "com.muaqqibatun.raqib.serializer.InternalStorageSerializer";
            // TODO use the Preference API to select the list of storage locations.
            // Will also need to assign a listener to nullify the factory if preference change

            try {
                Constructor constructor = Class.forName(className).getConstructor(Context.class);
                myFactory =   (SerializerFactory)constructor.newInstance(context);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return myFactory;
    }

    public abstract void saveArticles(ArrayList<Article> Articles) throws DataException;
    public abstract ArrayList<Article> loadArticles() throws DataException;


}
