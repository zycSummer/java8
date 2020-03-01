package com.zyc.java8.inter.impl;

import com.zyc.java8.Employee;
import com.zyc.java8.inter.MyPredicate;

public class FilterEmployeeForSalary implements MyPredicate<Employee> {

    @Override
    public boolean test(Employee t) {
        return t.getSalary() >= 5000;
    }

}
