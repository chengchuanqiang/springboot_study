package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/21 9:47
 ***@Version 1.0.0
 ********************************/
public class LeetCode56 {

    public List<Interval> merge(List<Interval> intervals) {

        if (intervals.isEmpty() || intervals.size() == 1) {
            return intervals;
        }

        List<Interval> res = new ArrayList<>();
        intervals.sort(Comparator.comparing(interval -> interval.start));
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
        LeetCode56 test = new LeetCode56();

        List<Interval> list1 = new ArrayList<>();
        list1.add(new Interval(1, 3));
        list1.add(new Interval(2, 6));
        list1.add(new Interval(8, 10));
        list1.add(new Interval(15, 18));
        System.out.println(test.merge(list1));

        List<Interval> list2 = new ArrayList<>();
        list2.add(new Interval(1, 4));
        list2.add(new Interval(2, 3));
        System.out.println(test.merge(list2));
    }
}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }

    @Override
    public String toString() {
        return "Interval{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
