package task;

import timeline.TimeLineActivity;
import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import util.TwitterUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import auth.Auth;
import data.TwitterAcountData;
import data.TwitterAcountDataList;

public class AuthAlmostTask extends AsyncTask<Object, Object, Object>
{

	private Activity activity;
	TwitterAcountDataList twitterAcountDataList;

	public AuthAlmostTask(Activity activity)
	{
		this.activity = activity;
	}

	@Override
	protected Object doInBackground(Object... params)
	{
		twitterAcountDataList = new TwitterAcountDataList();
		int acountCnt = TwitterUtil.loadAccountCnt(activity);
		Log.v("AlmostAuth", "acountCnt : " + acountCnt);
		for (int count = 0; count < acountCnt + 1; count++)
		{
			Auth auth = new Auth();
			AccessToken accessToken = TwitterUtil.loadAccessToken(activity, count);
			Twitter twitter = auth.getTwitterWithAccessToken(accessToken);

			TwitterAcountData twitterAcountData = new TwitterAcountData();
			twitterAcountData.setTwitter(twitter);
			twitterAcountDataList.getTwitterAcountDataList().add(twitterAcountData);
		}
		return null;
	}

	protected void onPostExecute(Object obj)
	{
		Intent intent = new Intent(activity, TimeLineActivity.class);
		intent.putExtra("Twitter", twitterAcountDataList);

		activity.startActivity(intent);
		activity.finish();
	}
}
