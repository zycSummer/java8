package com.zyc.java8;

import com.zyc.java8.inter.MyFunction;
import com.zyc.java8.inter.MyFunction2;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created with IntelliJ IDEA.
 * User: zyc
 * Date: 2020/2/29
 * Time: 20:09
 * Description:Java8内置的4大核心函数式接口
 * Consumer<T> : 消费型接口
 * void accept(T t);
 * <p>
 * Supplier<T> : 供给型接口
 * T get();
 * <p>
 * Function<T, R> : 函数型接口
 * R apply(T t);
 * <p>
 * Predicate<T> : 断言型接口
 * boolean test(T t);
 */
public class TestLambda4 {
    //Consumer<T> 消费型接口 :有来无回(有参数，无返回值类型的接口。)
    @Test
    public void test1() {
        happy(1000d, money -> System.out.println(money));
    }

    public void happy(double money, Consumer<Double> con) {
        con.accept(money);
    }

    //Supplier<T> 供给型接口 :产生一些对象(只有产出，没人输入，就是只有返回值，没有入参)
    @Test
    public void test2() {
//        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        List<Integer> numList2 = getNumList2(10, () -> 10);
//        System.out.println(numList);
        System.out.println(numList2);
    }

    private List<Integer> getNumList2(int i, Supplier<Integer> sup) {
        Integer integer = sup.get();
        List<Integer> list = new ArrayList<>();
        for (int j = i; j > 0; j--) {
            list.add(integer - j + 1);
        }
        return list;
    }

    //需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }

    //Function<T, R> 函数型接口：(输入一个类型得参数，输出一个类型得参数，当然两种类型可以一致。)
    @Test
    public void test3() {
        String newStr = strHandler("\t\t\t qweqweqwe   ", (str) -> str.trim());
        System.out.println(newStr);

        String subStr = strHandler("zzewerew", (str) -> str.substring(2, 5));
        System.out.println(subStr);
    }

    //需求：用于处理字符串
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    //Predicate<T> 断言型接口：过滤(输入一个参数，输出一个boolean类型得返回值。)
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "atguigu", "Lambda", "www", "ok");
        List<String> strings = filterStr(list, (str) -> str.length() > 3);
        strings.forEach(System.out::println);
    }

    //需求：将满足条件的字符串，放入集合中
    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<>();
        for (String str : list) {
            if (pre.test(str)) {
                strList.add(str);
            }
        }
        return strList;
    }
}




