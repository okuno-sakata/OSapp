package com.OSexample.Search;

import com.OSexample.osapplication.R;
import com.OSexample.osapplication.R.id;
import com.OSexample.osapplication.R.layout;
import com.OSexample.osapplication.R.menu;

import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;

public class OSSearchActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_menu_first_searchactivity);
		Button button_searchbox = (Button) findViewById(R.id.open_searchbox);
		button_searchbox.setOnClickListener(clicked);
	}

	private View.OnClickListener clicked = new View.OnClickListener() {
		public void onClick(View v) {
			Log.v("Button", "onClick");
			switch (v.getId()) {
			case R.id.open_searchbox:
				onSearchRequested();
				break;
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.search_menu_searchactivity, menu);
		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchableInfo searchableInfo = searchManager
				.getSearchableInfo(getComponentName());
		MenuItem menuItem = menu.findItem(R.id.action_search);
		SearchView searchView = (SearchView) menuItem.getActionView();
		searchView.setSearchableInfo(searchableInfo);
		searchView.setIconifiedByDefault(false);
		searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
		return true;
	}

	@Override
	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		if (Intent.ACTION_SEARCH.equals(intent.getAction()) == true) {
			String query = intent.getStringExtra(SearchManager.QUERY);
	         //Intent intent = new Intent(MainActivity.this, sub.class);
	         Intent resultintent = new Intent(OSSearchActivity.this, OSYoutubeResultListViewActivity.class);
	         // Parameter is edit text for sub activity
	         resultintent.putExtra("query", query);
	         // Call substitute activity
	         startActivity(resultintent);
		}
	}
}