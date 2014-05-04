package com.OSexample.osapplication;

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

public class OSApp_ListActivity extends Activity {
	static final int CONTEXT_MENU1_ID = 0;//ContextMenuアイテムid
  ArrayAdapter<String> adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listaddfolder);

        //ListViewオブジェクトの取得
        ListView listView=(ListView)findViewById(R.id.LISTadd_view);

        //ArrayAdapterオブジェクト生成
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        //Buttonオブジェクト取得
        Button Listadd_button=(Button)findViewById(R.id.Listadd_button);

        //クリックイベントの通知先指定
        Listadd_button.setOnClickListener(new OnClickListener() {

          //クリックイベント
      @Override
      public void onClick(View v) {
        //要素追加
        addStringData();
      }
    });
        //Adapterのセット
        listView.setAdapter(adapter);
     // コンテキストメニュー登録
        registerForContextMenu(listView);

    }
    //要素（Stringデータ）追加処理
    private void addStringData(){

      //EditTextオブジェクト取得
      EditText edit=(EditText)findViewById(R.id.edit_text_list);

      //EditText(テキスト)を取得し、アダプタに追加
      adapter.add(edit.getText().toString());

	}

    /*
     * コンテキストメニュー生成時処理
     */
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenuInfo info) {
    	super.onCreateContextMenu(menu, view, info);
    	AdapterContextMenuInfo adapterinfo = (AdapterContextMenuInfo)info;
    	ListView listView = (ListView) view;

    	//コンテキストメニューのタイトル
    	menu.setHeaderTitle((String)listView.getItemAtPosition(adapterinfo.position));
    	//メニュー出し
    	menu.add(0,CONTEXT_MENU1_ID,0,"削除する");

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {//ContextMenuアイテム選択イベント
    switch (item.getItemId()) {
    case CONTEXT_MENU1_ID:
     //削除項目を選択されたテキスト名取得
     EditText edit=(EditText)findViewById(R.id.edit_text_list);
     //取得されたテキストの削除
     adapter.remove(edit.getText().toString());

    return true;
    default:
        return super.onContextItemSelected(item);
        }
       }


}

//package com.OSexample.osapplication;
//
//import android.app.Activity;
//import android.app.ActionBar;
//import android.app.Fragment;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.os.Build;
//
//public class MainActivity extends Activity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        if (savedInstanceState == null) {
//            getFragmentManager().beginTransaction()
//                    .add(R.id.container, new PlaceholderFragment())
//                    .commit();
//        }
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * A placeholder fragment containing a simple view.
//     */
//    public static class PlaceholderFragment extends Fragment {
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            return rootView;
//        }
//    }
//
//}
