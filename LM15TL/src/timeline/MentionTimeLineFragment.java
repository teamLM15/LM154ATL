package timeline;

import task.getMentionTimeLine;
import twitter4j.Paging;
import util.TwitterUtil;
import data.TwitterAcountDataList;

/**
 * メンションをゲットするクラス。
 * ホームタイムラインを親クラスとして設定している（状況に応じて独立させる）
 *
 * @author さん
 *
 */
public class MentionTimeLineFragment extends HomeTimeLineFragment {

	private getMentionTimeLine getMentionTimeLine;

	public MentionTimeLineFragment(TwitterAcountDataList twitterDataList) {
		super(twitterDataList);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	protected void reload() {
		Paging paging = new Paging(1, 100);
		getMentionTimeLine = new getMentionTimeLine(items, adapter, paging, mSwipeRefreshLayout);
		getMentionTimeLine.execute(twitterDataList.getTwitterAcountDataList()
				.get(TwitterUtil.loadAccountCnt(getActivity()))
				.getTwitter());
	}

	@Override
	protected void reload(long sinceid) {
		Paging paging = new Paging(1, 100,sinceid);
		getMentionTimeLine = new getMentionTimeLine(items, adapter, paging, mSwipeRefreshLayout);
		getMentionTimeLine.execute(twitterDataList.getTwitterAcountDataList()
				.get(TwitterUtil.loadAccountCnt(getActivity()))
				.getTwitter());
	}

}

