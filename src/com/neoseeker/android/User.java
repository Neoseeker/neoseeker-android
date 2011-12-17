package com.neoseeker.android;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private int memberid;
	private String username;
	private String created;
	private String avatar_img;
	private boolean is_online;
	private String url;
	private String api_url;
	
	public int getMemberid() {
		return memberid;
	}

	public String getUsername() {
		return username;
	}

	public String getCreated() {
		return created;
	}

	public String getAvatar_img() {
		return avatar_img;
	}

	public boolean getIs_online() {
		return is_online;
	}

	public String getUrl() {
		return url;
	}

	public String getApi_url() {
		return api_url;
	}
	
	public User(JSONObject jsonObject) {
		try {
			memberid = Integer.parseInt(jsonObject.getString("memberid"));
			username = jsonObject.getString("username");
			created = jsonObject.getString("created");
			avatar_img = jsonObject.getString("avatar_img");
			is_online = (jsonObject.getString("is_online").equals("Y")) ? true : false;
			url = jsonObject.getString("url");
			api_url= jsonObject.getString("api_url");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
		} catch (JSONException e) {
			// TODO Auto-generated catch block
		}
	}
}
