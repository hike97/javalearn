package com.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author hike97
 * @Description threadlocal 场景
 * @create 2020-09-03 12:58
 * @Modified By:
 **/
public class ThreadLocalTest {
	public static void main (String[] args) {
		ThreadFactory threadFactory = new ThreadFactoryBuilder ().setNameFormat ("thread-%d").build ();

		ThreadPoolExecutor threadPoolExecutor =
				new ThreadPoolExecutor (20, 20,
						0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<> (1024), threadFactory);

		for (int i = 0; i < 20; i++) {
			threadPoolExecutor.execute (
//					()-> {System.out.println (DateUtilNotSafe.parse ("2020-01-01 10:00:00"));}
					()-> {System.out.println (DateUtilSafe.parse ("2020-01-01 10:00:00"));}
			);
		}

		threadPoolExecutor.shutdown ();

	}
}
