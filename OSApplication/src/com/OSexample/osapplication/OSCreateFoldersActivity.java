package com.OSexample.osapplication;

import com.OSexample.Search.OSYoutubeResultFactor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class OSCreateFoldersActivity extends Activity {

	// ArrayAdapterオブジェクト生成
	public static ArrayAdapter<String> playListAdapter;

	// ListViewの定義
	public static ListView listView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_folder_0610);
		// ArrayAdapterオブジェクト生成
		playListAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);

		// Button生成
		Button Listadd_button = (Button) findViewById(R.id.folder_add_button);

		// クリックイベントの通知先指定
		// Addボタン押した後の処理
		Listadd_button.setOnClickListener(new OnClickListener() {
			// クリックイベント
			@Override
			public void onClick(View v) {
				// EditTextオブジェクト取得
				EditText edit = (EditText) findViewById(R.id.edit_folder_name);
				String fileName = edit.getText().toString();

				// 要素追加
				addStringData();
				// EditText(テキスト)を取得し、アダプタに追加
				playListAdapter.add(fileName);
			}

			/**
			 * Stringのデータにオブジェクトを登録する
			 */
			private void addStringData() {

				// EditText(テキスト)を取得し、アダプタに追加
				// ListViewにアダプタをset
				ListView listView = (ListView) findViewById(R.id.playlist_view);
				listView.setAdapter(playListAdapter);

				// コンテキストメニュー登録
				registerForContextMenu(listView);
				// リストをクリックした後の処理
				listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// 動画再生が画面に遷移するためのインテントを作成
						Intent playListIntent = new Intent(
								OSCreateFoldersActivity.this,
								OSPlayListActivity.class);
						// OSPlayListActivityへ遷移
						startActivity(playListIntent);
					}
				});

			}
		});
	}
}