package com.muaqqibatun.raqib.serializer;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.muaqqibatun.raqib.Article;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by abalhassans on 7/13/2015.
 */
public class InternalStorageSerializer extends SerializerFactory {

    private static final String TAG = "Int_Storage_Serializer";
    private String mFilename = "raqib.json";
    private Context mContext;

    public InternalStorageSerializer(Context context) {
        this.mContext = context;
    }

    @Override
    public void saveArticles(ArrayList<Article> Articles) throws DataException {
        JSONArray array = new JSONArray();

        try {

            for (Article article : Articles) {
                array.put(article.toJSON());
            }

        } catch (JSONException e) {
            throw new DataException(e.getMessage());
        }

        Writer osw = null;
        try {

            osw = getOutputWriter();
            osw.write(array.toString());

        }  catch (Exception e){
            throw new DataException(e.getMessage());
        }finally {

            if (osw != null) {

                try {
                    osw.close();
                }catch(Exception e){

                }

            }
        }


    }

    @Override
    public ArrayList<Article> loadArticles() throws DataException{
        ArrayList<Article> Articles = new ArrayList<Article>();
        BufferedReader bufferedReader = null;
        StringBuilder JSONString = new StringBuilder();
        String line;
        try {
            bufferedReader = (BufferedReader) getInputReader();

            while ((line = bufferedReader.readLine()) != null)
                JSONString.append(line);

            JSONArray array = new JSONArray(new JSONTokener(JSONString.toString()));

            for (int i = 0; i < array.length(); i++) {
                Articles.add(new Article(array.getJSONObject(i)));
            }

        } catch (Exception e) {
            throw new DataException(e.getMessage());
        } finally {

            if (bufferedReader != null) {

                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return Articles;

    }

    private Reader getInputReader() throws FileNotFoundException {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            InputStream is = new FileInputStream(new File(Environment.getExternalStorageDirectory(), mFilename));
            return new BufferedReader(new InputStreamReader(is));

        } else {
            InputStream is = mContext.openFileInput(mFilename);
            return new BufferedReader(new InputStreamReader(is));

        }
    }

    private Writer getOutputWriter() throws FileNotFoundException {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            OutputStream os = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), mFilename), false);
            return new OutputStreamWriter(os);
        } else {
            OutputStream os = mContext.openFileOutput(mFilename, Context.MODE_PRIVATE);
            return new OutputStreamWriter(os);
        }
    }
}
