package org.nutz.socialauth.kaixin001;

import java.util.Map;

import org.brickred.socialauth.Profile;
import org.brickred.socialauth.exception.ServerDataException;
import org.brickred.socialauth.exception.SocialAuthException;
import org.brickred.socialauth.oauthstrategy.OAuth1;
import org.brickred.socialauth.util.Constants;
import org.brickred.socialauth.util.OAuthConfig;
import org.brickred.socialauth.util.Response;
import org.nutz.json.Json;
import org.nutz.socialauth.AbstractOAuthProvider;

/**
 * 实现开心001帐号登录,OAuth1
 * 
 * @author wendal
 */
@SuppressWarnings("serial")
public class Kaixin001OAuthProvider extends AbstractOAuthProvider {

	public Kaixin001OAuthProvider(OAuthConfig providerConfig) {
		super(providerConfig);
		ENDPOINTS.put(Constants.OAUTH_REQUEST_TOKEN_URL,"http://api.kaixin001.com/oauth/request_token");
		ENDPOINTS.put(Constants.OAUTH_AUTHORIZATION_URL,"http://api.kaixin001.com/oauth/authorize");
		ENDPOINTS.put(Constants.OAUTH_ACCESS_TOKEN_URL, "http://api.kaixin001.com/oauth/access_token");
		AllPerms = new String[] {};
		AuthPerms = new String[] {};
		authenticationStrategy = new OAuth1(config, ENDPOINTS);
		authenticationStrategy.setPermission(scope);
		authenticationStrategy.setScope(getScope());

		PROFILE_URL = "http://api.kaixin001.com/users/me.json";
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Profile authLogin() throws Exception {
		String presp;
		System.out.println(accessGrant.getAttributes());
		try {
			Response response = authenticationStrategy.executeFeed(PROFILE_URL);
			presp = response.getResponseBodyAsString(Constants.ENCODING);
		} catch (Exception e) {
			throw new SocialAuthException("Error while getting profile from "
					+ PROFILE_URL, e);
		}
		try {
			Map<String, Object> data = Json.fromJson(Map.class, presp);
			if (!data.containsKey("uid"))
				throw new SocialAuthException("Error: " + presp);
			if (userProfile == null)
				userProfile = new Profile();
			userProfile.setValidatedId(data.get("uid").toString());
			userProfile.setProviderId(getProviderId());
			//userProfile.setFullName(data.get("name").toString());
			return userProfile;

		} catch (Exception ex) {
			throw new ServerDataException(
					"Failed to parse the user profile json : " + presp, ex);
		}
	}

}
