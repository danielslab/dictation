package dictation.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
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

    @Autowired
    private AuthenticationManager authManager;

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
			return new ModelAndView("redirect:/error", new HashMap<String, Object>());
		}

		TwitterConnectionFactory connectionFactory = new TwitterConnectionFactory(System.getenv("CONSUMER_KEY"), System.getenv("CONSUMER_SECRET"));
		OAuth1Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuthToken requestToken = new OAuthToken(oauth_token, oauth_verifier);
		AuthorizedRequestToken authorizedRequestToken = new AuthorizedRequestToken(requestToken, oauth_verifier);
		OAuthToken accessToken = oauthOperations.exchangeForAccessToken(authorizedRequestToken, null);

		Twitter twitter = new TwitterTemplate(System.getenv("CONSUMER_KEY"), System.getenv("CONSUMER_SECRET"), accessToken.getValue(), accessToken.getSecret());
		String screenName = twitter.userOperations().getUserProfile().getScreenName();

        // 許可ロールの設定
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));

        // SpringSecurity認証 - ユーザID/パスワード認証を設定する - パスワードはpassword固定
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        screenName, "password", authorities);

        // SpringSecurity認証マネージャ経由にて認証を行う。
       	authentication = authManager.authenticate(authentication);

        // SpringSecurityコンテキストを生成し、SpringSecurityの認証情報を格納する
        SecurityContextImpl context = new SecurityContextImpl();
        context.setAuthentication(authentication);

        // SpringSecurityコンテキストをコンテキストホルダーへ格納し、認証連携を完了する。
        // この処理にて、SpringSecurityの設定(=HTTP用認証)を完了する。
        SecurityContextHolder.setContext(context);

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

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "error";
	}
}
