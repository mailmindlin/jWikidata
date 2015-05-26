package com.mindlin.jwd.data;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;
/**
 * Wikidata Entity retriver thing
 * @author mailmindlin
 *
 */
public class Entity implements Callable<JSONObject> {
	private static final int DEFAULT_BUFFER_SIZE = 4096;
	/**
	 * Whether to cache entities
	 */
	public static boolean USE_CACHE=false;
	/**
	 * Entity cache
	 */
	protected ConcurrentHashMap<String, JSONObject> cache;
	/**
	 * Name of entity
	 */
	final protected String name;
	/**
	 * Entity data
	 */
	protected JSONObject data;
	/**
	 * Create entity for name
	 * @param name name of entity
	 */
	public Entity(String name) {
		this.name = name;
	}
	/**
	 * Loads the data for the entity
	 * @throws IOException if the name is invalid or there's a problem with retrieving the data.
	 */
	public void load() throws IOException {
		String path="https://www.wikidata.org/wiki/Special:EntityData/" + name + ".json";
		System.out.println("URL: "+path);
		URL url = new URL(path);
		Reader in = new InputStreamReader(url.openStream());
		
		System.out.println("Reading...");
		StringWriter sw = new StringWriter();
		
		char[] buffer = new char[DEFAULT_BUFFER_SIZE];
		long count = 0;
		int n = 0;
		while (-1 != (n = in.read(buffer))) {
			sw.write(buffer, 0, n);
			count += n;
		}
		in.close();
		System.out.println("\tDone (Read "+count+" bytes).");
		
		data = new JSONObject(sw.toString());
		if(USE_CACHE) {
			if(cache==null)
				cache=new ConcurrentHashMap<String, JSONObject>();
			cache.put(name, data);
		}
	}

	@Override
	public JSONObject call() throws Exception {
		if(USE_CACHE && cache!=null && cache.containsKey(name))
			data=cache.get(name);
		else
			load();
		return data;
	}
}
