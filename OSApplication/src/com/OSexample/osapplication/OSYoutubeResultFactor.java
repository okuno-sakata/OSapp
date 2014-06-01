package com.OSexample.osapplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * OSYoutubeResultListViewActivityでListを表示する箱の要素を定義するクラス
 * 
 */
public class OSYoutubeResultFactor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Bitmap bitmap;
    private String title;
    private String url;
    private String thumbnail;
    public OSYoutubeResultFactor () {}
    public OSYoutubeResultFactor (String title, String url, String thumbnail) {
        super();
        this.title = title;
        this.url   = url;
        this.thumbnail = thumbnail;
    }
    public OSYoutubeResultFactor (String title, String url ,Bitmap bitmap) {
        super();
        this.title = title;
        this.url = url;
        this.bitmap = bitmap;
    }
    public void setTitle (String title) {
        this.title = title;
    }
    public void setUrl (String url) {
        this.url = url;
    }
    public void setThumbnail (String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    public String getTitle() {
        return this.title;
    }
    public String getUrl() {
        return this.url;
    }
    public String getThumbnail() {
        return this.thumbnail;
    }
    
    public void setImageData (Bitmap bitmap) {
        this.bitmap = bitmap;
    }
    public Bitmap getImageData () {
        return this.bitmap;
    }
    public void setStringData (String string) {
        this.title = string;
    }
    public String getStringData () {
        return this.title;
    }
    public Bitmap GetBitmap (String source) {
        try {
            URL url = new URL(source);
            // URL opens by HttpURLConnection
            HttpURLConnection connection  = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            // つなぐ
            connection.connect();
            // InputStreamをコネクションリザルトから作る
            InputStream inputStream = connection.getInputStream();
            // String data decodes　(bitmap)
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (IOException e) {}
        return null;
    }
}
