package com.ccq.springbootkafka.algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/********************************
 *** B 题 Cut The String
 ***@Author chengchuanqiang
 ***@Date 2018/7/30 12:59
 ***@Version 1.0.0
 ********************************/
public class Main {
    // 输入挂 --
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        int T = input.nextInt();
        while ((T--) > 0) {
            int len = input.nextInt();
            int m = input.nextInt();
            String s = input.next();

            int[][] ansVal = new int[len][len];
            for(int i=0; i < len; i++){
                for(int j=0; j < len; j++){
                    ansVal[i][j] = -1;
                }
            }

            // 预处理 [i,j]是否是回文串
            boolean[][] isPalindrome = new boolean[len][len];
            for (int i = 0; i < len; i++) {
                isPalindrome[i][i] = true;
            }
            for (int i = 0; i < len - 1; i++) {
                isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
            }
            for (int length = 2; length < len; length++) {
                for (int start = 0; start + length < len; start++) {
                    isPalindrome[start][start + length] = isPalindrome[start + 1][start + length - 1]
                            && s.charAt(start) == s.charAt(start + length);
                }
            }

            for(int i=0; i < len; i++){
                for(int j=0; j < len; j++){
                    System.out.print(isPalindrome[i][j] + " ");
                }
                System.out.println();
            }

            while ((m--) > 0) {
                int l = input.nextInt() - 1;
                int r = input.nextInt() - 1;
                if(ansVal[l][r] != -1){
                    System.out.println(ansVal[l][r]);
                    continue;
                }
                int ans = 0;
                //System.out.println(s.substring(l, r + 1));
                for (int i = l; i < r; i++) {
                    if (isPalindrome[l][i] && isPalindrome[i + 1][r]) {
                        ans++;
                    }
                    //System.out.println(s.substring(l, i+1) + "-----" + s.substring(i + 1, r+1));
                }
                ansVal[l][r] = ans;
                System.out.println(ans);
            }
        }
    }
}
