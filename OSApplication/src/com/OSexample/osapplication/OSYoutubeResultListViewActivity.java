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

import com.OSexample.Search.OSYoutubeResultAdapter;
import com.OSexample.Search.OSYoutubeResultFactor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ParseException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

/**
 * OSSearchActivityで検索した結果のYouTubeをリストで表示するクラス
 * 
 */
public class OSYoutubeResultListViewActivity extends Activity implements
		OnClickListener {

	private String searchWord;
	private Button button1;

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
	
    //YouTubeの要素をクリックした後の処理
	ListView listView =  (ListView) findViewById(R.id.osyoutube_listview_result);
	listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent,View view,int position,long id){
			//選択した要素を取得
			ListView listView = (ListView)parent;
			OSYoutubeResultFactor youtubeResultFactor = (OSYoutubeResultFactor)listView.getItemAtPosition(position);
			
			//動画再生が画面に遷移するためのインテントを作成
			Intent playIntent = new Intent(OSYoutubeResultListViewActivity.this,OSYoutubePlayActivity.class);
			
			//インテントに選択した要素のresourceID値をセット
			playIntent.putExtra("youtubeURL", youtubeResultFactor.getUrl());
			
			//ImageViewActivityへ遷移
			startActivity(playIntent);					
		}
	});
    }

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
