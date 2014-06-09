package com.OSexample.osapplication;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class OSCreateFoldersActivity extends Activity {

	// ContextMenuアイテムid
	static final int CONTEXT_MENU1_ID = 0;
	
	public static ArrayAdapter<String> adapter;
	//フォルダのオブジェクトをフィールドで定義
	public static List<Object> folder; 

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_new_folder);

		// ListViewオブジェクトの取得
		ListView listView = (ListView) findViewById(R.id.list_add_view);

		// ArrayAdapterオブジェクト生成
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);

		// Button生成
		Button Listadd_button = (Button) findViewById(R.id.folder_add_button);

		// クリックイベントの通知先指定
		Listadd_button.setOnClickListener(new OnClickListener() {
			// クリックイベント
			@Override
			public void onClick(View v) {
				// 要素追加
				addStringData();
			}
		});
		// Adapterのセット
		listView.setAdapter(adapter);
		// コンテキストメニュー登録
		registerForContextMenu(listView);

	}

	// 要素（Stringデータ）追加処理
	private void addStringData() {

		// EditTextオブジェクト取得
		EditText edit = (EditText) findViewById(R.id.edit_folder_name);

		// EditText(テキスト)を取得し、アダプタに追加
		adapter.add(edit.getText().toString());

	}

	/*
	 * コンテキストメニュー生成時処理
	 */
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo info) {
		super.onCreateContextMenu(menu, view, info);
		AdapterContextMenuInfo adapterinfo = (AdapterContextMenuInfo) info;
		ListView listView = (ListView) view;

		// コンテキストメニューのタイトル
		menu.setHeaderTitle((String) listView
				.getItemAtPosition(adapterinfo.position));
		// メニュー出し
		menu.add(0, CONTEXT_MENU1_ID, 0, "削除する");

	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {// ContextMenuアイテム選択イベント
		switch (item.getItemId()) {
		case CONTEXT_MENU1_ID:
			// 削除項目を選択されたテキスト名取得
			EditText edit = (EditText) findViewById(R.id.edit_folder_name);
			// 取得されたテキストの削除
			adapter.remove(edit.getText().toString());
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
}