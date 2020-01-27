package ru.job4j.array;

public class MatrixCheck {

    public static boolean monoHorizontal(char[][] board, int i) {

        boolean result = true;

        for (int j = 0; j < 5; j++) {
            if (board[i][j] != 'X') {
                result = false;
                break;
            }
        }

        return result;
    }


    public static boolean monoVertical(char[][] board, int j) {

        boolean result = true;

        for (int i = 0; i < 5; i++) {
            if (board[i][j] != 'X') {
                result = false;
                break;
            }
        }

        return result;
    }


    public static boolean isWin(char[][] board) {

        boolean result = false;
        for (int i = 0; i < 5; i++) {
            if (board[i][i] == 'X') {
                if ((monoHorizontal(board, i) || monoVertical(board, i))) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

}
