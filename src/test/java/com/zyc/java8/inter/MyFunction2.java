package com.zyc.java8.inter;

/**
 * Created with IntelliJ IDEA.
 * User: zyc
 * Date: 2020/2/29
 * Time: 20:18
 * Description:
 */
@FunctionalInterface
public interface MyFunction2<T, R> {
    public R getValue(T t1, T t2);
}
