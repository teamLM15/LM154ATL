package com.neoenox.lm15tl;

import task.AuthInitTask;
import task.AuthUrlTask;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import auth.Auth;

import com.neoenox.twitterapp.R;

public class AuthActivity extends Activity {
	private Auth auth;
	private TextView text_auth;
	private Button btn_auth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
		auth = new Auth();
		new AuthUrlTask(this, auth).execute();

		text_auth = (TextView) findViewById(R.id.auth_text);
		btn_auth = (Button) findViewById(R.id.auth_button);

		btn_auth.setOnClickListener(new OnClickListener() {

			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				String pin = text_auth.getText().toString();
				if(pin.length() == 7)
				{
					new AuthInitTask(auth, AuthActivity.this).execute(pin);
				}
				else
				{
					Toast.makeText(AuthActivity.this, "7桁で入力してください", Toast.LENGTH_LONG).show();;
				}
			}
		});

	}

}
