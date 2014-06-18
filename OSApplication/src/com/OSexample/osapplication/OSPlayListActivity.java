package com.OSexample.osapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.OSexample.Search.OSYoutubeVO;

public class OSPlayListActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_list);
        
        //ここにPlayListにひもづく曲の一覧を配置
        
        
        
        
	}
	


	/**
	 * String配列の生成
	 */
	private static String[] createStringData(EditText edit) {
		String fileName = edit.getText().toString();
		return dummyyoutubeInfo(fileName);
	}

	/**
	 * YouTubeInfoを取得し、Sring配列にする(オブジェクト配列の方がいいのか。。)
	 */
	private static String[] dummyyoutubeInfo(String fileName) {
		// ひとまずサンプル
		OSYoutubeVO youtubeVO = new OSYoutubeVO();
		youtubeVO.setVideoID("Ks-_Mh1QhMc");
		youtubeVO.setYoutubeURL("https://www.youtube.com/watch?v=Ks-_Mh1QhMc");
		String[] youtubeInfo = new String[] { fileName, youtubeVO.getVideoID(),
				youtubeVO.getYoutubeURL() };
		return youtubeInfo;
	}
}