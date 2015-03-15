package task;

import java.util.ArrayList;

import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import adapter.TwitterTimelineAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import data.TwitterStatusData;

public class getMentionTimeLine extends AbstractGetTimelineTask {

	private Paging paging;
	private SwipeRefreshLayout mSwipeRefreshLayout;

	public getMentionTimeLine(ArrayList<TwitterStatusData> items,
			TwitterTimelineAdapter adapter, Paging paging) {
		super(items, adapter);
		this.paging = paging;
	}

	public getMentionTimeLine(ArrayList<TwitterStatusData> items,
			TwitterTimelineAdapter adapter, Paging paging2,
			SwipeRefreshLayout mSwipeRefreshLayout) {
		this(items, adapter, paging2);
		this.mSwipeRefreshLayout = mSwipeRefreshLayout;
	}

	@Override
	protected Object doInBackground(Twitter... twitter) {

		try {
			responseList = twitter[0].getMentionsTimeline(paging);
			// responseList = twitter[0].getMentionsTimeline(paging);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		if (mSwipeRefreshLayout != null) {
			mSwipeRefreshLayout.setRefreshing(false);
		}

		return null;
	}

}
