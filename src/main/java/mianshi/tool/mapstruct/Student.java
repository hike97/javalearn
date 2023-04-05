package mianshi.tool.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: hike97
 * @createTime: 2023/04/05 10:07
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String name;
    private Integer age;

    public static void main(String[] args) {
        StudentVO studentVO = new StudentVO("诸葛亮", 22);
        Student student = StudentMapper.INSTANCE.toStudent(studentVO);
        System.out.println(student);
    }
}
