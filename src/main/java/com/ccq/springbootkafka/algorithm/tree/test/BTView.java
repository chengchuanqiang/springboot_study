package com.ccq.springbootkafka.algorithm.tree.test;

import com.ccq.springbootkafka.algorithm.tree.BinaryTree;
import com.ccq.springbootkafka.algorithm.tree.TreeNode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/26 19:47
 ***@Version 1.0.0
 ********************************/
public class BTView extends Pane {

    private double radius = 15;
    private double vGap = 50;

    BTView() {
        setStatus("BinaryTree is empty");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    public void displayTree(TreeNode<Integer> root) {
        this.getChildren().clear();
        if (root != null) {
            displayTree(root, getWidth() / 2, vGap, getWidth() / 4);
        }
    }

    private void displayTree(TreeNode<Integer> root, double x, double y, double hGap) {
        if (root.left != null) {
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            displayTree(root.left, x - hGap, y + vGap, hGap / 2);
        }

        if (root.right != null) {
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            displayTree(root.right, x + hGap, y + vGap, hGap / 2);
        }

        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.element + ""));
    }


}
