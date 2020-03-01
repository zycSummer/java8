package com.zyc.java8;

import com.zyc.java8.inter.MyFunction;
import com.zyc.java8.inter.MyFunction2;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zyc
 * Date: 2020/2/29
 * Time: 20:09
 * Description:
 */
public class TestLambda3 {
    List<Employee> emps = Arrays.asList(
            new Employee(101, "张三", 18, 9999.99),
            new Employee(102, "李四", 59, 6666.66),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    @Test
    public void test1() {
        Collections.sort(emps, (x, y) -> {
            if (x.getAge() == y.getAge()) {
                return x.getName().compareTo(y.getName());
            } else {
                return Integer.compare(x.getAge(), y.getAge());
            }
        });
        emps.forEach(System.out::println);
    }

    // 需求用于处理字符串
    public String strHandler(String value, MyFunction mf) {
        return mf.getValue(value);
    }

    @Test
    public void test2() {
        String s = strHandler("\t\t\t 啥登记卡后打开  ", value -> value.trim());
        System.out.println(s);
        String s1 = strHandler("awdwd", value -> value.toUpperCase());
        System.out.println(s1);
    }

    // 需求用于2个Long型数据进行处理
    public void op(Long l1, Long l2, MyFunction2<Long, Long> mf) {
        System.out.println(mf.getValue(l1, l2));
    }

    @Test
    public void test3() {
        op(100L, 120L, (x, y) -> x + y);
        op(100L, 120L, (x, y) -> x * y);
    }
}
