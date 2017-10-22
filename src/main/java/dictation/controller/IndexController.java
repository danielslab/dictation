package dictation.controller;

import java.net.URI;
import java.util.HashMap;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class IndexController {

	private static final String SPECIAL_ACCOUNT = "taka_2";

	@RequestMapping(value = "/twitterlogin", method = RequestMethod.POST)
	public ModelAndView twitterlogin(UriComponentsBuilder builder) {
		TwitterConnectionFactory connectionFactory = new TwitterConnectionFactory(System.getenv("CONSUMER_KEY"), System.getenv("CONSUMER_SECRET"));
		OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
		URI callbackURL = builder.path("/twittercallback").build().toUri();
		OAuthToken requestToken = oauthOperations.fetchRequestToken(callbackURL.toString(), null);
		String authorizeUrl = oauthOperations.buildAuthorizeUrl(requestToken.getValue(), OAuth1Parameters.NONE);
		return new ModelAndView("redirect:" + authorizeUrl, new HashMap<String, Object>());
	}

	@RequestMapping(value = "/twittercallback", method = RequestMethod.GET)
	public ModelAndView twittercallback(@RequestParam(value = "oauth_token", required = false) String oauth_token, @RequestParam(value = "oauth_verifier", required = false) String oauth_verifier) {
		if (oauth_token == null) {
			return new ModelAndView("redirect:/cancel", new HashMap<String, Object>());
		}

		TwitterConnectionFactory connectionFactory = new TwitterConnectionFactory(System.getenv("CONSUMER_KEY"), System.getenv("CONSUMER_SECRET"));
		OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuthToken requestToken = new OAuthToken(oauth_token, oauth_verifier);
		AuthorizedRequestToken authorizedRequestToken = new AuthorizedRequestToken(requestToken, oauth_verifier);
		OAuthToken accessToken = oauthOperations.exchangeForAccessToken(authorizedRequestToken, null);

		Twitter twitter = new TwitterTemplate(System.getenv("CONSUMER_KEY"), System.getenv("CONSUMER_SECRET"), accessToken.getValue(), accessToken.getSecret());
		String screenName = twitter.userOperations().getUserProfile().getScreenName();
		if (!SPECIAL_ACCOUNT.equals(screenName)) {
			return new ModelAndView("redirect:/cancel", new HashMap<String, Object>());
		}

		Authentication auth = new UsernamePasswordAuthenticationToken("user", "password");
		SecurityContextHolder.getContext().setAuthentication(auth);
		return new ModelAndView("redirect:/questions", new HashMap<String, Object>());
	}

	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}

	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public String cancel() {
		return "redirect:/error";
	}

}
