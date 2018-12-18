package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/30 9:53
 ***@Version 1.0.0
 ********************************/
public class LeetCode57 {

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (intervals.isEmpty()) {
            res.add(newInterval);
            return res;
        }

        // 寻找插入位置
        int index = 0;
        for (; index < intervals.size(); index++) {
            if (newInterval.start < intervals.get(index).start) {
                break;
            }
        }

        // 插入新数据
        intervals.add(newInterval);
        for (int i = intervals.size() - 1; i > index; i--) {
            intervals.set(i, intervals.get(i - 1));
        }
        intervals.set(index, newInterval);

        Interval curr = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            if (curr.end >= intervals.get(i).start) {
                curr.end = intervals.get(i).end > curr.end ? intervals.get(i).end : curr.end;
            } else {
                res.add(new Interval(curr.start, curr.end));
                curr = intervals.get(i);
            }
        }
        res.add(curr);
        return res;
    }

    public static void main(String[] args) {
        LeetCode57 test = new LeetCode57();

        List<Interval> list1 = new ArrayList<>();
        list1.add(new Interval(1, 3));
        list1.add(new Interval(6, 9));
        System.out.println(test.insert(list1, new Interval(2, 5)));

        List<Interval> list2 = new ArrayList<>();
        list2.add(new Interval(1, 2));
        list2.add(new Interval(3, 5));
        list2.add(new Interval(6, 7));
        list2.add(new Interval(8, 10));
        list2.add(new Interval(12, 16));
        System.out.println(test.insert(list2, new Interval(4, 8)));

        List<Interval> list3 = new ArrayList<>();
        list3.add(new Interval(1, 5));
        list3.add(new Interval(6, 8));
        System.out.println(test.insert(list3, new Interval(0, 9)));
    }
}
