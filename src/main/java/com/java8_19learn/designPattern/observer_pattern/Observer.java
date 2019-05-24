package com.java8_19learn.designPattern.observer_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description 观察者模式
 * @create 2019-05-24 16:26
 * @Modified By:
 **/
public interface Observer {
	//观察者模式是一种比较常见的方案，某些事件发生时（比如状态转变），如果一个对象（通
	//常我们称之为主题）需要自动地通知其他多个对象（称为观察者），就会采用该方案。
	void notify(String tweet);

	public static void main (String[] args) {
		Feed f = new Feed();
		f.registerObserver(new NYTimes());
		f.registerObserver(new Guardian());
		f.registerObserver(new LeMonde());
		f.notifyObservers("The queen said her favourite book is Java 8 in Action!");
	}
}
class NYTimes implements Observer{
	public void notify(String tweet) {
		if(tweet != null && tweet.contains("money")){
			System.out.println("Breaking news in NY! " + tweet);
		}
	}
}
class Guardian implements Observer{
	public void notify(String tweet) {
		if(tweet != null && tweet.contains("queen")){
			System.out.println("Yet another news in London... " + tweet);
		}
	}
}
class LeMonde implements Observer{
	public void notify(String tweet) {
		if(tweet != null && tweet.contains("wine")){
			System.out.println("Today cheese, wine and news! " + tweet);
		}
	}
}
//主题类
interface Subject{
	void registerObserver(Observer o);
	void notifyObservers(String tweet);
}
//Feed类用于完成整个流程 加入三个观察者 并根据消息进行消息处理
class Feed implements Subject{
	private final List<Observer> observers = new ArrayList<> ();
	public void registerObserver(Observer o) {
		this.observers.add(o);
	}
	public void notifyObservers(String tweet) {
		observers.forEach(o -> o.notify(tweet));
	}
}
