package com.OSexample.Search;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.OSexample.osapplication.R;
import com.OSexample.osapplication.R.id;
import com.OSexample.osapplication.R.layout;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * OSYoutubeResultListActivityから遷移し、ここで動画を再生する
 * 
 */
public class OSYoutubePlayActivity extends YouTubeBaseActivity implements
		OnInitializedListener, OnClickListener {
	private static final int RECOVERY_DIALOG_REQUEST = 1;
	// URLのフィールド
	private static String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		// Intentで渡されたURLをセット
		OSYoutubePlayActivity.url = intent.getStringExtra("youtubeURL");
		// Video IDに変換
		video_id(url);
		setContentView(R.layout.youtube_player_fragment);

		// YoutubePlayerViewにDeveloperキーを設定
		YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		youTubeView.initialize(OSYoutubeDeveloperKey.DEVELOPER_KEY, this);

		// btnの実装
		Button btn = (Button) findViewById(R.id.youtube_add_button);
		btn.setOnClickListener(this);
	}

	@Override
	public void onInitializationFailure(YouTubePlayer.Provider provider,
			YouTubeInitializationResult errorReason) {
		// 初期化に失敗したときの処理
		if (errorReason.isUserRecoverableError()) {
			// エラー回避が可能な場合のエラーダイアログを表示
			errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
		} else {
			// エラー回避が不可能な場合、トーストのみを表示
			String errorMessage = String.format(errorReason.toString());
			Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider,
			YouTubePlayer player, boolean wasRestored) {
		// YouTubeの動画IDを設定
		if (!wasRestored) {
			String video_id = OSYoutubePlayActivity.video_id(url);
			player.cueVideo(video_id);
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.youtube_add_button):
			AlertDialog.Builder alertDlg = new AlertDialog.Builder(this);
			alertDlg.setTitle("My Listへの追加");
			alertDlg.setMessage("My Listへ追加しますか？");
			// TODO ここで作成してあるリストを表示し、どこに追加するかせんたくできるようにする。

			// TODO ここでも新しいフォルダが作成できるようにする。

			alertDlg.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// YouTubeのObjectを作成 URLと//Video IDも作成する
							createYoutubeInfo(url, video_id(url));
							// TODO 作ったオブジェクトをデータ格納処理へ渡す。

						}
					});
			alertDlg.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			alertDlg.create().show();
			break;
		}
	}

	/**
	 * YouTubeのURLでv=以下にあるVideo IDを取得する
	 * 
	 */
	private static String video_id(String url) {
		String video_id = url.substring(url.indexOf("=") + 1);
		return video_id;
	}

	/**
	 * YouTubeのオブジェクトを生成する
	 * 
	 */
	public static Object createYoutubeInfo(String url, String videoId) {
		ArrayList<Object> youtubeInfo = new ArrayList<>();

		OSYoutubeVO youtubeVO = new OSYoutubeVO(url, videoId);

		youtubeInfo.add(youtubeVO);

		return youtubeInfo;
	}

}