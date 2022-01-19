package workcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @Author hike97
 * @Description
 * @create 2020-11-25 17:34
 * @Modified By:
 **/
public class obj {
	public static void main (String[] args) {
		Policy policy1 = new Policy ("110","01");
		Policy policy2 = new Policy ("111","02");
		Policy policy3 = new Policy ("112","03");
		Policy policy4 = new Policy ("113","04");

		Policy policy5 = new Policy ("111","02");
		Policy policy6 = new Policy ("112","03");

		List<Policy> list01 = new ArrayList<> ();

		list01.add (policy1);
		list01.add (policy2);
		list01.add (policy3);
		list01.add (policy4);
		list01.add (policy4);
		list01.add (policy4);

//		List<Policy> list02 = new ArrayList<> ();
//		list02.add (policy5);
//		list02.add (policy6);
		Set<Policy> set = new HashSet<> ();
		System.out.println ("变更前："+list01);
		set.addAll (list01);

//		System.out.println ("两个对象 相等吗？"+(policy3 == policy6));

//		list01.removeAll (list02);
		System.out.println ("变更后："+set);
	}
}
@Data
@NoArgsConstructor
@AllArgsConstructor
class Policy{
	private String policyNo;
	private String company;

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass () != o.getClass ()) return false;

		Policy policy = (Policy) o;

		if (policyNo != null ? !policyNo.equals (policy.policyNo) : policy.policyNo != null) return false;
		return company != null ? company.equals (policy.company) : policy.company == null;
	}

	@Override
	public int hashCode () {
		int result = policyNo != null ? policyNo.hashCode () : 0;
		result = 31 * result + (company != null ? company.hashCode () : 0);
		return result;
	}
}
