package com.example.tictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button eight;

    @FXML
    private Button five;

    @FXML
    private Button four;

    @FXML
    private Button nine;

    @FXML
    private Button one;

    @FXML
    private Button seven;

    @FXML
    private Button six;

    @FXML
    private Button three;

    @FXML
    private Button two;

    @FXML
    private Button restartButton;

    @FXML
    private Label info;

    private boolean gameOver = false;

    private char currentPlayer = 'x';

    private Button[] board;

    private final int[][] winCombinations = {
            {0, 1, 2},
            {0, 3, 6},
            {0, 4, 8},
            {1, 4, 7},
            {2, 5, 8},
            {2, 4, 6},
            {3, 4, 5},
            {6, 7, 8}
    };

    @FXML
    void buttonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();

        if (gameOver || !button.getText().isEmpty()) return;

        button.setText(Character.toString(currentPlayer));
        update();
    }

    void update() {
        for (int[] comb : winCombinations) {
            Button a = board[comb[0]], b = board[comb[1]], c = board[comb[2]];

            if (a.getText().equals(b.getText()) && a.getText().equals(c.getText()) && !a.getText().isEmpty()) {
                gameOver(Character.toUpperCase(currentPlayer) + " wins!");
                return;
            }
        }

        if (checkDraw()) {
            gameOver("Draw!");
            return;
        }

        currentPlayer = (currentPlayer == 'x') ? 'o' : 'x';
        info.setText(Character.toUpperCase(currentPlayer) + "'s turn");
    }

    boolean checkDraw() {
        boolean draw = true;
        for (Button btn : board) {
            if (btn.getText().isEmpty())
                draw = false;
        }
        return draw;
    }

    void gameOver(String str) {
        Alert alert = new Alert(AlertType.INFORMATION, str, ButtonType.OK);
        restartButton.setVisible(true);
        gameOver = true;
        info.setText(str);
    }

    @FXML
    void restart() {
        for (Button btn : board) {
            btn.setText("");
        }
        info.setText("X's turn");
        currentPlayer = 'x';
        restartButton.setVisible(false);
        gameOver = false;
    }

    @FXML
    void initialize() {
        board = new Button[]{
                one, two, three,
                four, five, six,
                seven, eight, nine,
        };
    }

}
