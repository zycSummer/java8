package com.zyc.java8.optional;

import com.zyc.java8.entity.Employee;
import com.zyc.java8.entity.Godness;
import com.zyc.java8.entity.Man;
import com.zyc.java8.entity.NewMan;
import org.junit.Test;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * User: zyc
 * Date: 2020/3/1
 * Time: 17:29
 * Description:
 * 一、Optional 容器类：用于尽量避免空指针异常
 * Optional.of(T t) : 创建一个 Optional 实例
 * Optional.empty() : 创建一个空的 Optional 实例
 * Optional.ofNullable(T t):若 t 不为 null,创建 Optional 实例,否则创建空实例
 * isPresent() : 判断是否包含值
 * orElse(T t) :  如果调用对象包含值，返回该值，否则返回t
 * orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回 s 获取的值
 * map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty()
 * flatMap(Function mapper):与 map 类似，要求返回值必须是Optional
 */
public class TestOptional {
    @Test
    public void test1() {
        Optional<Employee> optional = Optional.of(new Employee());
        Optional<Employee> op = Optional.of(null);
        System.out.println(optional.get());
        System.out.println(op.get());
    }

    @Test
    public void test2() {
        Optional<Employee> op1 = Optional.empty();
        System.out.println(op1.get());

        Optional<Employee> op = Optional.ofNullable(null);
        Optional<Employee> op2 = Optional.ofNullable(new Employee());
        System.out.println(op.get());
        System.out.println(op2.get());
    }

    @Test
    public void test3() {
        Optional<Employee> op = Optional.ofNullable(new Employee());
        if (op.isPresent()) {
            System.out.println(op.get());
        }
        Optional<Employee> op1 = Optional.ofNullable(null);
        if (op1.isPresent()) {
            System.out.println(op1.get());
        }
        Employee employee = op1.orElse(new Employee("zyc"));
        System.out.println(employee);

        Employee emp = op1.orElseGet(() -> new Employee());
        System.out.println(emp);
    }

    @Test
    public void test4() {
        Optional<Employee> op = Optional.ofNullable(new Employee(101, "张三", 18, 9999.99));
        Optional<String> op2 = op.map(Employee::getName);
        System.out.println(op2.get());
        Optional<String> op3 = op.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(op3.get());
    }

    @Test
    public void test5() {
       /* Man man = new Man();
        String name = getGodnessName(man);
        System.out.println(name);*/
        Optional<Godness> godness = Optional.ofNullable(new Godness("林志玲"));
        Optional<NewMan> op = Optional.ofNullable(new NewMan(godness));
        System.out.println(getGodnessName2(op));
    }

    //需求：获取一个男人心中女神的名字
    public String getGodnessName(Man man) {
        if (man != null) {
            Godness g = man.getGod();
            if (g != null) {
                return g.getName();
            }
        }
        return "苍老师";
    }

    public String getGodnessName2(Optional<NewMan> man) {
        return man.orElse(new NewMan())
                .getGodness()
                .orElse(new Godness("苍老师")).getName();
    }
}
