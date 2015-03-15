package timeline;

import java.util.ArrayList;

import adapter.TwitterTimelineAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.neoenox.twitterapp.R;

import data.TwitterAcountDataList;
import data.TwitterStatusData;

public abstract class AbstractTimeLineFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	protected ArrayList<TwitterStatusData> items = new ArrayList<TwitterStatusData>();
	private ListView listView;
	protected TwitterTimelineAdapter adapter;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	protected TwitterAcountDataList twitterDataList;

	public AbstractTimeLineFragment(TwitterAcountDataList twitterDataList) {
		this.twitterDataList = twitterDataList;
	}

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		setRetainInstance(true);
		adapter = new TwitterTimelineAdapter(getActivity(),
				R.layout.timeline_inline, items);
		reload();
	}

	protected abstract void reload();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_time_line_dummy,
				container, false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		listView = (ListView) getView().findViewById(R.id.listView1);
		listView.setAdapter(adapter);

		mSwipeRefreshLayout = (SwipeRefreshLayout)getView().findViewById(R.id.refresh);
		mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.green, R.color.blue, R.color.yellow);
		mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				// 3秒待機
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						mSwipeRefreshLayout.setRefreshing(false);
					}
				}, 3000);
			}
		});

	}
}