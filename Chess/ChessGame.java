import java.util.*;

class ChessGame {
    private ChessBoard board;
    private Player currentPlayer;
    private Player whitePlayer;
    private Player blackPlayer;

    public ChessGame() {
        board = new ChessBoard();
        whitePlayer = new Player("White");
        blackPlayer = new Player("Black");
        currentPlayer = whitePlayer;
        playGame();
    }

    private void playGame() {
        while (true) {
            board.displayBoard();
            int[] userMove = currentPlayer.getMove();

            if (board.isValidMove(userMove[0], userMove[1], userMove[2], userMove[3])) {
                board.movePiece(userMove[0], userMove[1], userMove[2], userMove[3]);

                if (isCheckmate()) {
                    displayCheckmateMessage(currentPlayer);
                    break;
                }

                switchPlayer();
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
    }

    private boolean isCheckmate() {
        // placeholder
        return false;
    }

    private void displayCheckmateMessage(Player player) {
        // System.out.println("Checkmate! " + player.getColor() + " wins!");
    }

    public static void main(String[] args) {
        new ChessGame();
    }
}

abstract class ChessPiece {
    String color;

    public ChessPiece(String color) {
        this.color = color;
    }

    abstract boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessBoard board);

    public String getColor() {
        return color;
    }
}

class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessBoard board) {
        return startRow == endRow || startCol == endCol;
    }
}

class Knight extends ChessPiece {
    public Knight(String color) {
        super(color);
    }

    @Override
    boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessBoard board) {
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
}

class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessBoard board) {
        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);
        return rowDiff == colDiff;
    }
}

class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessBoard board) {
        return startRow == endRow || startCol == endCol || Math.abs(endRow - startRow) == Math.abs(endCol - startCol);
    }
}

class Pawn extends ChessPiece {
    private boolean hasMoved; // Flag to track whether the pawn has moved

    public Pawn(String color) {
        super(color);
        this.hasMoved = false;
    }

    @Override
    boolean isValidMove(int startRow, int startCol, int endRow, int endCol, ChessBoard board) {
        int direction = getColor().equals("White") ? -1 : 1; // Adjust direction based on pawn color

        // Pawn moves forward one square
        if (endCol == startCol && endRow == startRow + direction) {
            return board.isValidPosition(endRow, endCol) && board.getPiece(endRow, endCol) == null;
        }

        // Pawn's initial two-square move
        if (!hasMoved && endCol == startCol && endRow == startRow + 2 * direction) {
            // Check if the squares between the start and end positions are empty
            return board.isValidPosition(startRow + direction, startCol)
                    && board.isValidPosition(endRow, endCol)
                    && board.getPiece(startRow + direction, startCol) == null
                    && board.getPiece(endRow, endCol) == null;
        }

        // Pawn captures diagonally
        if (Math.abs(endCol - startCol) == 1 && endRow == startRow + direction) {
            ChessPiece targetPiece = board.getPiece(endRow, endCol);
            return board.isValidPosition(endRow, endCol) && targetPiece != null && !targetPiece.getColor().equals(getColor());
        }

        return false;
    }

    // @Override
    // public void move() {
    //     // Update the hasMoved flag when the pawn is moved
    //     hasMoved = true;
    // }
}


class ChessBoard {
    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[8][8];
        initializeBoard();
    }

    private void initializeBoard() {
        board[0][0] = new Rook("Black");
        board[0][1] = new Knight("Black");
        board[0][2] = new Bishop("Black");
        board[0][3] = new Queen("Black");
        board[0][4] = new King("Black");
        board[0][5] = new Bishop("Black");
        board[0][6] = new Knight("Black");
        board[0][7] = new Rook("Black");

        board[7][0] = new Rook("White");
        board[7][1] = new Knight("White");
        board[7][2] = new Bishop("White");
        board[7][3] = new Queen("White");
        board[7][4] = new King("White");
        board[7][5] = new Bishop("White");
        board[7][6] = new Knight("White");
        board[7][7] = new Rook("White");

        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn("Black");
            board[6][i] = new Pawn("White");
        }
    }

    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board[i][j];
                System.out.print((piece != null ? piece.getColor().charAt(0) : ".") + " ");
            }
            System.out.println();
        }
    }

    public boolean isValidMove(int startRow, int startCol, int endRow, int endCol) {
        return isValidPosition(startRow, startCol) && isValidPosition(endRow, endCol) &&
                board[startRow][startCol] != null &&
                board[startRow][startCol].isValidMove(startRow, startCol, endRow, endCol, this);
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public ChessPiece getPiece(int row, int col) {
        return isValidPosition(row, col) ? board[row][col] : null;
    }

    public void movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (isValidMove(startRow, startCol, endRow, endCol)) {
            board[endRow][endCol] = board[startRow][startCol];
            board[startRow][startCol] = null;
        }
    }
}
