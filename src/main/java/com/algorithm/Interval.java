package com.algorithm;

import lombok.Data;

import java.util.*;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description
 * @create 2019-10-16 14:26
 * @Modified By:
 **/
@Data
public class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}

	public static List<Interval> merge(List<Interval> intervals) {
		List<Interval> list = new ArrayList<> ();
		int len = intervals.size();
		if (len == 0) {
			return list;
		}
		if (len == 1) {
			return intervals;
		}
		//按区间的左端点进行从小到大的排序
		Collections.sort(intervals, new Comparator<Interval> () {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});

		Interval temp = intervals.get(0);
		for (int i = 1; i < len; i++) {
			//此为条件1
			if (temp.end >= intervals.get(i).start) {
				//取右端点最大值
				temp.end = Math.max(temp.end, intervals.get(i).end);
			} else {
				//不满足条件1，则加入到结果区间中，将当前比较区间变更为遍历区间
				list.add(temp);
				temp = intervals.get(i);
			}
			//遍历到最后一个，判断完直接加入到结果区间中
			if (i == len - 1) {
				list.add(temp);
			}
		}
		return list;
	}

	public static void main (String[] args) {
		List<Interval> intervals = Arrays.asList (
				new Interval (-1, 0),
				new Interval (2000, 30000),
				new Interval (3000, 100000),
				new Interval (200000, 300000));

		List<Interval> merge = merge (intervals);
		merge.stream ().forEach (System.out::println);

	}
}
