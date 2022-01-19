package workcode;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Properties;

/**
 * @Author hike97
 * @Description
 * @create 2020-10-29 14:31
 * @Modified By:
 **/
public class Test01 {
	public static void main (String[] args) {
//		DecimalFormat df = new DecimalFormat("0.00");
//		BigDecimal bd = new BigDecimal("1.19E7");
//		System.out.println(df.format(bd));
//		Properties properties = System.getProperties ();
//		System.out.println (properties);
//		System.out.println ("成吉思汗".length ());
//		System.out.println ("hike".length ());
//		String plus = "([0-9]\\d*\\.?\\d*)";
//		String minus = "((-)?[0-9]\\d*\\.?\\d*)";
//		System.out.println ("-110".matches (plus));
//		System.out.println ("-110".matches (minus));
//		long now = System.currentTimeMillis ();
//		System.out.println (now);
//		//毫秒级
//		Long milliSecond = LocalDateTime.now().minusDays (1L).toInstant(ZoneOffset.of("+8")).toEpochMilli();
//		System.out.println (milliSecond);
//		//秒级
//		Long second = LocalDateTime.now().minusDays (1L).toEpochSecond(ZoneOffset.of("+8"));
//		System.out.println (second);
		double sqrt = Math.sqrt (1641263317);
		double v = sqrt + 5;
		System.out.println (v);

	}
}
