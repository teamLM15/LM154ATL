package data;

import java.io.Serializable;

import twitter4j.Twitter;
import twitter4j.TwitterStream;
import twitter4j.auth.AccessToken;

public class TwitterAcountData  implements Serializable
{
	/**
	 *
	 */
	private static final long serialVersionUID = 5281410317725210594L;
	private Twitter twitter;
	private TwitterStream twitterStream;
	private AccessToken accessToken;
	private String screenName;
	public Twitter getTwitter()
	{
		return twitter;
	}
	public void setTwitter(Twitter twitter)
	{
		this.twitter = twitter;
	}
	public TwitterStream getTwitterStream()
	{
		return twitterStream;
	}
	public void setTwitterStream(TwitterStream twitterStream)
	{
		this.twitterStream = twitterStream;
	}
	public AccessToken getAccessToken()
	{
		return accessToken;
	}
	public void setAccessToken(AccessToken accessToken)
	{
		this.accessToken = accessToken;
	}
	public String getScreenName()
	{
		return screenName;
	}
	public void setScreenName(String screenName)
	{
		this.screenName = screenName;
	}
}
