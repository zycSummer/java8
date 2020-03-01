package com.zyc.java8;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

/*
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 * 1. 对象的引用 :: 实例方法名
 * 2. 类名 :: 静态方法名
 * 3. 类名 :: 实例方法名
 *
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 *
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 * 1. 类名 :: new
 * 三、数组引用
 * 	类型[] :: new;
 */
public class TestMethodRef {
    // 对象的引用 :: 实例方法名
    @Test
    public void test1() {
        Consumer<String> con = (x) -> System.out.println(x);
        PrintStream ps = System.out;
        Consumer<String> con1 = ps::println;

        Consumer<String> con2 = System.out::println;
        con2.accept("zyc");
    }

    @Test
    public void test2() {
        Employee employee = new Employee();
        Supplier<String> sup = () -> employee.getName();
        String str = sup.get();
        System.out.println(str);

        Supplier<Integer> sup2 = employee::getAge;
        System.out.println(sup2.get());
    }

    //类名 :: 静态方法名
    @Test
    public void test3() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> com1 = Integer::compare;
    }

    //类名 :: 实例方法名
    @Test
    public void test4() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);

        BiPredicate<String, String> bp1 = String::equals;
    }

    //构造器引用
    @Test
    public void test5() {
        Supplier<Employee> sup = () -> new Employee();
        Supplier<Employee> sup1 = Employee::new;
        System.out.println(sup1.get());
    }

    @Test
    public void test6() {
        Function<String, Employee> fun = (x) -> new Employee(x);
        Function<String, Employee> fun1 = Employee::new;
        System.out.println(fun1.apply("朱一成"));

        BiFunction<Integer, Integer, Employee> bi = (x, y) -> new Employee(x, y);
        BiFunction<Integer, Integer, Employee> bi1 = Employee::new;
        System.out.println(bi1.apply(1, 2));
    }

    //数组引用
    @Test
    public void test7() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        System.out.println(fun.apply(10).length);

        Function<Integer, String[]> fun1 = String[]::new;
        System.out.println(fun1.apply(20).length);
    }

}















