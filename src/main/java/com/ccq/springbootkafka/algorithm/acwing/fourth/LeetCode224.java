package com.ccq.springbootkafka.algorithm.acwing.fourth;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Stack;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/12/2 11:06
 */
public class LeetCode224 {

    public static void main(String[] args) throws ScriptException {
        LeetCode224 test = new LeetCode224();
//        System.out.println(test.calculate("111 + 1"));
//        System.out.println(test.calculate(" 2-1 + 2 "));
        System.out.println(test.calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    public int calculate1(String s) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        Object res = null;
        try {
            res = engine.eval(s);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return (int) res;
    }

    public int calculate(String s) {
        Stack<Character> op = new Stack<>();
        Stack<Integer> num = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c == '(' || c == '+' || c == '-') {
                op.push(c);
            } else if (c == ')') {
                Character curr = op.pop();
                assert curr == '(';
                if (!op.isEmpty() && (op.peek() == '+' || op.peek() == '-')) {
                    int x = num.pop();
                    int y = num.pop();
                    if (op.peek() == '+') {
                        num.push(x + y);
                    } else {
                        num.push(y - x);
                    }
                    op.pop();
                }
            } else {
                int j = i;
                while (j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    j++;
                }
                num.push(Integer.parseInt(s.substring(i, j)));
                i = j - 1;
                if (!op.isEmpty() && op.peek() != '(') {
                    int x = num.pop();
                    int y = num.pop();
                    if (op.peek() == '+') {
                        num.push(x + y);
                    } else {
                        num.push(y - x);
                    }
                    op.pop();
                }
            }
        }
        return num.pop();
    }
}
