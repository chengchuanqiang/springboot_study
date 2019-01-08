package com.ccq.springbootkafka.algorithm.play.moveTheBox;

import com.ccq.springbootkafka.algorithm.test20181113.E;

/**
 * @Author: ChengChuanQiang
 * @Description:
 * @Date: Created in 2019/1/1 21:37
 */
public class Board {
    public static char EMPTY = '.';
    private static int d[][] = {{0, 1}, {1, 0}};
    private int N;
    private int M;
    private char[][] data;
    private Board preBoard = null;
    private String swapString = "";

    public Board(String[] lines) {
        if (null == lines) {
            throw new IllegalArgumentException("lines cannot be null in board constructor.");
        }

        this.N = lines.length;
        if (N == 0) {
            throw new IllegalArgumentException("lines cannot be empty in board constructor");
        }
        this.M = lines[0].length();
        data = new char[N][M];

        for (int i = 0; i < N; i++) {
            if (lines[i].length() != M) {
                throw new IllegalArgumentException("All line is length must be same in board constructor.");
            }
            for (int j = 0; j < M; j++) {
                data[i][j] = lines[i].charAt(j);
            }
        }
    }

    public Board(Board board, Board preBoard, String swapString) {
        if (null == board) {
            throw new IllegalArgumentException("board cannot be null in board constructor");
        }

        this.N = board.getN();
        this.M = board.getM();
        this.data = new char[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                this.data[i][j] = board.data[i][j];
            }
        }

        this.preBoard = preBoard;
        this.swapString = swapString;
    }

    public Board(Board board) {
        this(board, null, "");
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public char getData(int x, int y) {
        if (!inArea(x, y))
            throw new IllegalArgumentException("x, y are out of index in getData!");

        return data[x][y];
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print() {
        for (int i = 0; i < N; i++) {
            System.out.println(String.valueOf(data[i]));
        }
    }

    public void swap(int x, int y, int newX, int newY) {

        if (!inArea(x, y) || !inArea(newX, newY)) {
            throw new IllegalArgumentException("(x,y) or (newX, newY) are out of index in swap.");
        }

        char t = data[x][y];
        data[x][y] = data[newX][newY];
        data[newX][newY] = t;
    }

    public boolean isWin() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (Board.EMPTY != data[i][j]) {
                    return false;
                }
            }
        }

        printSwapInfo();
        return true;
    }

    private void printSwapInfo() {
        if (preBoard != null) {
            preBoard.printSwapInfo();
        }

        System.out.println(swapString);
        return;
    }

    public void run() {
        do {
            drop();
        } while (match());
    }

    private boolean match() {
        boolean isMatched = false;

        boolean[][] tag = new boolean[N][M];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (data[x][y] != Board.EMPTY) {
                    for (int i = 0; i < 2; i++) {
                        int newX1 = x + d[i][0];
                        int newY1 = y + d[i][1];
                        int newX2 = newX1 + d[i][0];
                        int newY2 = newY1 + d[i][1];

                        if (inArea(newX1, newY1) && inArea(newX2, newY2)
                                && data[x][y] == data[newX1][newY1] && data[x][y] == data[newX2][newY2]) {

                            tag[x][y] = true;
                            tag[newX1][newY1] = true;
                            tag[newX2][newY2] = true;

                            isMatched = true;
                        }
                    }
                }
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (tag[x][y]) {
                    data[x][y] = Board.EMPTY;
                }
            }
        }
        return isMatched;
    }

    private void drop() {
        for (int j = 0; j < M; j++) {
            int curr = N - 1;
            for (int i = N - 1; i >= 0; i--) {
                if (data[i][j] != EMPTY) {
                    data[curr][j] = data[i][j];
                    curr--;
                }
            }
            for (; curr >= 0; curr--) {
                data[curr][j] = Board.EMPTY;
            }
        }
    }

}
