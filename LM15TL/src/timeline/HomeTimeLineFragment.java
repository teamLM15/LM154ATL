package timeline;

import java.util.ArrayList;

import task.GetHomeTimeLine;
import twitter4j.Paging;
import util.TwitterUtil;
import adapter.TwitterTimelineAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.neoenox.twitterapp.R;

import data.TwitterAcountDataList;
import data.TwitterStatusData;

/**
 * A dummy fragment representing a section of the app, but that simply displays
 * dummy text.
 */
public class HomeTimeLineFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private GetHomeTimeLine getHomeTimeLine;

	public static final String ARG_SECTION_NUMBER = "section_number";

	private static final int CONTEXT_MENU_TEXT_VIEW_ITEM_ID_1 = 1;

	private static final int CONTEXT_MENU_TEXT_VIEW_ITEM_ID_2 = 2;
	protected ArrayList<TwitterStatusData> items = new ArrayList<TwitterStatusData>();
	private ListView listView;
	protected TwitterTimelineAdapter adapter;
	protected SwipeRefreshLayout mSwipeRefreshLayout;
	protected TwitterAcountDataList twitterDataList;

	public HomeTimeLineFragment(TwitterAcountDataList twitterDataList) {
		this.twitterDataList = twitterDataList;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setRetainInstance(true);
		adapter = new TwitterTimelineAdapter(getActivity(),
				R.layout.timeline_inline, items);

		// 表示時にタイムライン読み込み。場合によって削除かな。
		reload();
	}

	protected void reload() {
		Paging paging = new Paging(1, 100);
		getHomeTimeLine = new GetHomeTimeLine(items, adapter, paging,
				mSwipeRefreshLayout);
		getHomeTimeLine.execute(twitterDataList.getTwitterAcountDataList()
				.get(TwitterUtil.loadAccountCnt(getActivity())).getTwitter());
	}

	protected void reload(long sinceid) {
		Paging paging = new Paging(1, 100, sinceid);
		getHomeTimeLine = new GetHomeTimeLine(items, adapter, paging,
				mSwipeRefreshLayout);
		getHomeTimeLine.execute(twitterDataList.getTwitterAcountDataList()
				.get(TwitterUtil.loadAccountCnt(getActivity())).getTwitter());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_time_line_dummy, container,
				false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		listView = (ListView) getView().findViewById(R.id.listView1);
		listView.setAdapter(adapter);

		// コンテキストメニュー許可
		registerForContextMenu(listView);

		// アイテムクリック時
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				getActivity().openContextMenu(view);

			}
		});


		// 以下 pull to refreshの機能
		mSwipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(
				R.id.refresh);
		mSwipeRefreshLayout.setColorSchemeResources(R.color.red, R.color.green,
				R.color.blue, R.color.yellow);
		mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {

			@Override
			public void onRefresh() {
				if (!items.isEmpty()) {
					Log.v("onRefrech", "name:"
							+ items.get(0).getStatus().getUser()
									.getScreenName());
					Log.v("onRefrech", "text:"
							+ items.get(0).getStatus().getText());

					reload(items.get(0).getStatus().getId());
				} else {
					mSwipeRefreshLayout.setRefreshing(false);

				}
			}
		});

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, view, menuInfo);

		menu.setHeaderTitle("TextViewのコンテキストメニュー");
		menu.setHeaderIcon(getResources().getDrawable(R.drawable.ic_launcher));

		menu.add(0, CONTEXT_MENU_TEXT_VIEW_ITEM_ID_1, 0, "TextView メニュー 1");
		menu.add(0, CONTEXT_MENU_TEXT_VIEW_ITEM_ID_2, 0, "TextView メニュー 2");
	}

}
