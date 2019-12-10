package com.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author hike97
 * @Description 简单的正则表达式测试
 * @create 2019-11-22 16:59
 * @Modified By:
 **/
public class SimpleRegexTest {
	public static void main (String[] args) {
		String myText = "this is my 1st test string";
		String myRegex = "\\d+\\w+"; //表示\d+\w+
		Pattern pattern = Pattern.compile (myRegex,Pattern.CASE_INSENSITIVE|Pattern.UNICODE_CASE);
		Matcher matcher = pattern.matcher (myText);
		if (matcher.find ()){
			String matchedText = matcher.group ();
			int start = matcher.start ();
			int end = matcher.end ();
			System.out.println ("matched [" + matchedText + "]" + "from" + start + "to" + end + ".");
		}else {
			System.out.println ("didn't match");
		}

	}

	@Test
	public void test_find () {
		String regex = "\\w+";//[\w+]
		String text = "Masterting Regular Expressions";
		Matcher m = Pattern.compile (regex).matcher (text);
		if (m.find ()) {
			System.out.println ("match [" + m.group () + "]");
		}
		while (m.find ()) {
			System.out.println ("match [" + m.group () + "]");
		}
	}
}
