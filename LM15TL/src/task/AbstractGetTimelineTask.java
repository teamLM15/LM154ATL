package task;

import java.util.ArrayList;

import twitter4j.ResponseList;
import twitter4j.Twitter;
import adapter.TwitterTimelineAdapter;
import android.os.AsyncTask;
import android.util.Log;
import data.TwitterStatusData;

public abstract class AbstractGetTimelineTask extends
		AsyncTask<Twitter, Object, Object> {

	private ArrayList<TwitterStatusData> items;
	private TwitterTimelineAdapter adapter;
	protected ResponseList<twitter4j.Status> responseList;


	public AbstractGetTimelineTask(ArrayList<TwitterStatusData> items,
			TwitterTimelineAdapter adapter) {
		this.items = items;
		this.adapter = adapter;
	}

	@Override
	protected abstract Object doInBackground(Twitter... twitter);

	protected void onPostExecute(Object obj) {
		Log.v("TimeLineTask","responseList:" + responseList.size());
		for(int cnt = 0; cnt < responseList.size(); cnt++)
		{
			// 追加部
			TwitterStatusData insertdata = new TwitterStatusData();
			insertdata.setStatus(responseList.get(cnt));
			items.add(cnt, insertdata);
			adapter.add(null);
		}
	}
}
