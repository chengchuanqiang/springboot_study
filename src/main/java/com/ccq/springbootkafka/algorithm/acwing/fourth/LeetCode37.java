package com.ccq.springbootkafka.algorithm.acwing.fourth;

/********************************
 *** 数独
 ***@Author chengchuanqiang
 ***@Date 2019/1/8 19:16
 ***@Version 1.0.0
 ********************************/
public class LeetCode37 {

    public void solveSudoku(char[][] board) {
        if (null == board) {
            return;
        }

        int sum = board.length * board[0].length;
        dfs(board, 0, sum);
    }

    private boolean dfs(char[][] board, int index, int sum) {

        for (int i = index; i < sum; i++) {
            int x = i / 9;
            int y = i % 9;
            if (board[x][y] == '.') {
                for (int j = 1; j <= 9; j++) {
                    board[x][y] = (char) (j + '0');
                    if (validateRowAndCol(x, y, board) && validateSubBoxes(x, y, board) && dfs(board, i + 1, sum)) {
                        return true;
                    }
                    board[x][y] = '.';
                }
                return false;
            }
        }
        return true;
    }

    private boolean validateSubBoxes(int x, int y, char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (x == i && j != y || x != i && j == y) {
                    if (board[x][y] == board[i][j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean validateRowAndCol(int x, int y, char[][] board) {
        int ii = (x / 3) * 3;
        int jj = (y / 3) * 3;

        for (int i = ii; i < ii + 3; i++) {
            for (int j = jj; j < jj + 3; j++) {
                if (board[i][j] == board[x][y] && i != x && j != y) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        LeetCode37 test = new LeetCode37();
        char[][] board = new char[9][9];
        board[0] = "53..7....".toCharArray();
        board[1] = "6..195...".toCharArray();
        board[2] = ".98....6.".toCharArray();
        board[3] = "8...6...3".toCharArray();
        board[4] = "4..8.3..1".toCharArray();
        board[5] = "7...2...6".toCharArray();
        board[6] = ".6....28.".toCharArray();
        board[7] = "...419..5".toCharArray();
        board[8] = "....8..79".toCharArray();

        for (int i = 0; i < board.length; i++) {
            System.out.println(new String(board[i]));
        }
        test.solveSudoku(board);
        System.out.println("=============================");
        for (int i = 0; i < board.length; i++) {
            System.out.println(new String(board[i]));
        }

        // 验证最后生成的数独是否正确
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!test.validateRowAndCol(i, j, board) || !test.validateSubBoxes(i, j, board)) {
                    System.out.println("ERROR");
                }
            }
        }
    }
}
