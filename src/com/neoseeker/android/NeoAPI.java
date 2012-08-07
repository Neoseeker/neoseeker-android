package com.neoseeker.android;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import android.util.Log;


public class NeoAPI {
	private String TAG = "NeoAPI";
	
	private String consumerKey;
	private String consumerSecret;
	private String baseAPIURL = "http://api.neoseeker.com/";
	
	private OAuthService service;
	private Token requestToken;
	private Token accessToken;
	private Verifier verifier;
	
	/**
	 * Neoseeker OAuth based API constructor
	 * @param consumerKey The OAuth Consumer Key you received from http://apps.neoseeker.com/my_apps/
	 * @param consumerSecret The OAuth Consumer Key Secrete you received from http://apps.neoseeker.com/my_apps/
	 */
	public NeoAPI(String consumerKey, String consumerSecret) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		
		this.service = new ServiceBuilder().provider(NeoseekerAPI.class).apiKey(this.consumerKey).apiSecret(this.consumerSecret).build();
	}
	
	/**
	 * Grabs the request Token
	 * @return
	 */
	public void getRequestToken() {
		Log.v(TAG, "Getting request token...");
	    this.requestToken = service.getRequestToken();
	    Log.v(TAG, "Got request token!");
	}
	
	/**
	 * Returns the URL to direct the user to so their account can approve the application to access user data
	 * @return
	 */
	public String getAuthenticationUrl() {
		return service.getAuthorizationUrl(this.requestToken);
	}
	
	/**
	 * Sets the Access Token and Access Token secret used for API call authentication
	 * @param accessToken
	 */
	public void setAccessTokenWithSecret(String accessToken, String accessTokenSecret) {
		this.accessToken = new Token(accessToken, accessTokenSecret);
	}
	
	
	/**
	 * Returns the Access Token returned from Neoseeker
	 * @return
	 */
	public String getAccessToken() {
		return this.accessToken.getToken();
	}
	
	/**
	 * Returns the Access Token Secret returned from Neoseeker
	 * @return
	 */
	public String getAccessTokenSecret() {
		return this.accessToken.getSecret();
	}
	
	/**
	 * 
	 * @param oauth_verifier
	 * @throws Exception
	 */
	public void authenticate(String oauth_verifier) throws Exception {
		this.verifier = new Verifier(oauth_verifier);
		
		//this.provider.retrieveAccessToken(consumer, oauth_verifier);
		//this.setAccessTokenWithSecret(this.consumer.getToken(), this.consumer.getTokenSecret());
	}
	
	/**
	 * Makes an API request to Neoseeker
	 * @param apiCall API Call (ex. members/me/)
	 * @return JSON results
	 */
	public String makeRequest(String apiCall) {
		OAuthRequest request = new OAuthRequest(Verb.GET, this.baseAPIURL + apiCall);
		
		service.signRequest(this.accessToken, request);
		
	    Response response = request.send();
	    return response.getBody();
	}
}
