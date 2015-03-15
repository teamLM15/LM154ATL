package task;

import java.io.IOException;
import java.net.URISyntaxException;

import twitter4j.TwitterException;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import auth.Auth;

public class AuthUrlTask extends AsyncTask<Object, Integer, Object>
{
	private Activity authActivity;
	private Auth auth;

	public AuthUrlTask(Activity authActivity, Auth auth)
	{
		this.authActivity = authActivity;
		this.auth = auth;
	}

	protected Object doInBackground(Object... aobj)
	{
		Log.v("AuthUrlTask", "doInBackground start");
		String authUrl = null;
		try
		{
			auth.authRequestToken();
			authUrl = auth.openAuthUrl();
		}
		catch (TwitterException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		catch (URISyntaxException e)
		{
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return authUrl;
	}

	protected void onPostExecute(Object obj)
	{
		onPostExecute((String) obj);
	}

	protected void onPostExecute(String s)
	{
		if (s != null)
		{
			Log.v("AuthUrlTask", "onPostExecute start");
			Intent intent = new Intent(new Intent(Intent.ACTION_VIEW , Uri.parse(s)));
			authActivity.startActivity(intent);
		}
	}
}
