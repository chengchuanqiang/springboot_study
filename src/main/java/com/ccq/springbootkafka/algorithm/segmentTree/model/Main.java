package com.ccq.springbootkafka.algorithm.segmentTree.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/********************************
 *** 线段树模板1
 ***@Author chengchuanqiang
 ***@Date 2018/7/24 9:40
 ***@Version 1.0.0
 ********************************/
public class Main {

    private static long[] sum; // 线段树 和
    private static long[] lazy; // 延迟标记
    private static long[] data; // 原数据

    // 向上更新
    private static void pushUp(int rt) {
        sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }

    // 向下更新
    private static void pushDown(int rt, int l, int r) {
        if (lazy[rt] != 0) {
            int mid = (l + r) >> 1;
            sum[rt << 1] += (mid - l + 1) * lazy[rt];
            sum[rt << 1 | 1] += (r - mid) * lazy[rt];
            lazy[rt << 1] += lazy[rt];
            lazy[rt << 1 | 1] += lazy[rt];
            lazy[rt] = 0;
        }
    }

    // 建树
    private static void build(int rt, int l, int r) {
        if (l == r) {
            sum[rt] = data[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(rt << 1, l, mid);
        build(rt << 1 | 1, mid + 1, r);
        pushUp(rt);
    }

    // 区间修改
    private static void update(int rt, int l, int r, int L, int R, int V) {
        if (L <= l && R >= r) {
            sum[rt] += (r - l + 1) * V;
            lazy[rt] += V;
            return;
        }
        pushDown(rt, l, r);
        int mid = (r + l) >> 1;
        if (L <= mid) {
            update(rt << 1, l, mid, L, R, V);
        }
        if (R > mid) {
            update(rt << 1 | 1, mid + 1, r, L, R, V);
        }
        pushUp(rt);
    }

    // 区间查询
    private static long query(int rt, int l, int r, int L, int R) {
        if (L <= l && R >= r) {
            return sum[rt];
        }
        pushDown(rt, l, r);
        int mid = (l + r) >> 1;
        long ans = 0;
        if (L <= mid) {
            ans += query(rt << 1, l, mid, L, R);
        }
        if (R > mid) {
            ans += query(rt << 1 | 1, mid + 1, r, L, R);
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        data = new long[n + 1];
        sum = new long[(n + 2) << 2 | 1];
        lazy = new long[(n + 2) << 2 | 1];
        st = new StringTokenizer(f.readLine());
        for (int i = 1; i <= n; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }
        build(1, 1, n);
        StringBuilder sbf = new StringBuilder();
        while ((m--) > 0) {
            st = new StringTokenizer(f.readLine());
            int op = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (op == 1) {
                int k = Integer.parseInt(st.nextToken());
                update(1, 1, n, x, y, k);
            } else {
                sbf.append(query(1, 1, n, x, y)).append("\n");
            }
        }
        System.out.print(sbf.toString());
    }
}