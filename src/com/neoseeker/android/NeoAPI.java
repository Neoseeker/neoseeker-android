package com.neoseeker.android;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;


public class NeoAPI {
	
	
	private String consumerKey;
	private String consumerSecret;
	private String accessToken;
	private String accessTokenSecret;
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

		this.provider = new CommonsHttpOAuthProvider(NeoAPI.requestTokenURL, NeoAPI.accessTokenURL, NeoAPI.authTokenURL);
	}
	
	/**
	 * Sets the Access Token used for API call authentication
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	/**
	 * Sets the Access Token Secret used for API call authentication
	 * @param accessTokenSecret
	 */
	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
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
			authUrl = this.provider.retrieveRequestToken(this.consumer, OAuth.OUT_OF_BAND);
		} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthNotAuthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthCommunicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authUrl;
	}
	
	private boolean authenticate() throws Exception {

		if (true) { // If we need to authenticate
			String authUrl = this.provider.retrieveRequestToken(this.consumer, OAuth.OUT_OF_BAND);
			String pin = "";
			// Get authUrl to throw browser Intent
			// Whatever was on the page should be placed in the pin
			this.provider.retrieveAccessToken(consumer, pin);
		} else {
			// We've authenticated before, just set tokens
			
			
			this.consumer.setTokenWithSecret("5db3ef664a1e72c7c0aca64788fcdcd6e59a0830", "0593bf70a3bde3ebdf7b71553acdc68b99c3d347");
		}
		
		return true;
	}
}
