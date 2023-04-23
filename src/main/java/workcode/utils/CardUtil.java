package workcode.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 身份证信息算法类
 *
 * @author javaweb
 */
public class CardUtil {

	public static Map<String, String> parseCertificateNo (String certificateNo) {
		Map map = new HashMap<> ();
		int idxSexStart = 16;
		int birthYearSpan = 4;
		//如果是15位的证件号码
		if (certificateNo.length () == 15) {
			idxSexStart = 14;
			birthYearSpan = 2;
		}
		//性别
		String idxSexStr = certificateNo.substring (idxSexStart, idxSexStart + 1);
		int idxSex = Integer.parseInt (idxSexStr) % 2;
		String sex = (idxSex == 1) ? "M" : "F";
		map.put ("sex", sex);

		//出生日期
		String year = (birthYearSpan == 2 ? "19" : "") + certificateNo.substring (6, 6 + birthYearSpan);
		String month = certificateNo.substring (6 + birthYearSpan, 6 + birthYearSpan + 2);
		String day = certificateNo.substring (8 + birthYearSpan, 8 + birthYearSpan + 2);
		String birthday = year + '-' + month + '-' + day;
		map.put ("birthday", birthday);

		//年龄
		Calendar certificateCal = Calendar.getInstance ();
		Calendar currentTimeCal = Calendar.getInstance ();
		certificateCal.set (Integer.parseInt (year), Integer.parseInt (month) - 1, Integer.parseInt (day));
		int yearAge = (currentTimeCal.get (Calendar.YEAR)) - (certificateCal.get (Calendar.YEAR));
		certificateCal.set (currentTimeCal.get (Calendar.YEAR), Integer.parseInt (month) - 1, Integer.parseInt (day));
		int monthFloor = (currentTimeCal.before (certificateCal) ? 1 : 0);
		map.put ("age", yearAge - monthFloor + "");

		return map;
	}

	public static void main (String[] args) {
		System.out.println (parseCertificateNo ("22020419930707541X"));
	}

}