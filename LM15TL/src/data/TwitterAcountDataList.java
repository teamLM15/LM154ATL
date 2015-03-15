package data;

import java.io.Serializable;
import java.util.ArrayList;

public class TwitterAcountDataList implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = -5594523845027982791L;
	private ArrayList<TwitterAcountData> twitterAcountDataList = new ArrayList<TwitterAcountData>();

	public ArrayList<TwitterAcountData> getTwitterAcountDataList()
	{
		return twitterAcountDataList;
	}

	public void setTwitterAcountDataList(ArrayList<TwitterAcountData> twitterAcountDataList)
	{
		this.twitterAcountDataList = twitterAcountDataList;
	}
}
