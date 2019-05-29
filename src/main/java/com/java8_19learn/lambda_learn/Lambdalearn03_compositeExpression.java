package com.java8_19learn.lambda_learn;

import com.java8_19learn.entity.Apple;
import com.java8_19learn.entity.AppleTest;
import com.java8_19learn.entity.Letter;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.comparing;


/**
 * @Author hike97 許せ　サスケ　これで最後だ
 * @Description Lambda的复合表达式
 * @create 2019-04-26 11:17 金曜日
 * @Modified By:
 * Hope for the best, plan for the worst.
 * 抱最大的希望，做最坏的打算。
 **/
public class Lambdalearn03_compositeExpression {
	public static void main (String[] args) {

		/**1.比较器复合*/
			//1.1使用静态方法 Comparator.comparing ，根据提取用于比较的键值的 Function 来返回一个 Comparator ，如下所示：
		List<Apple> apples = AppleTest.apples;
		Comparator<Apple> c = comparing(Apple::getWeight);
		apples.sort (c);
		System.out.println ("正序排列："+apples);
			//1.2逆序 + 其它属性排序
		apples.sort(comparing(Apple::getWeight)
				.reversed()
				.thenComparing(Apple::getColor));
		/*
		  comparing 可以加自定义的collator
		  employees.sort (Comparator.comparing (Employee::getName, Collator.getInstance (Locale.CHINA)));
		 */
		System.out.println (apples);

		/**谓词复合*/
		/*谓词接口包括三个方法： negate 、 and 和 or ，让你可以重用已有的 Predicate 来创建更复杂的谓词*/


	}

	@Test
	public void test_functionCompose () {
		/**函数复合*/
		Function<Integer,Integer> f = x->x+1;
		Function<Integer,Integer> g = x->x*2;
		Function<Integer,Integer> h = f.andThen (g); //相当于g(f(x))
		Function<Integer,Integer> i = f.compose (g); //相当于f(g(x))
		//(1+1)*2=4
		Integer result = h.apply (1);
		System.out.println ("g(f(x)="+result);
		//1*2+1=3
		result =i.apply (1);
		System.out.println ("f(g(x)="+result);
	}

	@Test
	public void test_stringComp () {
		Function<String, String> addHeader = Letter::addHeader;
		Function<String, String> transformationPipeline
				= addHeader.andThen(Letter::checkSpelling)
				.andThen(Letter::addFooter);
		System.out.println (transformationPipeline.apply ("蔡徐坤"));
	}
}
