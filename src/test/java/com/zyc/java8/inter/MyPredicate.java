package com.zyc.java8.inter;

/**
 * Created with IntelliJ IDEA.
 * User: zyc
 * Date: 2020/2/29
 * Time: 17:40
 * Description:
 */
@FunctionalInterface
public interface MyPredicate<T> {
    public boolean test(T t);
}
