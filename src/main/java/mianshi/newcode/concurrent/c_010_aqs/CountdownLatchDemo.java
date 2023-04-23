package mianshi.newcode.concurrent.c_010_aqs;

import lombok.Getter;
import mianshi.newcode.concurrent.c_009_threadpool.NamingThreadPoolFactory;

import java.util.concurrent.*;

/**
 * @Author hike97
 * @Description 秦灭六国一统中原
 * @create 2020-09-24 10:00
 * @Modified By:
 **/
public class CountdownLatchDemo {


	public static void main (String[] args) {
		CountDownLatch latch = new CountDownLatch(6);
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor (10, 10, 0L, TimeUnit.SECONDS,
				new SynchronousQueue<>(),new NamingThreadPoolFactory(Executors.defaultThreadFactory(),"qin"),new ThreadPoolExecutor.AbortPolicy ());
		for (int i = 0; i < 6; i++) {
			int finalI = i;
			threadPool.execute (()->{
				System.out.println(SixCountryEnum.values()[finalI].getDesc());
				latch.countDown ();
			});
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
		}
		try {
			latch.await ();
		} catch (InterruptedException e) {
			e.printStackTrace ();
		}
		System.out.println ("公元前221年，秦统一六国");
		threadPool.shutdown ();
	}
}
@Getter
enum SixCountryEnum {
	/**
	 * 战国七雄
	 */
	HAN("韩",230),
	ZHAO("赵",228),
	WEI("魏",225),
	CHU("楚",223),
	YAN("燕",222),
	QI("齐",221);

	private final String name;
	private final int year;
	private final String desc;

	SixCountryEnum(String name, int year) {
		this.name = name;
		this.year = year;
		this.desc = String.format("%s: 公元前%s年，%s被秦灭",Thread.currentThread().getName(),this.year,this.name);
	}
}
