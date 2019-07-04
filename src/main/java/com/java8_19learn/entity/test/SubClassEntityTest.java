package com.java8_19learn.entity.test;

import com.java8_19learn.entity.Apple;
import com.java8_19learn.entity.SortedDish;
import com.java8_19learn.entity.SubClassEntity;
import lombok.val;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-07-04 14:24
 * @Modified By:
 **/
public class SubClassEntityTest {
		List<Apple> apples01 = Arrays.asList (
				new Apple ("red", 112),
				new Apple ("blue", 112)
		);
		List<Apple> apples02 = Arrays.asList (
				new Apple ("red", 112),
				new Apple ("blue", 112)
		);
		List<Apple> apples03 = Arrays.asList (
				new Apple ("blue", 112),
				new Apple ("black", 112)
		);
		List<Apple> apples04 = Arrays.asList (
				new Apple ("yellow", 112),
				new Apple ("blue", 112)
		);
		List<SubClassEntity> entities = Arrays.asList (
				new SubClassEntity ("lili", apples01),
				new SubClassEntity ("luck", apples02),
				new SubClassEntity ("slum", apples03),
				new SubClassEntity ("echo", apples04)
		);

	@Test
	public void test_ () {
		Map<String, SortedDish> collect = entities.stream ().collect (
				Collectors.groupingBy (d -> getAppleStatus (d.getApples ()), Collectors.collectingAndThen (
						Collectors.toList (), val -> new SortedDish (val.size (), val.get (0))
				))
		);
		System.out.println (collect);
	}
	private String getAppleStatus(List<Apple> apples){
		return apples.get (0).getColor ();
	}
}
