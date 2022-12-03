import java.awt.event.MouseListener;
import java.util.ArrayList;



public class ChessBoard {

    private ChessBoardGUI chessgui;
    private int boardSize;

    private int[][] chessMatrix; // 0: Empty 1: White 2: Black


    public ChessBoard(int boardWidth, int boardSize) {
        chessgui = new ChessBoardGUI(boardWidth, boardSize);
        chessMatrix = new int[boardSize][boardSize];
        this.boardSize = boardSize;

    }

    public ChessBoard(ChessBoard chessBoard) {

        // copy the board and the board matrix
        int[][] copymatrix = chessBoard.getChessMatrix();
        chessMatrix = new int[copymatrix.length][copymatrix.length];
        for(int i = 0; i < copymatrix.length; i++) {
            for(int j = 0; j < copymatrix.length; j++) {
                chessMatrix[i][j] = copymatrix[i][j];
            }
        }
    }

    public int getBoardSize() {
        return chessMatrix.length;
    }
    public void putPiece(int posX, int posY, boolean black) {
        chessMatrix[posY][posX] = black ? 2 : 1;
    }

    public boolean putPieceGUI(int posX, int posY, boolean black) {

        // Check if the cell is empty
        if(chessMatrix[posY][posX] != 0) {
            return false;
        }

        // place piece on chessboard
        chessgui.setPiece(posX, posY, black);
        chessMatrix[posY][posX] = black ? 2 : 1;

        return true;

    }
    public ArrayList<int[]> Moves() {
        // Find a square with at least one stone nearby

        ArrayList<int[]> moveList = new ArrayList<>();

        // go through each square of the chessboard
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {

                // have stone situation
                if(chessMatrix[i][j] > 0)
                    continue;

                // check the square(not at the border)
                if(i > 0) { //check left and middle side
                    if(j > 0) {
                        // check if the nearby square is empty
                        // top-left, top
                        if(chessMatrix[i-1][j-1] > 0 || chessMatrix[i][j-1] > 0) {
                            int[] move = {i,j};
                            moveList.add(move);
                            continue;
                        }
                    }
                    if(j < boardSize-1) {
                        // bottom-left, left
                        if(chessMatrix[i-1][j+1] > 0 || chessMatrix[i][j+1] > 0) {
                            int[] move = {i,j};
                            moveList.add(move);
                            continue;
                        }
                    }
                    // left
                    if(chessMatrix[i-1][j] > 0) {
                        int[] move = {i,j};
                        moveList.add(move);
                        continue;
                    }
                }
                if( i < boardSize-1) { // check right and middle side
                    if(j > 0) {
                        // top-right, top
                        if(chessMatrix[i+1][j-1] > 0 || chessMatrix[i][j-1] > 0) {
                            int[] move = {i,j};
                            moveList.add(move);
                            continue;
                        }
                    }
                    if(j < boardSize-1) {
                        // bottom-right, bottom
                        if(chessMatrix[i+1][j+1] > 0 || chessMatrix[i][j+1] > 0) {
                            int[] move = {i,j};
                            moveList.add(move);
                            continue;
                        }
                    }
                    // right
                    if(chessMatrix[i+1][j] > 0) {
                        int[] move = {i,j};
                        moveList.add(move);
                        continue;
                    }
                }
            }
        }
        return moveList;
    }

    public int[][] getChessMatrix() {
        return chessMatrix;
    }

    public void mouseListening(MouseListener listener) {
        chessgui.attachListener(listener);
    }
    public ChessBoardGUI getGUI() {
        return chessgui;
    }

    public int calcuCorrespPos(int x) {
        return chessgui.calcuCorrespPos(x);
    }

    public void showWinner(int winner) {
        chessgui.showWinner(winner);
    }
}
