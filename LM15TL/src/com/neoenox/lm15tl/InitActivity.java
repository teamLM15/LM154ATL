package com.neoenox.lm15tl;

import task.AuthAlmostTask;
import util.TwitterUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.neoenox.twitterapp.R;

public class InitActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_init);

		Log.v("init", "loadAccountCnt : " + Integer.toString(TwitterUtil.loadAccountCnt(this)));
		Log.v("init", "hasAccessToken : " + Boolean.toString(TwitterUtil.hasAccessToken(this)));

		if (TwitterUtil.hasAccessToken(this))
		{
			new AuthAlmostTask(this).execute();
		}
		else
		{
			TwitterUtil.storeAccountCnt(this, -1);
			startActivity(new Intent(this, AuthActivity.class));
		}
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.init, menu);
		return true;
	}

}
