package com.example.parallelPlayground;

import ch.qos.logback.core.util.TimeUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ParallelPlaygroundApplication {

	public static void main(String[] args) throws Exception, InterruptedException {
		SpringApplication.run(ParallelPlaygroundApplication.class, args);

		long start = System.currentTimeMillis();

		ExecutorService es = Executors.newFixedThreadPool(2);

		try{
			es.execute(() -> {
				try {
					System.out.println(generateString());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
//			es.execute(() -> System.out.println("executor:2, thread-id:" + Thread.currentThread().getId()));
//			es.execute(() -> System.out.println("executor:3, thread-id:" + Thread.currentThread().getId()));
//			es.execute(() -> System.out.println("executor:4, thread-id:" + Thread.currentThread().getId()));
//			es.execute(() -> System.out.println("executor:5, thread-id:" + Thread.currentThread().getId()));

		} finally {
			es.shutdown();
			es.awaitTermination(1, TimeUnit.MINUTES);
		}

//		for (int i = 0; i < 50; i++) {
//			System.out.println(generateString());
//		}

		long end = System.currentTimeMillis();

		System.out.printf((end-start) + "ms");
	}


	public static String generateString() throws InterruptedException {
		Thread.sleep(1000);
		return "test";
	}

}
