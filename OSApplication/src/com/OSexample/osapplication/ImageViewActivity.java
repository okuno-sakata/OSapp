package com.OSexample.osapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {

	/**
	 * アクティビティ作成時に呼び出されるメソッド
	 */
	//override
	public void onCreate(Bundle savedInstanceState){
		//スーパークラスのonCreateを呼び出す
		super.onCreate(savedInstanceState);
		
		//コンテントビューに"imageview.xml"をセット
		setContentView(R.layout.imageview);
		
		//インテントから値を取得
		Intent intent = getIntent();
		int resourceID = intent.getIntExtra("resourceID", 0);
		
		//値が取得できていればその値をもとにImageViewに画像をセット
		if(resourceID != 0){
			//ImageViewを取得して、画像をセット
			ImageView imageView = (ImageView)findViewById(R.id.imageView);
			imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),resourceID));
			
			//ImageViewをタッチされたときに呼び出されるイベントリスナーとして無名クラスをセット
			imageView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {

					//このアクティビティを終了して前のアクティビティに戻る
					finish();
					
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

}
