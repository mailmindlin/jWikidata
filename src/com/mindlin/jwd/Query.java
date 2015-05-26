package com.mindlin.jwd;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Callable;

import org.json.JSONObject;
/**
 * WikiData query
 * @author mailmindlin
 *
 */
public class Query implements Callable<JSONObject> {
	Boolean useHttps;
	Boolean resolve;
	Property[] props;
	List[] lists;
	/**
	 * Gets the build parameters
	 * @return map of build parameters
	 */
	public Map<String, String> buildParameters() {
		HashMap<String, String> result = new HashMap<String, String>();
		//standard props
		result.put("action", "query");
		result.put("format", "json");
		StringBuilder sb;
		//build properties
		sb=new StringBuilder();
		for (int i = 0; i < props.length; i++) {
			if (i != 0)
				sb.append("|");
			sb.append(props[i].toString().toLowerCase());
		}
		result.put("prop", sb.toString());
		
		//lists
		sb=new StringBuilder();
		for (int i = 0; i < lists.length; i++) {
			if (i != 0)
				sb.append("|");
			sb.append(lists[i].toString().toLowerCase());
		}
		result.put("list", sb.toString());
		
		return result;
	}
	/**
	 * Builds URL with parameters
	 * @return URL
	 * @throws MalformedURLException if there was a problem building the URL
	 */
	public URL buildURL() throws MalformedURLException {
		StringBuffer sb = new StringBuffer();
		sb.append("http");
		if (useHttps)
			sb.append("s");
		sb.append("://www.wikidata.org/w/api.php?");
		Set<Entry<String, String>> params = buildParameters().entrySet();
		for(Entry<String, String> param: params)
			sb.append(param.getKey()+"="+param.getValue());
		
		return new URL(sb.toString());
	}

	@Override
	public JSONObject call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * What kind of property to query for
	 * @author mailmindlin
	 *
	 */
	public static enum Property {
		Categories,
		CategoryInfo,
		Contributors,
		DeletedRevisions,
		DuplicateFiles,
		ExtLinks,
		Extracts,
		FileUsage,
		GlobalUsage,
		/**
		 * Info about the images
		 */
		ImageInfo,
		/**
		 * images
		 */
		Images,
		/**
		 * Page information
		 */
		Info,
		/**
		 * Interwiki links 
		 */
		IWLinks,
		LangLinks,
		Links,
		LinksHere,
		PageImages,
		PageProps,
		PageTerms,
		Redirects,
		Revisions,
		StashImageInfo,
		Templates,
		TranscludedIn,
		TranscodeStatus,
		VideoInfo;
	}

	public static enum List {
		AbuseFilters,
		AbuseLog,
		AllCategories,
		AllDeletedFevisions,
		AllFileUsages,
		AllImages,
		AllLinks,
		AllPages,
		AllRedirects,
		AllTransclusions,
		AllUsers,
		BackLinks,
		BetaFeatures,
		Blocks,
		CategoryMembers,
		CentralNoticeLogs,
		CheckUser,
		CheckUserLog,
		DeletedRevs,
		EmbeddedIn,
		ExtURLUsage,
		FileArchive,
		GadgetCategories,
		Gadgets,
		GlobalAllUsers,
		GlobalBlocks,
		GlobalGroups,
		ImageUsage,
		IwbackLinks,
		LangbackLinks,
		LogEvents,
		MessageCollection,
		MMSites,
		PagePropNames,
		PagesWithProp,
		PrefixSearch,
		ProtectedTitles,
		QueryPage,
		Random,
		RecentChanges,
		Search,
		Tags,
		UserContribs,
		Users,
		Watchlist,
		Watchlistraw,
		Wikisets
	}
}
