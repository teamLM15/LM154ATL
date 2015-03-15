package auth;

import java.io.IOException;
import java.net.URISyntaxException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * 認証を行う。
 */
public class Auth {
	private Twitter twitter;

	private RequestToken requestToken;

	private AccessToken accessToken;

	private final String ConsumerKey = "Gi22ZQX8ulWJA6uC38Gs28gtU";

	private final String ConsumerSecret = "1syifwO3TTZCfmg0pzsyBf8ZuNiR2FxoL7PF4g2un493hItumk";

	private TwitterStream twitterStream;

	/**
	 * コンストラクタ。 Twitterインスタンスとコンシューマキーを設定する。
	 * PINから認証するときはAuth()→authRequestToken()→ getTwitterWithPin()の順で呼び出す。
	 * トークンとシークレットトークンから認証するときはAuth()→getTwitterWithToken()の順で呼び出す。
	 * アクセストークンから認証するときはAuth()→getTwitterWithAccessToken()の順で呼び出す。
	 */
	public Auth() {
		Configuration conf = new ConfigurationBuilder().build();
		twitter = new TwitterFactory(conf).getInstance();
		twitter.setOAuthConsumer(ConsumerKey, ConsumerSecret);
		twitterStream = new TwitterStreamFactory(conf).getInstance();
		twitterStream.setOAuthConsumer(ConsumerKey, ConsumerSecret);
	}

	/**
	 * リクエストトークンを設定する。
	 *
	 * @throws TwitterException
	 */
	public void authRequestToken() throws TwitterException {
		if (requestToken == null) {
			requestToken = twitter.getOAuthRequestToken();
		}
	}

	/**
	 * 認証用URLをブラウザで開く。開けなかった場合は返り値から開くようにする。
	 *
	 * @return 認証用URL
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String openAuthUrl() throws IOException, URISyntaxException {
		String url = requestToken.getAuthorizationURL();

		return url;
	}

	/**
	 * PINコードを使用して認証する。
	 *
	 * @param pin
	 * @return Twitter
	 * @throws TwitterException
	 */
	public Twitter getTwitterWithPin(String pin) throws TwitterException {
		accessToken = twitter.getOAuthAccessToken(requestToken, pin);

		return twitter;
	}

	/**
	 * トークンを指定して認証する。
	 *
	 * @param token
	 * @param tokenSecret
	 * @return Twitter
	 * @throws IllegalStateException
	 * @throws TwitterException
	 */
	public Twitter getTwitterWithToken(String token, String tokenSecret)
			throws IllegalStateException, TwitterException {
		accessToken = new AccessToken(token, tokenSecret);
		twitter.setOAuthAccessToken(accessToken);

		return twitter;
	}

	public Twitter getTwitterWithVerifier(String verifier)
			throws TwitterException {
		accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
		return twitter;
	}

	/**
	 * アクセストークンを指定して認証する。
	 *
	 * @param accessToken
	 * @return Twitter
	 */
	public Twitter getTwitterWithAccessToken(AccessToken accessToken) {
		twitter.setOAuthAccessToken(accessToken);

		return twitter;
	}

	public TwitterStream getStreamWithAccessToken(AccessToken accessToken) {
		twitterStream.setOAuthAccessToken(accessToken);

		return twitterStream;
	}

	public AccessToken getAccsessToken() {
		return accessToken;
	}
}
