package com.ccq.springbootkafka.algorithm.segmentTree.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/********************************
 *** 线段树模板2
 ***@Author chengchuanqiang
 ***@Date 2018/7/24 9:40
 ***@Version 1.0.0
 ********************************/
public class Main2 {

    private static long[] sum; // 线段树 和
    private static long[] addv; // 延迟标记 加
    private static long[] mulv; // 延迟标记 乘
    private static long[] data; // 原数据
    private static int p;

    // 向上更新
    private static void pushUp(int rt) {
        sum[rt] = (sum[rt << 1] + sum[rt << 1 | 1]) % p;
    }

    // 向下更新
    private static void pushDown(int rt, int l, int r) {
        if (mulv[rt] != 1) {
            sum[rt << 1] = (sum[rt << 1] * mulv[rt]) % p;
            sum[rt << 1 | 1] = (sum[rt << 1 | 1] * mulv[rt]) % p;
            mulv[rt << 1] = (mulv[rt << 1] * mulv[rt]) % p;
            mulv[rt << 1 | 1] = (mulv[rt << 1 | 1] * mulv[rt]) % p;
            addv[rt << 1] = (addv[rt << 1] * mulv[rt]) % p;
            addv[rt << 1 | 1] = (addv[rt << 1 | 1] * mulv[rt]) % p;
            mulv[rt] = 1;
        }
        if (addv[rt] != 0) {
            int mid = (l + r) >> 1;
            sum[rt << 1] = (sum[rt << 1] + (mid - l + 1) * addv[rt]) % p;
            sum[rt << 1 | 1] = (sum[rt << 1 | 1] + (r - mid) * addv[rt]) % p;
            addv[rt << 1] = (addv[rt << 1] + addv[rt]) % p;
            addv[rt << 1 | 1] = (addv[rt << 1 | 1] + addv[rt]) % p;
            addv[rt] = 0;
        }
    }

    // 建树
    private static void build(int rt, int l, int r) {
        addv[rt] = 0;
        mulv[rt] = 1;
        if (l == r) {
            sum[rt] = data[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(rt << 1, l, mid);
        build(rt << 1 | 1, mid + 1, r);
        pushUp(rt);
    }

    // 区间修改 加
    private static void updateAdd(int rt, int l, int r, int L, int R, int V) {
        if (L <= l && R >= r) {
            sum[rt] = (sum[rt] + (r - l + 1) * V) % p;
            addv[rt] = (addv[rt] + V) % p;
            return;
        }
        pushDown(rt, l, r);
        int mid = (r + l) >> 1;
        if (L <= mid) {
            updateAdd(rt << 1, l, mid, L, R, V);
        }
        if (R > mid) {
            updateAdd(rt << 1 | 1, mid + 1, r, L, R, V);
        }
        pushUp(rt);
    }

    // 区间修改 乘
    private static void updateMul(int rt, int l, int r, int L, int R, int V) {
        if (L <= l && R >= r) {
            sum[rt] = (sum[rt] * V) % p;
            mulv[rt] = (mulv[rt] * V) % p;
            addv[rt] = (addv[rt] * V) % p;
            return;
        }
        pushDown(rt, l, r);
        int mid = (r + l) >> 1;
        if (L <= mid) {
            updateMul(rt << 1, l, mid, L, R, V);
        }
        if (R > mid) {
            updateMul(rt << 1 | 1, mid + 1, r, L, R, V);
        }
        pushUp(rt);
    }

    // 区间查询
    private static long query(int rt, int l, int r, int L, int R) {
        if (L <= l && R >= r) {
            return sum[rt] % p;
        }
        pushDown(rt, l, r);
        int mid = (l + r) >> 1;
        long ans = 0;
        if (L <= mid) {
            ans = (ans + query(rt << 1, l, mid, L, R)) % p;
        }
        if (R > mid) {
            ans = (ans + query(rt << 1 | 1, mid + 1, r, L, R)) % p;
        }
        return ans % p;
    }

    public static void main(String[] args) {
        InputReader input = new InputReader(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        p = input.nextInt();

        data = new long[n + 1];
        sum = new long[(n + 2) << 2 | 1];
        addv = new long[(n + 2) << 2 | 1];
        mulv = new long[(n + 2) << 2 | 1];
        for (int i = 1; i <= n; i++) {
            data[i] = input.nextInt();
        }
        build(1, 1, n);
        StringBuilder sbf = new StringBuilder();
        while ((m--) > 0) {
            int op = input.nextInt();
            int x = input.nextInt();
            int y = input.nextInt();
            if (op == 1) {
                int k = input.nextInt();
                updateMul(1, 1, n, x, y, k);
            } else if (op == 2) {
                int k = input.nextInt();
                updateAdd(1, 1, n, x, y, k);
            } else {
                sbf.append(query(1, 1, n, x, y)).append("\n");
            }
        }
        System.out.print(sbf.toString());
    }

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
}