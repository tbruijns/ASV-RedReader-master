package org.quantumbadger.redreader.test.general;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import org.junit.Before;
import org.junit.Test;
import org.quantumbadger.redreader.R;
import org.quantumbadger.redreader.account.RedditAccount;
import org.quantumbadger.redreader.account.RedditAccountManager;
import org.quantumbadger.redreader.common.PrefsUtility;
import org.quantumbadger.redreader.reddit.api.RedditOAuth;
import org.quantumbadger.redreader.reddit.prepared.RedditParsedComment;
import org.quantumbadger.redreader.reddit.prepared.RedditRenderableComment;
import org.quantumbadger.redreader.reddit.things.RedditComment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ASV {
	 SharedPreferences prefs = mock(SharedPreferences.class);
	 Context context = mock(Context.class);

	@Before
	public void before() throws Exception {
		this.prefs = mock(SharedPreferences.class);
		this.context = mock(Context.class);
		when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(prefs);
	}

	@Test
	public void unitTestSnowcard1(){
		String canonName = RedditAccountManager.getAnon().getCanonicalUsername();
		assertEquals("", canonName);
	}

	@Test
	public void unitTestSnowcard2(){
		RedditComment testComment1 = new RedditComment();
		testComment1.distinguished = "moderator";
		RedditParsedComment parsedTestComment = mock(RedditParsedComment.class);
		when(parsedTestComment.getRawComment()).thenReturn(testComment1);
		RedditRenderableComment renderableTestComment = new RedditRenderableComment(parsedTestComment,"testModAuthor",1, true);
		assertEquals(renderableTestComment.getParsedComment().getRawComment().distinguished, "moderator");

	}

	@Test
	public void unitTestSnowcard3(){
		RedditOAuth.RefreshToken testToken = new RedditOAuth.RefreshToken("sampleToken");
		RedditAccount test1 = new RedditAccount("",testToken ,1);
		test1.isAnonymous();
		assertTrue(test1.isAnonymous());
	}

	@Test
	public void unitTestSnowcard4(){
		when(PrefsUtility.getString(R.string.pref_appearance_theme_key, "red", context, prefs)).thenReturn("red");
		assertEquals("RED", PrefsUtility.appearance_theme(context, prefs).toString());

	}

	@Test
	public void unitTestSnowcard5(){
		when(PrefsUtility.getString(R.string.pref_appearance_twopane_key, "auto", context, prefs)).thenReturn("auto");
		assertEquals("AUTO", PrefsUtility.appearance_twopane(context, prefs).toString());
	}

	@Test
	public void unitTestSnowcard6(){
		when(prefs.getString(anyString(), anyString())).thenReturn("NEVER");
		assertEquals("NEVER", PrefsUtility.cache_precache_images(context, prefs).toString());
	}

	@Test
	public void unitTestSnowcard7(){
		when(PrefsUtility.getString(R.string.pref_behaviour_fling_post_left_key, "downvote", context, prefs)).thenReturn("downvote");
		assertEquals("DOWNVOTE", PrefsUtility.pref_behaviour_fling_post_left(context, prefs).toString());
	}

	@Test
	public void unitTestSnowcard8(){
		when(prefs.getString(anyString(), anyString())).thenReturn("NEVER");
		assertEquals("NEVER", PrefsUtility.cache_precache_comments(context, prefs).toString());

	}

@Test
	public void unitTestSnowcard9(){
	assertEquals(26,Build.VERSION_CODES.O);
}
}
