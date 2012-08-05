package com.neoseeker.android;

import java.io.InputStream;
import java.io.StringWriter;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import oauth.signpost.signature.HmacSha1MessageSigner;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;


public class NeoAPI {
	
	
	private String consumerKey;
	private String consumerSecret;
	private static String requestTokenURL		= "http://api.neoseeker.com/oauth/request.php";
	private static String accessTokenURL		= "http://api.neoseeker.com/oauth/access.php";
	private static String authTokenURL			= "http://api.neoseeker.com/oauth/authorize.php";
	
	private OAuthConsumer consumer;
	private OAuthProvider provider;
	
	/**
	 * Neoseeker OAuth based API constructor
	 * @param consumerKey The OAuth Consumer Key you received from http://apps.neoseeker.com/my_apps/
	 * @param consumerSecret The OAuth Consumer Key Secrete you received from http://apps.neoseeker.com/my_apps/
	 */
	public NeoAPI(String consumerKey, String consumerSecret) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		
		this.consumer = new CommonsHttpOAuthConsumer(this.consumerKey, this.consumerSecret);
		this.consumer.setMessageSigner(new HmacSha1MessageSigner());
		this.provider = new CommonsHttpOAuthProvider(NeoAPI.requestTokenURL, NeoAPI.accessTokenURL, NeoAPI.authTokenURL);
		this.provider.setOAuth10a(true);
	}
	
	/**
	 * Sets the Access Token and Access Token secret used for API call authentication
	 * @param accessToken
	 */
	public void setAccessTokenWithSecret(String accessToken, String accessTokenSecret) {
		this.consumer.setTokenWithSecret(accessToken, accessTokenSecret);
	}
	
	
	/**
	 * Returns the Access Token returned from Neoseeker
	 * @return
	 */
	public String getAccessToken() {
		return this.consumer.getToken();
	}
	
	/**
	 * Returns the Access Token Secret returned from Neoseeker
	 * @return
	 */
	public String getAccessTokenSecret() {
		return this.consumer.getTokenSecret();
	}
	
	/**
	 * Makes an API request to Neoseeker
	 * @param apiCall API Call (ex. members/me/)
	 * @return JSON results
	 * @throws Exception
	 */
	public String makeRequest(String apiCall) throws Exception {
		String result = "";
		String baseAPIURL = "http://api.neoseeker.com/";
		
		HttpGet request = new HttpGet(baseAPIURL + apiCall);

        consumer.sign(request);
        
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);
        
        InputStream is = response.getEntity().getContent();
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer);
	        result = writer.toString();

		return result;
	}
	
	/**
	 * Returns the URL to direct the user to so their account can approve the application to access user data
	 * @return
	 */
	public String getAuthenticationUrl() {
		String authUrl = "";
		try {
			authUrl = this.provider.retrieveRequestToken(this.consumer, "neoseeker-app://oauth");
		} catch (OAuthMessageSignerException e) {
			Log.e("NeoAPI", "OAuthMessageSignerException: " + e.getMessage());
		} catch (OAuthNotAuthorizedException e) {
			Log.e("NeoAPI", "OAuthNotAuthorizedException: " + e.getMessage());
		} catch (OAuthExpectationFailedException e) {
			Log.e("NeoAPI", "OAuthExpectationFailedException: " + e.getMessage());
		} catch (OAuthCommunicationException e) {
			Log.e("NeoAPI", "OAuthCommunicationException: " + e);
		}
		return authUrl;
	}
	
	/**
	 * 
	 * @param oauth_verifier
	 * @throws Exception
	 */
	public void authenticate(String oauth_verifier) throws Exception {
		this.provider.retrieveAccessToken(consumer, oauth_verifier);
		this.setAccessTokenWithSecret(this.consumer.getToken(), this.consumer.getTokenSecret());
	}
}
