package timeline;

import task.GetOwnTimeLine;
import twitter4j.Paging;
import util.TwitterUtil;
import data.TwitterAcountDataList;

/**
 * 自分のツイート表示。ID指定して好きな人の表示させるほうがいいかもしれん。
 * ホームタイムラインを親クラスとして設定している（状況に応じて独立させる）
 *
 * @author neoenox
 *
 */
public class OwnTimeLineFragment extends HomeTimeLineFragment {

	private GetOwnTimeLine getUserTimeLine;

	public OwnTimeLineFragment(TwitterAcountDataList twitterDataList) {
		super(twitterDataList);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	protected void reload() {
		Paging paging = new Paging(1, 100);
		getUserTimeLine = new GetOwnTimeLine(items, adapter, paging, mSwipeRefreshLayout);
		getUserTimeLine.execute(twitterDataList.getTwitterAcountDataList()
				.get(TwitterUtil.loadAccountCnt(getActivity()))
				.getTwitter());
	}

	@Override
	protected void reload(long sinceid) {
		Paging paging = new Paging(1, 100,sinceid);
		getUserTimeLine = new GetOwnTimeLine(items, adapter, paging, mSwipeRefreshLayout);
		getUserTimeLine.execute(twitterDataList.getTwitterAcountDataList()
				.get(TwitterUtil.loadAccountCnt(getActivity()))
				.getTwitter());
	}

}

