package com.atguigu.inter_face.Impl;

import com.atguigu.entity.Employee;
import com.atguigu.inter_face.MyPredicate;

/**
 * @author hike97
 * @create 2018-09-05 16:29
 * @desc predicate接口实现类
 **/
public class FilterEmployeeBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 5000;
    }
}
