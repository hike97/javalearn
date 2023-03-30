package workcode.work;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName ModifyGroupedListDemo
 * @Description list 根据type分组 成map 修改其中的list 查看原map是否改变
 * @Author hike97
 * @Date 2022/1/26 14:56
 * @Version 1.0
 **/
public class ModifyGroupedListDemo {
    public static void main (String[] args) {
        List<TestObject> list = new ArrayList<> ();
        TestObject obj1 = new TestObject ("1","tom");
        TestObject obj2 = new TestObject ("2","cat");
        TestObject obj3 = new TestObject ("2","jerry");
        TestObject obj4 = new TestObject ("3","tiger");
        list.add (obj1);
        list.add (obj2);
        list.add (obj3);
        list.add (obj4);
        Map<String, List<TestObject>> testMap = list.stream ().collect (Collectors.groupingBy (TestObject::getType));
        System.out.println ("before:"+testMap);
        obj1.setField ("cat pro");
        System.out.println ("after:"+testMap);

    }

}
@Data
@NoArgsConstructor
@AllArgsConstructor
class TestObject{
    private String type;
    private String field;
}
