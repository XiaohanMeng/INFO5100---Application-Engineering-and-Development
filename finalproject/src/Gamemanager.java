import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Gamemanager {

    private ChessBoard chessboard;
    private int minimaxDepth = 3;
    private boolean humanTurn = true;
    private boolean gameOver = false;
    private boolean ComputerStarts = true; // computer goes first
    private MinimaxAB minimaxComputer;
    private int result; // 0: no winner, 1: computer win, 2: human win

    public Gamemanager(ChessBoard chessboard) {
        this.chessboard = chessboard;
        minimaxComputer = new MinimaxAB(chessboard);

        result = 0;
    }

    public void startGame() {
        // if computer starts first, put the piece in the middle of chessboard
        if(ComputerStarts)
            addPieceMove(chessboard.getBoardSize() / 2, chessboard.getBoardSize() / 2, false);

        // human turn: mouse click event
        chessboard.mouseListening(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(humanTurn){
                    humanTurn = false; // set for next computer turn
                    Thread mouseClick = new Thread(new MouseClickManagement(e));
                    mouseClick.start();
                }
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public void setDifficulty(int depth) {
        this.minimaxDepth = depth;
    }

    public void setPlayerfirst(boolean ComputerStarts) {
        this.ComputerStarts = ComputerStarts;
    }

    private class MouseClickManagement implements Runnable{
        MouseEvent event;
        private MouseClickManagement(MouseEvent event) {
            this.event = event;
        }

        public void run() {
            // when game over, the game doesn't run
            if(gameOver) {
                return;
            }
            // find where is clicked
            int posX = chessboard.calcuCorrespPos(event.getX());
            int posY = chessboard.calcuCorrespPos(event.getY());

            if(!addPieceMove(posX, posY, true)){
                humanTurn = true;
                return;
            }

            // check the winner for each move
            result = checkWinner();

            if(result == 2){    // human win
                chessboard.showWinner(result);
                gameOver = true;
                return;
            }

            // use MinMax Algorithm to get the computer next move
            int[] computermove = minimaxComputer.getNextmove(minimaxDepth);

            if(computermove == null){ // standoff when no next move
                chessboard.showWinner(0);
                gameOver = true;
                return;
            }

            // put the white stone
            addPieceMove(computermove[1], computermove[0],false);

            // check winner for each move
            result = checkWinner();

            if(result == 1){
                chessboard.showWinner(result);
                gameOver = true;
                return;
            }

            if(chessboard.Moves().size() == 0){ // no place for piece, standoff
                chessboard.showWinner(0);
                gameOver = true;
                return;
            }

            humanTurn = true; // set for next human turn
        }


    }
    private int checkWinner() {

        // check which side score is equal or higher than winning score
        if(MinimaxAB.sumScore(chessboard, true, false) >= MinimaxAB.getWinScore())
            return 2;
        if(MinimaxAB.sumScore(chessboard, false, true) >= MinimaxAB.getWinScore())
            return 1;

        return 0;
    }

    private boolean addPieceMove(int posX, int posY, boolean blackPiece) {

        // add the piece
        boolean movepiece = chessboard.putPieceGUI(posX, posY, blackPiece);
        return movepiece;
    }

}
