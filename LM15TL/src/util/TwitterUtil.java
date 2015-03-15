package util;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import twitter4j.auth.AccessToken;

/**
 * アカウントは0から
 * @author eno
 *
 */
public class TwitterUtil {

	static String ACCESSTOKEN = "accesstoken";
	static String ACCOUNTCNT = "account_cnt";
	static String CURRENTACCOUNTCNT = "current_account_cnt";

    public static AccessToken loadAccessToken(Context context, int accountCnt)
    {
    	Log.v("loadAccessToken", "loadAccessToken start");
    	Log.v("loadAccessToken", "accontCnt : " + accountCnt);
        SharedPreferences sharedpreferences = context.getSharedPreferences("twitter_access_token", 0);
        String accesstokenStr = sharedpreferences.getString(ACCESSTOKEN + "_"  + accountCnt, null);

        Gson gson = new Gson();
        AccessToken acesstoken = gson.fromJson(accesstokenStr, AccessToken.class);
        return acesstoken;
    }

    public static void storeAccessToken(Context context, AccessToken accesstoken, int accountCnt)
    {
    	Log.v("storeAccessToken", "storeAccessToken start");
    	Log.v("storeAccessToken", "accontCnt : " + accountCnt);
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences("twitter_access_token", 0).edit();
        Gson gson = new Gson();

        editor.putString(ACCESSTOKEN + "_"  + accountCnt, gson.toJson(accesstoken));
        editor.commit();
    }

    public static int loadAccountCnt(Context context)
    {
    	SharedPreferences sharedpreferences = context.getSharedPreferences(ACCOUNTCNT, 0);
        int accountCnt = sharedpreferences.getInt(ACCOUNTCNT, 0);

        return accountCnt;
    }

    public static void storeAccountCnt(Context context, int cnt)
    {
    	Log.v("storeAccountCnt", "storeAccountCnt start");
    	Log.v("storeAccountCnt", "cnt : " + cnt);
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences(ACCOUNTCNT, 0).edit();

        editor.putInt(ACCOUNTCNT, cnt);
        editor.commit();
    }

    public static int loadCurrentAccountCnt(Context context)
    {
    	SharedPreferences sharedpreferences = context.getSharedPreferences(CURRENTACCOUNTCNT, 0);
        int currentAccountCnt = sharedpreferences.getInt(CURRENTACCOUNTCNT, 0);

        return currentAccountCnt;
    }

    public static void storeCurrentAccountCnt(Context context, int cnt)
    {
    	Log.v("storeCurrentAccountCnt", "storeCurrentAccountCnt start");
    	Log.v("storeCurrentAccountCnt", "cnt : " + cnt);
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences(CURRENTACCOUNTCNT, 0).edit();

        editor.putInt(ACCOUNTCNT, cnt);
        editor.commit();
    }

    public static boolean hasAccessToken(Context context)
    {
        return loadAccessToken(context, loadAccountCnt(context)) != null;
    }
}
