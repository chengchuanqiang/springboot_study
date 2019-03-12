package com.ccq.springbootkafka.algorithm.play.moveTheBox;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author: ChengChuanQiang
 * @Description: 游戏实体
 * @Date: Created in 2019/1/1 21:23
 */
public class GameData {

    private static int d[][] = {{-1, 0}, {0, 1}, {0, -1}};
    private int maxTurn;
    private int N;
    private int M;
    private Board starterBoard;
    private Board showBoard;

    public GameData(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName is null!");
        }

        Scanner scanner = null;
        File file = new File(this.getClass().getResource(fileName).getPath());
        if (!file.exists()) {
            throw new IllegalArgumentException("file " + fileName + " doesn't exist!");
        }

        try {
            FileInputStream inputStream = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(inputStream), "UTF-8");
            String turnLine = scanner.nextLine();
            this.maxTurn = Integer.parseInt(turnLine);

            ArrayList<String> lines = new ArrayList<>();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                lines.add(line);
            }

            this.starterBoard = new Board(lines.toArray(new String[lines.size()]));
            this.N = starterBoard.getN();
            this.M = starterBoard.getM();

            starterBoard.print();

            this.showBoard = new Board(starterBoard);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public Board getShowBoard() {
        return showBoard;
    }

    public boolean solve() {
        if (maxTurn <= 0) {
            return false;
        }
        return solve(new Board(starterBoard), maxTurn);
    }

    private boolean solve(Board board, int turn) {
        if (board == null) {
            throw new IllegalArgumentException("board cat not be null is solve function");
        }

        if (turn == 0) {
            return board.isWin();
        }

        if (board.isWin()) {
            return true;
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (board.getData(x, y) != Board.EMPTY) {
                    for (int i = 0; i < 3; i++) {
                        int newX = x + d[i][0];
                        int newY = y + d[i][1];
                        if (inArea(newX, newY)) {
                            String swapString = String.format("swap (%d, %d) and (%d, %d)", x, y, newX, newY);
                            Board nextBoard = new Board(board, board, swapString);
                            nextBoard.swap(x, y, newX, newY);
                            nextBoard.run();
                            if (solve(nextBoard, turn - 1)) {
                                return true;
                            }
                        }
                    }
                }
            }

        }
        return false;
    }
}
