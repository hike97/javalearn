package workcode.exercise;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
	/**
	 * 二分查找
	 * @param n int整型 数组长度
	 * @param v int整型 查找值
	 * @param a int整型一维数组 有序数组
	 * @return int整型
	 */
	public int upper_bound_ (int n, int v, int[] a) {
		// write code here
		return binarySearch(a,0,n-1,v);
	}
	public static int binarySearch(int[] a,int left,int right,int findValue){
		int mid = (left+right)/2;
		int midValue = a[mid];
		if(left>right){
			return a.length+1;
		}
		if(midValue>=findValue){
			//找到对应地址，向左扫描
			List<Integer> resIndex = new ArrayList<Integer> ();
			int temp = mid-1;
			while(true){
				if(temp<0||a[temp]<findValue){
					break;
				}
				resIndex.add(temp);
				temp -=1;
			}
			resIndex.add(mid);
			//向右扫描
			temp = mid + 1;
			while(true){
				if(temp<0||a[temp]<findValue||temp<a.length){
					break;
				}
				resIndex.add(temp);
				temp +=1;
			}
			Collections.sort (resIndex);
			return resIndex.get(0)+1;
		}else{
			return binarySearch(a,mid+1,right,findValue);
		}
	}


	public static void main (String[] args) {
		Solution solution = new Solution ();
		int[]a ={1,1,2,3,7,7,7,9,9,10};
		int i = solution.upper_bound_ (10, 2, a);
		System.out.println (i);
	}
}