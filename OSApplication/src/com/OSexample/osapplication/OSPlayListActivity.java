package com.OSexample.osapplication;

import java.util.ArrayList;
import java.util.List;

import playList.YoutubeListItem;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.OSexample.Search.OSYoutubeVO;

public class OSPlayListActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.play_list);

		// YoutubeListViewに表示する要素を作成する
		List<YoutubeListItem> list = new ArrayList<YoutubeListItem>();
		
		//ほんとはここで各マイリストに対応した情報をfor文かなんかで取得する
		list.add(new YoutubeListItem("Ks-_Mh1QhMc",
				"Sample song", BitmapFactory.decodeResource(
						getResources(), R.drawable.ic_launcher),"Ks-_Mh1QhMc"));

		// アダプタにListをセット
		PlayListViewItemAdapter adapter = new PlayListViewItemAdapter(this, 0, list);

		// PlayListViewを取得する
		ListView listView = (ListView) findViewById(R.id.song_playlist_view);

		// ListViewにアダプタをセット
		listView.setAdapter(adapter);		
		
		// ListViewの要素がタッチされたときに呼び出されるイベントリスナーとして無名クラスをセット
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			// override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 選択した要素を取得
				ListView listView = (ListView) parent;
				YoutubeListItem item = (YoutubeListItem) listView
						.getItemAtPosition(position);
				Intent intent;
				
				switch (item.getTitle()) {
				//要実装
				case "Sample song":
					// OSSearchActivityに遷移するためのインテントを作成
					intent = new Intent(OSPlayListActivity.this,
							OSPlayFromPlayListActivity.class);
					// インテントにvideoIDをセット
					intent.putExtra("videoid", item.getVideoid());
					// ImageViewActivityへ遷移
					startActivity(intent);
					break;
				}
			}
		});
		
		
        
        
	}
	

	
	
	/**
	 * PlayListにセットするアダプタクラス
	 */
	private class PlayListViewItemAdapter extends ArrayAdapter<YoutubeListItem> {
		private LayoutInflater layoutInflater;

		/**
		 * コンストラクタ
		 */
		public PlayListViewItemAdapter(Context context, int textViewResourceId,
				List<YoutubeListItem> objects) {
			// スーパークラスのコンストラクタを呼び出す
			super(context, textViewResourceId, objects);
			// LayoutInflaterを取得
			layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		}

		/**
		 * PlayListViewの各行が表示する要素を返すメソッド
		 */
		// @Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// convertViewがnullだった場合のみLayoutInflaterを利用して"listview_item.xml"からビューを取得する
			if (convertView == null) {
				//ひとまず流用してスタートのリストと同じもの使う
				convertView = layoutInflater.inflate(R.layout.listview_item,
						null);
			}
			// position行目のデータを取得
			YoutubeListItem listItem = (YoutubeListItem) getItem(position);

			// ImageViewに画像をセット
			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.imageView_Item);
			imageView.setImageBitmap(listItem.getImage());

			// TextViewに文字をセット
			TextView textView = (TextView) convertView
					.findViewById(R.id.textView_Item);
			textView.setText(listItem.getTitle());

			// covertViewを返す
			return convertView;
		}
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