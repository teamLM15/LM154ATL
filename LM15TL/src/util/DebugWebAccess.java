package util;

import android.os.StrictMode;

public class DebugWebAccess
{
	public static void start()
	{
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
	}

	public static void end()
	{
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().build());
	}
}
