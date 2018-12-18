package com.ccq.springbootkafka.algorithm.acwing.fourth;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2018/12/2 21:57
 */
public class LeetCode784 {

    public static void main(String[] args) {
        LeetCode784 test = new LeetCode784();

        System.out.println(test.letterCasePermutation("a1b2"));
    }

    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        dfs(res, 0, S.toCharArray(), new StringBuffer());
        return res;
    }

    public void dfs(List<String> res, int index, char[] s, StringBuffer curr) {
        if (curr.length() == s.length) {
            res.add(new String(curr));
            return;
        }

        for (int i = index; i < s.length; i++) {
            char c = s[i];
            if (c >= '0' && c <= '9') {
                curr.append(c);
                dfs(res, i + 1, s, curr);
                curr.deleteCharAt(curr.length() - 1);
            } else if (c >= 'A' && c <= 'Z') {
                curr.append(c);
                dfs(res, i + 1, s, curr);
                curr.deleteCharAt(curr.length() - 1);

                curr.append((char) (c + 32));
                dfs(res, i + 1, s, curr);
                curr.deleteCharAt(curr.length() - 1);
            } else if (c >= 'a' && c <= 'z') {
                curr.append(c);
                dfs(res, i + 1, s, curr);
                curr.deleteCharAt(curr.length() - 1);

                curr.append((char)(c - 32));
                dfs(res, i + 1, s, curr);
                curr.deleteCharAt(curr.length() - 1);
            }
        }
    }
}
