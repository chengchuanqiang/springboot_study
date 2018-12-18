package com.ccq.springbootkafka.algorithm.tree.test;

import com.ccq.springbootkafka.algorithm.tree.BinaryTree;
import com.ccq.springbootkafka.algorithm.tree.impl.AVLTree;
import com.ccq.springbootkafka.algorithm.tree.impl.BinarySearchTree;
import com.ccq.springbootkafka.algorithm.tree.impl.BinarySearchTree2;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/********************************
 ***
 ***@Author chengchuanqiang
 ***@Date 2018/11/26 19:45
 ***@Version 1.0.0
 ********************************/
public class BSTAnimation extends Application {

    private BinaryTree<Integer> tree = new BinarySearchTree<>();
    private BTView view = new BTView();


    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();

        MenuBar menuBar = getMenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.setTop(menuBar);
        pane.setCenter(view);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key:"), tfKey, btInsert, btDelete);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (tree.search(key)) {
                view.displayTree(tree.getRoot());
                view.setStatus(key + " is already in the tree");
            } else {
                tree.insert(key);
                view.displayTree(tree.getRoot());
                view.setStatus(key + " is inserted in the tree");
            }
        });

        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) {
                view.displayTree(tree.getRoot());
                view.setStatus(key + " is not in the tree");
            } else {
                tree.delete(key);
                view.displayTree(tree.getRoot());
                view.setStatus(key + " is deleted in the tree");
            }
        });

        Scene scene = new Scene(pane, 450, 250);
        primaryStage.setTitle("BSTAnimation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private MenuBar getMenuBar() {

        MenuBar menuBar = new MenuBar();

        Menu binaryTreeMenu = new Menu("BinarySearchTree");
        MenuItem BSTMenuItem1 = new MenuItem("recursion");
        BSTMenuItem1.setOnAction(event ->
        {
            tree = new BinarySearchTree<>();
            view.getChildren().clear();
            System.out.println("instance BinarySearchTree");
        });

        MenuItem BSTMenuItem2 = new MenuItem("non-recursion");

        BSTMenuItem2.setOnAction(event -> {
            tree = new BinarySearchTree2<>();
            view.getChildren().clear();
            System.out.println("instance BinarySearchTree2");
        });

        binaryTreeMenu.getItems().addAll(BSTMenuItem1, BSTMenuItem2);

        Menu avlMenu = new Menu("AVLTree");
        MenuItem AVLMenuItem1 = new MenuItem("recursion");

        MenuItem AVLMenuItem2 = new MenuItem("non-recursion");
        AVLMenuItem2.setOnAction(event -> {
            tree = new AVLTree<>();
            view.getChildren().clear();
            System.out.println("instance AVLTree");
        });

        avlMenu.getItems().addAll(AVLMenuItem1, AVLMenuItem2);

        Menu redBlackTreeMenu = new Menu("RedBlackTree");
        MenuItem rbMenuItem1 = new MenuItem("recursion");
        MenuItem rbMenuItem2 = new MenuItem("non-recursion");
        redBlackTreeMenu.getItems().addAll(rbMenuItem1, rbMenuItem2);

        menuBar.getMenus().addAll(binaryTreeMenu, avlMenu, redBlackTreeMenu);
        return menuBar;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
