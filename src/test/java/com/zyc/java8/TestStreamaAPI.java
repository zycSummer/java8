package com.zyc.java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.zyc.java8.entity.Employee;
import org.junit.Test;

import javax.xml.transform.Source;

/*
 * 一、Stream API 的操作步骤：
 *
 * 1. 创建 Stream
 * 2. 中间操作
 * 3. 终止操作(终端操作)
 */
public class TestStreamaAPI {

    //1. 创建 Stream
    @Test
    public void test1() {
        //1. Collection 提供了两个方法  stream() 与 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        Stream<String> parallelStream = list.parallelStream();

        //2. 通过 Arrays 中的 stream() 获取一个数组流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);

        //3. 通过 Stream 类中静态方法 of()
        Stream<String> stream2 = Stream.of("aa", "bb", "cc");

        //4. 创建无限流
        // 迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 2);
        stream3.limit(10).forEach(System.out::println);

        //生成
        Stream<Double> stream4 = Stream.generate(Math::random).limit(2);
        stream4.forEach(System.out::println);
    }

    //2. 中间操作
    /*
	  筛选与切片
		filter——接收 Lambda ， 从流中排除某些元素。
		limit——截断流，使其元素不超过给定数量。
		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "zyc", 8, 9999.77),
            new Employee(104, "zyc", 8, 9999.77),
            new Employee(104, "zyc", 8, 9999.77),
            new Employee(104, "wkx", 8, 4444.77),
            new Employee(105, "田七", 38, 3555.55)
    );

    List<Employee> emps2 = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Employee.Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Employee.Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Employee.Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Employee.Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Employee.Status.BUSY)
    );

    //内部迭代：迭代操作 Stream API 内部完成
    @Test
    public void test2() {
        emps.stream().filter((e) -> e.getAge() > 35 && e.getSalary() > 6000).forEach(System.out::println);
    }

    //外部迭代
    @Test
    public void test3() {
        Iterator<Employee> it = emps.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Test
    public void test4() {
        emps.stream().filter((e) -> {
            System.out.println("短路");
            return e.getSalary() > 4000;
        }).limit(2).forEach(System.out::println);
    }

    @Test
    public void test5() {
        emps.stream().filter((e) -> {
            return e.getSalary() > 4000;
        }).skip(2).forEach(System.out::println);
    }

    @Test
    public void test6() {
        emps.stream().filter((e) -> {
            return e.getSalary() > 4000;
        }).skip(2).distinct().forEach(System.out::println);
    }

    /*
    映射
    map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
    flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
 */
    @Test
    public void test7() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
       /* list.stream().map((str) -> str.toUpperCase()).forEach(System.out::println);

        System.out.println("------------");
        emps.stream().map(Employee::getName).forEach(System.out::println);
        System.out.println("------------");
*/
      /*  Stream<Stream<Character>> streamStream = list.stream().map(TestStreamaAPI::filterCharacter);
        streamStream.forEach((sm) -> {
            sm.forEach(System.out::println);
        });*/

        Stream<Character> characterStream = list.stream().flatMap(TestStreamaAPI::filterCharacter);
        characterStream.forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    /*
        sorted()——自然排序
        sorted(Comparator com)——定制排序
    */
    @Test
    public void test8() {
        List<String> list = Arrays.asList("ccc", "aaa", "bbb", "ddd");
        list.stream().sorted().forEach(System.out::println);

        System.out.println("------------");
        emps.stream().sorted((x, y) -> {
            if (x.getAge() == y.getAge()) {
                return x.getName().compareTo(y.getName());
            } else {
                return Integer.compare(x.getAge(), y.getAge());
            }
        }).forEach(System.out::println);
    }

    //3. 终止操作
	/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */
    @Test
    public void test9() {
        System.out.println(emps2.stream().allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY)));
        System.out.println(emps2.stream().anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY)));
        System.out.println(emps2.stream().noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY)));
        Optional<Employee> first = emps2.stream().sorted((x, y) -> -Integer.compare(x.getAge(), y.getAge())).findFirst();
        System.out.println(first.get());
        Optional<Employee> any = emps2.stream().filter(e -> e.getStatus().equals(Employee.Status.FREE)).findAny();
        System.out.println(any.get());
    }

    @Test
    public void test10() {
        System.out.println(emps2.stream().count());
        Optional<Employee> max = emps2.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
        System.out.println(max.get());
        Optional<Double> min = emps2.stream().map(Employee::getSalary).min(Double::compare);
        System.out.println(min.get());
    }

    /*
		归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */
    @Test
    public void test11() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list.stream().reduce(0, (x, y) -> x + y));
        System.out.println("-------------");
        Optional<Double> reduce = emps2.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(reduce.get());
    }

    /*
        收集
        collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test12() {
        List<String> list = emps2.stream().map(Employee::getName).collect(Collectors.toList());
        list.forEach(System.out::println);
        System.out.println("-----------------");
        Set<String> set = emps2.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
        System.out.println("-----------------");
        HashSet<String> hs = emps2.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hs.forEach(System.out::println);
    }

    @Test
    public void test13() {
        //总数
        System.out.println(emps2.stream().collect(Collectors.counting()));
        //平均值
        System.out.println(emps2.stream().collect(Collectors.averagingDouble(Employee::getSalary)));
        //总和
        System.out.println(emps2.stream().collect(Collectors.summingDouble(Employee::getSalary)));
        //最大值
        Optional<Employee> employee = emps2.stream().collect(Collectors.maxBy((x, y) -> Double.compare(x.getSalary(), y.getSalary())));
        System.out.println(employee.get());
        //最小值
        Optional<Double> min = emps2.stream().map(Employee::getSalary).collect(Collectors.minBy((Double::compare)));
        System.out.println(min.get());
    }

    //分组
    @Test
    public void test14() {
        Map<Employee.Status, List<Employee>> map = emps2.stream()
                .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    //多级分组
    @Test
    public void test15() {
        Map<Employee.Status, Map<String, List<Employee>>> map = emps2.stream()
                .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() >= 60)
                        return "老年";
                    else if (e.getAge() >= 35)
                        return "中年";
                    else
                        return "成年";
                })));
        System.out.println(map);
    }

    //分区
    @Test
    public void test16() {
        Map<Boolean, List<Employee>> map = emps2.stream().collect(Collectors.partitioningBy((e) -> e.getSalary() > 5000));
        System.out.println(map);
    }

    @Test
    public void test17() {
        DoubleSummaryStatistics collect = emps2.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(collect.getSum());
        System.out.println(collect.getMax());
        System.out.println(collect.getMin());
    }

    @Test
    public void test18() {
        String collect = emps2.stream().map(Employee::getName).collect(Collectors.joining(","));
        System.out.println(collect);
    }

    @Test
    public void test19(){
        Optional<Double> sum = emps2.stream()
                .map(Employee::getSalary)
                .collect(Collectors.reducing(Double::sum));
        System.out.println(sum.get());
    }
}
