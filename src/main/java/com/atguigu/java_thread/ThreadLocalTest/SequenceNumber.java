package com.atguigu.java_thread.ThreadLocalTest;

/**
 * @author hike97
 * @create 2018-10-23 20:18
 * @desc
 **/
public class SequenceNumber {

	//1.通过匿名内部类覆盖ThreadLocalDe initialValue()方法，指定初始值
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer> (){
		public Integer initialValue(){
			return 0;
		}
	};
	//2.获取下一个序列值
	public int getNextNum(){
		seqNum.set (seqNum.get ()+1);
		return seqNum.get ();
	}

	public static void main (String[] args) {
		SequenceNumber sn = new SequenceNumber ();
		new TestClient (sn).start ();
		new TestClient (sn).start ();
		new TestClient (sn).start ();
	}
	private static class TestClient extends Thread{
		private SequenceNumber sn;

		public TestClient(SequenceNumber sn){
			this.sn = sn;
		}

		@Override
		public void run () {
			for (int i = 0; i <3 ; i++) {
				System.out.println("thread_pool["+Thread.currentThread().getName()+
						"] sn["+sn.getNextNum()+"]");
			}
		}
	}
}
