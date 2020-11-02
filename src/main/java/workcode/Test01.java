package workcode;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Author hike97
 * @Description
 * @create 2020-10-29 14:31
 * @Modified By:
 **/
public class Test01 {
	public static void main (String[] args) {
		DecimalFormat df = new DecimalFormat("0.00");
		BigDecimal bd = new BigDecimal("1.19E7");
		System.out.println(df.format(bd));
	}
}
