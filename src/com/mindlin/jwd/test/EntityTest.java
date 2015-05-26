package com.mindlin.jwd.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.json.JSONObject;
import org.junit.Test;

import com.mindlin.jwd.data.Entity;

public class EntityTest {
	ExecutorService exec = Executors.newCachedThreadPool();
	@Test
	public void test() throws InterruptedException, ExecutionException {
		Entity e = new Entity("Q42");
		Future<JSONObject> f = exec.submit(e);
		JSONObject data=f.get();
		System.out.println(data.toString());
	}

}
