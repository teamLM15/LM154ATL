package timeline;

import java.util.ArrayList;
import java.util.Locale;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

import com.neoenox.twitterapp.R;

import data.TwitterAcountDataList;

public class TimeLineActivity extends FragmentActivity {

	public static final String ITEMS = "items";

	private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter = null;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	private TwitterAcountDataList twitterDataList = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeline_main);

		// Twitterインスタンス読み込み
		Object extra = getIntent().getExtras().get("Twitter");
		if (extra instanceof TwitterAcountDataList) {
			Log.v("TimeLine", "TwitterAcountDataList Instance");
			twitterDataList = (TwitterAcountDataList) extra;
		}

		// 試し
		fragmentList.add(new HomeTimeLineFragment(twitterDataList));
		fragmentList.add(new MentionTimeLineFragment(twitterDataList));
		fragmentList.add(new OwnTimeLineFragment(twitterDataList));

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		if (mSectionsPagerAdapter == null) {
			mSectionsPagerAdapter = new SectionsPagerAdapter(
					getSupportFragmentManager());
		}
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_line, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			return (Fragment)fragmentList.get(position);
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}
}
