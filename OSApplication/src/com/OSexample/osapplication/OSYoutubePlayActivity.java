package com.OSexample.osapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class OSYoutubePlayActivity extends Activity {

	/**
	 * YouTubeを再生するためのクラス
	 * OSYoutubeResuleListViewActivityから遷移してくるもの
	 */
	private String youtubeURL;
	
	// YouTubeのDeveloperKeyを取得するためのフィールド定義
	public static String DEVELOPER_KEY;
	
	public void onCreate(Bundle savedInstanceState){
		//スーパークラスのonCreateを呼び出す
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		youtubeURL = intent.getStringExtra("youtubeURL");
	}
}
