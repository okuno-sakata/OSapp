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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OSPlayListActivity extends Activity {
	
	//VideoIDのリストフィールド
	private static List<String> videoidlist;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.play_list);

		// YoutubeListViewに表示する要素を作成する
		List<YoutubeListItem> list = new ArrayList<YoutubeListItem>();

		// ほんとはここで各マイリストに対応した情報をfor文かなんかで取得する
		list.add(new YoutubeListItem("Ks-_Mh1QhMc", "Sample song",
				BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_launcher), "Ks-_Mh1QhMc"));
		list.add(new YoutubeListItem("oBS7KOuYujQ", "Sample song1",
				BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_launcher), "oBS7KOuYujQ"));
		list.add(new YoutubeListItem("XEgHyaeSFkk", "Sample song2",
				BitmapFactory.decodeResource(getResources(),
						R.drawable.ic_launcher), "S7hrRU1s6go"));
		//VideoIdのリストを作成
		OSPlayListActivity.setVideoidlist(makeVideoIDList(list));

		// アダプタにListをセット
		PlayListViewItemAdapter adapter = new PlayListViewItemAdapter(this, 0,
				list);

		// PlayListViewを取得する
		ListView listView = (ListView) findViewById(R.id.song_playlist_view);

		// ListViewにアダプタをセット
		listView.setAdapter(adapter);

		// ListViewの要素がタッチされたときに呼び出されるイベントリスナーとして無名クラスをセット
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// 選択した要素を取得
				ListView listView = (ListView) parent;
				YoutubeListItem item = (YoutubeListItem) listView
						.getItemAtPosition(position);
				Intent intent;

				switch (item.getTitle()) {
				// 要実装
				default:
					// OSSearchActivityに遷移するためのインテントを作成
					intent = new Intent(OSPlayListActivity.this,
							OSPlayFromPlayListActivity.class);
					// インテントにvideoIDをセット
					intent.putExtra("videoid", item.getVideoid());
					// // PlayListが所属する曲の全videoidのリストをセット
					// intent.putExtra("videoidList",videoIDList);
					// ImageViewActivityへ遷移
					startActivity(intent);
					break;
				}
			}
		});

	}

	private List<String> makeVideoIDList(List<YoutubeListItem> list) {
		// video Id の配列作成
		List<String> videoIDList = new ArrayList<>();
		// Listの分だけアイテムをvideoIDListに追加
		for (int i = 0; i < list.size(); i++) {
			videoIDList.add(list.get(i).getVideoid());
		}
		return videoIDList;
	}

	public static List<String> getVideoidlist() {
		return videoidlist;
	}
	public static void setVideoidlist(List<String> videoidlist) {
		OSPlayListActivity.videoidlist = videoidlist;
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
				// ひとまず流用してスタートのリストと同じもの使う
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
}