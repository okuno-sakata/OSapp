package com.OSexample.osapplication;

import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.OSexample.Search.OSYoutubeDeveloperKey;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * プレイリストを選択し、再生する画面 (PSPlayListActivityから遷移)
 */
public class OSPlayFromPlayListActivity extends YouTubeBaseActivity implements
		OnInitializedListener, OnClickListener {

	private String videoid;
	private static final int RECOVERY_DIALOG_REQUEST = 1;

	private YouTubePlayer player;

	// 動画の再生ボタン
	private Button playButton;
	// 動画の停止ボタン
	private Button pauseButton;
	// 動画のランダムボタン
	private Button randamButton;
	// 動画のネクストボタン
	private Button nextButton;
	// videoIDのリストフィールド
	public static List<String> videoIDList;

	// ランダム用のフラグ
	private boolean randamPlay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		// Intentで渡されたURLをセット
		this.videoid = intent.getStringExtra("videoid");
		setContentView(R.layout.play_from_playlist);
		// YoutubePlayerViewにDeveloperキーを設定
		YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		youTubeView.initialize(OSYoutubeDeveloperKey.DEVELOPER_KEY, this);

		playButton = (Button) findViewById(R.id.play_button);
		pauseButton = (Button) findViewById(R.id.pause_button);
		randamButton = (Button) findViewById(R.id.random_button);
		nextButton =  (Button) findViewById(R.id.next_button);

		playButton.setOnClickListener(this);
		pauseButton.setOnClickListener(this);
		randamButton.setOnClickListener(this);
		nextButton.setOnClickListener(this);

		// 初期状態はランダム再生オフにしておく。
		randamPlay = false;
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
		// Playerの初期化
		this.player = player;
		// YouTubeの動画IDを設定
		if (!wasRestored) {
			if(!randamPlay){
//			String video_id = videoid;
			player.cueVideos(OSPlayListActivity.getVideoidlist());
			player.play();
//			player.cueVideo(video_id);
			}
		}
	}

	@Override
	public void onClick(View v) {
		if (v == playButton) {
			player.play();
		} else if (v == pauseButton) {
			player.pause();
		} else if (v == randamButton) {
			if (!randamPlay) {
				randamPlay = true;
			} else {
				randamPlay = false;
			}
		}else if (v == nextButton){
			player.next();
		}
	}

	// 必要に応じてランダムリストのvideoIDを生成する。
	public List<String> makeRandamList(List<String> list) {
		// ここでランダムのリストを作っておく。
		Collections.shuffle(videoIDList);
		return videoIDList;
	}

}
