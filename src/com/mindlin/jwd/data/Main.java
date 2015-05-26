package com.mindlin.jwd.data;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONObject;
/**
 * Entry test class
 * @author mailmindlin
 *
 */
public class Main {
	/**
	 * Entry point
	 * @param args command line arguments
	 * @throws InterruptedException not really called
	 * @throws ExecutionException if there's a problem with getting the data
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService exec = Executors.newCachedThreadPool();
		Entity e = new Entity("Q42");
		Future<JSONObject> f = exec.submit(e);
		JSONObject data=f.get();
		System.out.println(data.toString());
	}

}
