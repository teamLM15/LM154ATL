package adapter;

import java.util.ArrayList;

import twitter4j.Status;
import util.LruCacheUse;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.neoenox.twitterapp.R;

import data.TwitterStatusData;

public class TwitterTimelineAdapter extends ArrayAdapter<Object> implements ListAdapter {

	private ArrayList<?> items;
	private LayoutInflater inflater;
	private ImageLoader mImageLoader;
	private int textViewResourceId;

	public TwitterTimelineAdapter(Context context, int textViewResourceId, ArrayList<TwitterStatusData> items) {
		super(context, textViewResourceId);
		this.items = items;
		this.textViewResourceId = textViewResourceId;
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mImageLoader = new ImageLoader(Volley.newRequestQueue(getContext()), new LruCacheUse());
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;

		View view = convertView;
		if (view == null) {
			view = inflater.inflate(textViewResourceId, null);

			viewHolder = new ViewHolder();
			viewHolder.screenName = (TextView) view.findViewById(R.id.timeline_name);
			viewHolder.statusText = (TextView) view.findViewById(R.id.timeline_text);
			viewHolder.iconImage = (ImageView) view.findViewById(R.id.timeline_icon);
			viewHolder.retweetImage = (ImageView) view.findViewById(R.id.retweet_icon);
			viewHolder.retweetText = (TextView) view.findViewById(R.id.retweet_name);

			view.setTag(viewHolder);

		}
		else
		{
			viewHolder = (ViewHolder) view.getTag();
		}

		TwitterStatusData item = (TwitterStatusData) items.get(position);
		Log.v("listAdapter", "getView data start");
		Log.v("getView", "name : " + item.getStatus().getUser().getScreenName());
		Log.v("getView", "text : " + item.getStatus().getText());
//		Log.v("getView", "name : " + item.getScreenName());
//		Log.v("getView", "text : " + item.getText());

		viewHolder.screenName.setTypeface(Typeface.DEFAULT_BOLD);

		Status status = null;

		// リツイートだった場合
		if(item.getStatus().isRetweet())
		{
			status = item.getStatus().getRetweetedStatus();

			// リツイート箇所を表示する
			viewHolder.retweetText.setVisibility(View.VISIBLE);
			viewHolder.retweetImage.setVisibility(View.VISIBLE);

			viewHolder.retweetUrl = item.getStatus().getUser().getBiggerProfileImageURL();
			viewHolder.retweetText.setText(getShowName(item.getStatus()) + " がリツイート");
			// リクエストのキャンセル処理
			ImageContainer imageContainer = (ImageContainer) viewHolder.retweetImage.getTag();
			if (imageContainer != null) {
				imageContainer.cancelRequest();
			}

			// 画像はあとで変える
			ImageListener listener = ImageLoader.getImageListener(viewHolder.retweetImage,
					R.drawable.ic_launcher, R.drawable.ic_launcher);
			viewHolder.retweetImage.setTag(mImageLoader.get(viewHolder.retweetUrl, listener));

		}else{
			status = item.getStatus();

			// リツイート箇所を非表示にして詰める
			viewHolder.retweetText.setVisibility(View.GONE);
			viewHolder.retweetImage.setVisibility(View.GONE);

			viewHolder.retweetUrl = "";
			viewHolder.retweetText.setText("");
			viewHolder.retweetImage.setImageBitmap(null);

		}

		viewHolder.screenName.setText(getShowName(status));
		viewHolder.statusText.setText(status.getText());
		viewHolder.url = status.getUser().getBiggerProfileImageURL();


		// リクエストのキャンセル処理
		ImageContainer imageContainer = (ImageContainer) viewHolder.iconImage.getTag();
		if (imageContainer != null) {
			imageContainer.cancelRequest();
		}

		// 画像はあとで変える
		ImageListener listener = ImageLoader.getImageListener(viewHolder.iconImage,
				R.drawable.ic_launcher, R.drawable.ic_launcher);
		viewHolder.iconImage.setTag(mImageLoader.get(viewHolder.url, listener));

		return view;
	}

	/**
	 * 表示する名前を編集する
	 * @return
	 */
	private String getShowName(Status status)
	{
		StringBuilder name = new StringBuilder();

		name.append(status.getUser().getName());
		name.append(" ");
		name.append("@" + status.getUser().getScreenName());

		return name.toString();
	}

	class ViewHolder
	{
		String url;
		TextView screenName;
		TextView statusText;
		ImageView iconImage;
		String retweetUrl;
		ImageView retweetImage;
		TextView retweetText;
	}
}