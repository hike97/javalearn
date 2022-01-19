package com.aboutstring;

import org.junit.Test;

/**
 * @Author hike97
 * @Description
 * @create 2020-11-02 17:34
 * @Modified By:
 **/
public class StringBuilderBufferDemo {
	/**
	 * String          不可变字符串
	 * StringBuffer    可变字符串 线程安全 效率低
	 * StringBuilder   可变字符串 线程不安全 效率高
	 * 1.StringBuffer 初始值 16 每次扩容到原来的 length*2+2
	 * 2.推荐使用StringBuffer(capacity)
	 */
	/**
	 * 字符串反转
	 *
	 * @param str
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public String reverse (String str, int startIndex, int endIndex) {
		if (str != null) {
			char[] arr = str.toCharArray ();
			for (; startIndex < endIndex; startIndex++, endIndex--) {
				char temp = arr[startIndex];
				arr[startIndex] = arr[endIndex];
				arr[endIndex] = temp;
			}
			return new String (arr);
		}
		return null;
	}

	/**
	 * reverse 用字符串遍历实现
	 *
	 * @param str
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public String reverse01 (String str, int startIndex, int endIndex) {
		if (str != null) {
			String result = str.substring (0, startIndex);
			for (int i = endIndex; i >= startIndex; i--) {
				result += str.charAt (i);
			}
			result += str.substring (endIndex + 1);
			return result;
		}
		return null;
	}

	/**
	 * 用stringbuffer builder 实现
	 *
	 * @param str
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public String reverse02 (String str, int startIndex, int endIndex) {
		if (str != null) {
			StringBuilder builder = new StringBuilder (str.length ());
			builder.append (str, 0, startIndex);
			for (int i = endIndex; i >= startIndex; i--) {
				builder.append (str.charAt (i));
			}
			builder.append (str.substring (endIndex + 1));
			return builder.toString ();
		}
		return null;
	}


	@Test
	public void testReserve () {
		String str = "abcdefg";
		String reverse = reverse02 (str, 0, 5);
		System.out.println (reverse);
	}

	/*
	 * 获取 ab 在 absdkjsakdlasjdkfhakabdsad中出现的次数
	 *
	 */
	public int getCount (String mainStr, String subStr) {
		int index = 0;
		int count = 0;
		int mainLen = mainStr.length ();
		int subLen = subStr.length ();
		if (mainLen >= subLen) {
			//			while ((index=mainStr.indexOf (subStr))!=-1){
			//				count ++;
			//				mainStr = mainStr.substring (index+subStr.length ());
			//			}
			//更有效率的方法
			while ((index = mainStr.indexOf (subStr, index)) != -1) {
				count++;
				index += subLen;
			}
			return count;
		} else {
			return 0;
		}
	}

	@Test
	public void testGetStrCount () {
		int count = getCount ("absdkjsakdlasjdkfhakabdsad", "ab");
		System.out.println (count);
	}

	/*
	 * 获取两个字符串最长字串
	 * "absdkjsakdlasjdkfhakabdsad"
	 * "absdkjsakdlasjdkfhakabdsaddsafaewag"
	 */

	public String getMaxSubString (String str1, String str2) {
		if (str1 != null && str2 != null) {
			int str1Len = str1.length ();
			int str2Len = str2.length ();
			String maxStr = str1Len >= str2Len ? str1 : str2;
			String minStr = str1Len < str2Len ? str1 : str2;
			//最小字符串控制循环次数
			for (int i = 0; i < minStr.length (); i++) {
				//
				for (int x = 0, y = minStr.length () - i; y <= minStr.length (); x++, y++) {
					String substring = minStr.substring (x, y);
					if (maxStr.contains (substring)) {
						return substring;
					}
				}
			}
		}
		return null;
	}

	public String[] getMaxSubStringByArray (String str1, String str2) {
		if (str1 != null && str2 != null) {
			int str1Len = str1.length ();
			int str2Len = str2.length ();
			String maxStr = str1Len >= str2Len ? str1 : str2;
			String minStr = str1Len < str2Len ? str1 : str2;
			StringBuffer buffer = new StringBuffer ();
			//最小字符串控制循环次数
			for (int i = 0; i < minStr.length (); i++) {
				//
				for (int x = 0, y = minStr.length () - i; y <= minStr.length (); x++, y++) {
					String substring = minStr.substring (x, y);
					if (maxStr.contains (substring)) {
						buffer.append (substring + ",");
					}
				}
				if (buffer.length () != 0) {
					break;
				}
			}
			String[] split = buffer.toString ().replaceAll (",$", "").split (",");
			return split;
		}
		return null;
	}

	@Test
	public void testMaxSubString () {
//		String string = getMaxSubString ("hdsajshdajk", "ghdaoigladhfkdfha");
//		System.out.println (string);
//		String[] stringByArray = getMaxSubStringByArray ("hdsajshdajk", "ghdaoigladhfkdfha");
//		System.out.println (Arrays.toString (stringByArray));
//		ArrayList<Object> list = new ArrayList<> ();
//		boolean b = list.stream ().anyMatch (v -> v == null);
//		System.out.println (b);
		StringBuilder stringBuilder = null;
		System.out.println (stringBuilder.append ("1"));
		StringBuilder builder = new StringBuilder ();
	}

}
