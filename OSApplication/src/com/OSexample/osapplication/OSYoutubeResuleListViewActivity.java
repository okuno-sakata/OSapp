package com.OSexample.osapplication;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

/**
 * OSSearchActivityで検索した結果のYouTubeをリストで表示するクラス
 * 
 */
public class OSYoutubeResuleListViewActivity extends Activity implements OnClickListener {

	// 引数として渡されるqueryのフィールド定義
	private String query;
	// YouTubeのDeveloperKeyを取得するためのフィールド定義
	public static String DEVELOPER_KEY;

	private String searchWord;
    private Button button1;

	// コンストラクタ
	public OSYoutubeResuleListViewActivity(){
	}
	
	public OSYoutubeResuleListViewActivity(String query) {
		this.query = query;
	}

	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //Intentの取得
	        Intent intent = this.getIntent();
	        searchWord = intent.getStringExtra("query");

	        //Layoutの定義
	        setContentView(R.layout.osyoutube_listview);

	        //検索結果画面から検索画面にもどる
	        button1 = (Button)findViewById(R.id.button_return);
	        button1.setOnClickListener(this);
	        button1.setId(1);
	        
	        // HttpResponseの生成
	        HttpResponse httpResponse = null;
	        
	        //URLの定義
	        String Url = "http://gdata.youtube.com/feeds/api/videos?v=2&alt=jsonc";
	        try {
	        	//UTF-8 エンコード対応
	            Url += "&q=" + URLEncoder.encode(searchWord, "UTF-8");
	        } catch(UnsupportedEncodingException e1){
	        	//exception対応
	            e1.printStackTrace();
	        	}
	        HttpUriRequest httpGet = new HttpGet(Url);
	        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
	        try {
	            // YouTubeからレスポンスデータを取得する 
	        	httpResponse = defaultHttpClient.execute(httpGet);
	        } catch (Exception e) { 
	            //Log.e("HttpResponse", "Error Execute"); 
	        } finally{
	        	
	        }
	        
	        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	             try {
	                 // レスポンスデータをjsonオブジェクトへ変換する
	                 JSONObject json = new JSONObject(EntityUtils.toString(httpResponse.getEntity()));
	                 // jsonオブジェクトを配列とする
	                 JSONArray jsonArray = json.getJSONObject("data").getJSONArray("items");
	                 // DefaultHttpClient shutdown
	                 defaultHttpClient.getConnectionManager().shutdown();
	                 // Custom Class list creates to store the feed data
	                 List<OSYoutubeResultFactor> objects = new ArrayList<OSYoutubeResultFactor>();
	                 
	                 Bitmap bitmap;
	                 OSYoutubeResultFactor imageData = new OSYoutubeResultFactor();
	                 for (int i = 0; i < jsonArray.length(); ++i) {
	                     // jsonarray から JSONObjectを配列の長さ分取得する 
	                     JSONObject jsonObject = jsonArray.getJSONObject(i);
	                     // Title gets from JSONObject
	                     String title = jsonObject.getString("title");
	                     String url; // Mobile URL
	                     try {
	                         // URL of mobile gets from JSONObject
	                         url = jsonObject.getJSONObject("player").getString("mobile");
	                         //Log.v("TAG", "url1");
	                     } catch (JSONException e) {
	                         url = jsonObject.getJSONObject("player").getString("default");
	                         //Log.v("TAG", "url2");
	                     }
	                     // サムネイルの取得
	                     String thumbnail = jsonObject.getJSONObject("thumbnail").getString("sqDefault");
	                     // thumbnail String converts as Bitmap
	                     bitmap = imageData.GetBitmap(thumbnail);
	                     // ListView at the made object added
	                     objects.add(new OSYoutubeResultFactor(title,url, bitmap));	   
	                 }    
	                 // Adapter creates from the made object
	                 OSYoutubeResultAdapter imageAdapter = new OSYoutubeResultAdapter(this, 0, objects);
	                 ListView listView =  (ListView) findViewById(R.id.osyoutube_listview_result);
	                 // CustomAdapter sets to ListView
	                 listView.setAdapter(imageAdapter);
	             } catch (JSONException e) {
	                 
	             } catch (ParseException e) {
	                 e.printStackTrace();
	             } catch (IOException e) {
	                 e.printStackTrace();
	             }
	        }
	        
	        //youtubeの要素をクリックした後の処理
	        
	        
	        }
	//
	// protected void onCreate(Bundle savedInstanceState) {
	// super.onCreate(savedInstanceState);
	// setContentView(R.layout.activity_main);
	//
	// // フラグメントインスタンスを取得
	// YouTubePlayerFragment youTubePlayerFragment =
	// (YouTubePlayerFragment)
	// getFragmentManager().findFragmentById(R.id.youtube_fragment);
	//
	// // フラグメントのプレーヤーを初期化する
	// youTubePlayerFragment.initialize("DEVELOPER_KEY", this);
	// }
	//
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	//
	// @Override
	// public void onInitializationFailure(Provider provider,
	// YouTubeInitializationResult error) {
	// // プレーヤーの初期化失敗時に呼ばれる
	// }
	//
	// @Override
	// public void onInitializationSuccess(Provider provider, YouTubePlayer
	// player,
	// boolean wasRestored) {
	// // プレーヤーの初期化成功時に呼ばれる
	// if (!wasRestored) {
	// // 指定された動画のサムネイルを読み込み、プレーヤーがその動画を再生する準備を行う
	// player.cueVideo("nCgQDjiotG0");
	// }
	//
	// }

    public void onClick(View v) {
        switch (v.getId()) {
        case 1:
            Intent data = new Intent();
            data.putExtra("keyword", "Return");
            setResult(RESULT_OK, data);
            // End of sub activity
            finish();
            break;
        default:
            break;
        }
    }

}
