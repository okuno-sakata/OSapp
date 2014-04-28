package com.OSexample.osapplication;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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



public class ListViewActivity extends Activity {

	/**
	 * ListViewに表示する要素のクラス
	 * 
	 */
	private class ListViewItem
	{
		private int resourceID;
		private String fileName;
		private Bitmap img;
		
		/**
		 * コンストラクタ
		 * @param resource_id 画像ファイルのリソースID
		 * @param String fileName 画像ファイルのファイル名
		 * @param img 画像ファイルを変換して作成したビットマップ
		 */
		public ListViewItem(int resource_id, String file_name, Bitmap img)
		{
			this.resourceID = resource_id;
			this.fileName = file_name;
			this.img = img;
		}
		
		//Getter
		public int getResourceID(){
			return resourceID;
		}
		public String getFileName(){
			return fileName;
		}
		public Bitmap getImage(){
			return img;
		}
	}
		
		/**
		 * Listviewにセットするアダプタクラス
		 */
		private class ListViewItemAdapter extends ArrayAdapter<ListViewItem>{
			private LayoutInflater layoutInflater;
			
			/**
			 * コンストラクタ
			 */			
			public ListViewItemAdapter(Context context,
					int textViewResourceId, List<ListViewItem> objects) {
				//スーパークラスのコンストラクタを呼び出す
				super(context, textViewResourceId, objects);
				//LayoutInflaterを取得
				layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
			}

			/**
			 * ListViewの各行が表示する要素を返すメソッド
			 */
		//@Override
		public View getView(int position,View convertView, ViewGroup parent){
			//convertViewがnullだった場合のみLayoutInflaterを利用して"listview_item.xml"からビューを取得する
			if(convertView == null){
				convertView = layoutInflater.inflate(R.layout.listview_item, null);
			}
			//position行目のデータを取得
			ListViewItem item = (ListViewItem)getItem(position);
			
			//ImageViewに画像をセット
			ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView_Item);
			imageView.setImageBitmap(item.getImage());
			
			//TextViewに文字をセット
			TextView textView = (TextView)convertView.findViewById(R.id.textView_Item);
			textView.setText(item.getFileName());
			
			//covertViewを返す
			return convertView;
		}
		}
		
		/*
		 * アクティビティ作成時に呼び出されるメソッド
		 */
		//override
		public void onCreate(Bundle savedInstanceState){
			//スーパークラスのonCreateを呼び出す
			super.onCreate(savedInstanceState);
			
			//コンテントビューに"listView.xml"をセット
			setContentView(R.layout.listview);
			
			//ListViewに表示する要素を作成する
			List<ListViewItem> list = new ArrayList<ListViewItem>();
			list.add(new ListViewItem (
					R.drawable.ic_launcher,"My PlayList",
					BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher) ));
			list.add(new ListViewItem (
					R.drawable.ic_launcher,"Create New Folder",
					BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher) ));
			list.add(new ListViewItem (
					R.drawable.ic_launcher,"chinchin",
					BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher) ));
			
			//アダプタにListをセット
			ListViewItemAdapter adapter = new ListViewItemAdapter(this ,0 ,list);
			
			//ListViewを取得する
		    ListView listView = (ListView)findViewById(R.id.listView);
			
			//ListViewにアダプタをセット
			listView.setAdapter(adapter);
			
			//ListViewの要素がタッチされたときに呼び出されるイベントリスナーとして無名クラスをセット
			listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				//override
				public void onItemClick(AdapterView<?> parent,View view,int position,long id){
					//選択した要素を取得
					ListView listView = (ListView)parent;
					ListViewItem item = (ListViewItem)listView.getItemAtPosition(position);
					
					//ImageViewActivityに遷移するためのインテントを作成
					Intent intent = new Intent(ListViewActivity.this,ImageViewActivity.class);
					
					//インテントに選択した要素のresourceID値をセット
					intent.putExtra("resourceID", item.getResourceID());
					
					//ImageViewActivityへ遷移
					startActivity(intent);					
					}
				});
			}
		}
	
//	/** Called when the activity is first created. */
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//	    super.onCreate(savedInstanceState);
//	
//	    // TODO Auto-generated method stub
//	}
