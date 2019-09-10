package com;

import com.sun.xml.ws.util.StringUtils;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-09-05 19:14
 * @Modified By:
 **/
public class Turn {
	public static void main (String[] args) {
//		String s = LocalDate.parse ("0001-02-01").format (DateTimeFormatter.ofPattern ("yyyy年MM月dd日"));
//		System.out.println (s);
//		int hour = LocalDateTime.now ().getHour ();
//		String str = "2018-01-31T16:00:00Z";
//		str=str.replace ("Z","");
//		LocalDateTime parse = LocalDateTime.parse (str);
//		int hour = parse.getHour ();
//		System.out.println (hour);
		List<Object> list = Arrays.asList (null, null, null);
		List<CompletableFuture<Object>> futureList = list.stream ().map (i -> CompletableFuture.completedFuture (i).thenApplyAsync (v -> v)).collect (Collectors.toList ());
		CompletableFuture.allOf (futureList.toArray (new CompletableFuture[futureList.size ()])).join ();
		futureList.forEach (System.out::println);
		futureList.stream ().filter (v-> v.join ()!=null).map (CompletableFuture::join).forEach (System.out::println);

	}
}

