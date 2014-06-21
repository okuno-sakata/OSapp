package playList;

import android.graphics.Bitmap;

public class YoutubeListItem {

	private String url;
	private String title;
	private Bitmap img;
	private String videoid;

	/**
	 * コンストラクタ
	 * 
	 * @param String
	 *            url youtubeのurl
	 * @param String
	 *            title youtubeのtitle
	 * @param img
	 *            画像ファイルを変換して作成したビットマップ
	 */
	public YoutubeListItem(String url, String title, Bitmap img,String videoid) {
		this.url = url;
		this.title = title;
		this.img = img;
		this.videoid = videoid;
	}

	// Getter
	public String getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public Bitmap getImage() {
		return img;
	}
	public String getVideoid(){
		return videoid;
	}
}
