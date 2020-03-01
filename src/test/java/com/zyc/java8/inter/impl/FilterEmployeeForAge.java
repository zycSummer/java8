package com.zyc.java8.inter.impl;

import com.zyc.java8.entity.Employee;
import com.zyc.java8.inter.MyPredicate;

public class FilterEmployeeForAge implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee t) {
        return t.getAge() > 35;
    }

}
