package com.muaqqibatun.raqib;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.UUID;

/**
 * Created by abalhassans on 7/12/2015.
 */
public class Article {


    private UUID mId;
    private String mAuthor;
    private String mTitle;
    private String mDescription;
    private String mBody;
    private URI mAudio; //TODO change to MultiMedia (Youtube, SoundCloud, ...)
    //private String Translation
    //private Date Alerts
    private static final String ID = "UUID";
    private static final String AUTHOR = "AUTHOR";
    private static final String TITLE = "TITLE";
    private static final String DESCR = "DESCR";
    private static final String BODY = "BODY";
    private static final String AUDIO = "AUDIO";  //TODO ArrayList of media types URLs, URL Type (Youtube, Media Player,...etc)


    public Article() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        this.mId = id;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String mBody) {
        this.mBody = mBody;
    }

    public URI getAudio() {
        return mAudio;
    }

    public void setAudio(URI mAudio) {
        this.mAudio = mAudio;
    }


    public Article(JSONObject JSONArticle) throws JSONException {
        if (JSONArticle.has(ID))
            mId = UUID.fromString(JSONArticle.getString(ID));
        if (JSONArticle.has(AUTHOR))
            mAuthor = JSONArticle.getString(AUTHOR);
        if (JSONArticle.has(TITLE))
            mTitle = JSONArticle.getString(TITLE);
        if (JSONArticle.has(DESCR))
            mDescription = JSONArticle.getString(DESCR);
        if (JSONArticle.has(BODY))
            mBody = JSONArticle.getString(BODY);
        if (JSONArticle.has(AUDIO))
            mAudio = null;
    }

    public JSONObject toJSON() throws JSONException {

        JSONObject JSONArticle = new JSONObject();
        JSONArticle.put(ID, mId.toString());
        JSONArticle.put(AUTHOR, mAuthor);
        JSONArticle.put(TITLE, mTitle);
        JSONArticle.put(DESCR, mDescription);
        JSONArticle.put(BODY, mBody);
        JSONArticle.put(AUDIO, mAudio);

        return JSONArticle;


    }


}
