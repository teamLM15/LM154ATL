package task;

import timeline.TimeLineActivity;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import util.TwitterUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import auth.Auth;
import data.TwitterAcountData;
import data.TwitterAcountDataList;

public class AuthInitTask extends AsyncTask<String, String, Object>
{
	private Twitter twitter;
	private Auth auth;
	private Activity activity;

	public AuthInitTask(Auth auth, Activity activity)
	{
		this.auth = auth;
		this.activity = activity;
	}

	@Override
	protected Object doInBackground(String... params)
	{
		Log.v("AuthInitTask", "doInBackground start");
		try
		{
			twitter = auth.getTwitterWithPin(params[0]);
		}
		catch (TwitterException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return null;
	}

	protected void onPostExecute(Object obj)
	{
		Log.v("AuthInitTask", "onPostExecute start");
		// アカウントカウント
		int currentAcount = TwitterUtil.loadAccountCnt(activity);
		currentAcount++;
		TwitterUtil.storeAccountCnt(activity, currentAcount);
		Log.v("authInit", "" + currentAcount);

		// アクセストークン保存
		TwitterUtil.storeAccessToken(activity, auth.getAccsessToken(), currentAcount);
		// カレントアカウント保存
		TwitterUtil.storeCurrentAccountCnt(activity, currentAcount);

		Intent intent = new Intent(activity, TimeLineActivity.class);

		TwitterAcountData acountData = new TwitterAcountData();
		acountData.setTwitter(twitter);
		TwitterAcountDataList twitterAcountDataList = new TwitterAcountDataList();
		twitterAcountDataList.getTwitterAcountDataList().add(acountData);

		intent.putExtra("Twitter", twitterAcountDataList);

		activity.startActivity(intent);
		activity.finish();
	}
}
