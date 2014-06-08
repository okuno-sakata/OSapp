package com.OSexample.osapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerView;

public class OSYoutubePlayActivity extends YouTubeBaseActivity implements
		OnInitializedListener {
	private static final int RECOVERY_DIALOG_REQUEST = 1;
	private static String url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		OSYoutubePlayActivity.url = intent.getStringExtra("youtubeURL");
		video_id(url);
		setContentView(R.layout.youtube_player_fragment);

		// YoutubePlayerViewにDeveloperキーを設定
		YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		youTubeView.initialize(OSYoutubeDeveloperKey.DEVELOPER_KEY, this);
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
			String errorMessage = String.format(

			errorReason.toString());
			Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
		}
	}

	@Override
	public void onInitializationSuccess(YouTubePlayer.Provider provider,
			YouTubePlayer player, boolean wasRestored) {
		// YouTubeの動画IDを設定
		if (!wasRestored) {
			String video_id =  this.video_id(url);
			player.cueVideo(video_id);
		}
	}

	private String video_id(String url) {
		String video_id = url.substring(url.indexOf("=") + 1);
		return video_id;
	}

}