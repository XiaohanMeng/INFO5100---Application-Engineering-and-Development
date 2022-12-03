import java.util.ArrayList;

public class MinimaxAB {

    private ChessBoard chessboard;

    private static int winScore = (int) 1e8;

    private static int evaluateNum = 0;      // tracking numbers of evaluations

    public MinimaxAB(ChessBoard chessboard) {
        this.chessboard = chessboard;
    }

    private static double AgainstBlackPiece(ChessBoard chessBoard, boolean blacksTurn) {
        evaluateNum++;

        // Get board score of both players.
        double blackScore = sumScore(chessBoard, true, blacksTurn);
        double whiteScore = sumScore(chessBoard, false, blacksTurn);

        if(blackScore == 0) {
            blackScore = 1.0;
        }

        // Calculate relative score of white against black
        return whiteScore / blackScore;
    }

    public static int sumScore(ChessBoard chessboard, boolean blackPiece, boolean blacksTurn) {

        // Read the board
        int[][] boardMatrix = chessboard.getChessMatrix();

        // three dimension scores
        int score = HorizontalScore(boardMatrix, blackPiece, blacksTurn) + VerticalScore(boardMatrix, blackPiece, blacksTurn)
                + DiagonalScore(boardMatrix, blackPiece, blacksTurn);

        return score;
    }

    public int[] getNextmove(int depth) {
        int[] nextmove = new int[2];

        // search move for winning
        Object[] winningMove = checkwinMove(chessboard);

        if(winningMove != null ) {      // find the move for winning, end the game
            nextmove[0] = (Integer)(winningMove[1]);
            nextmove[1] = (Integer)(winningMove[2]);
        } else {
            // no move for winning, find the best move by MinMax ab algorithm
            winningMove = MMAB(depth, chessboard, true, -1.0, getWinScore());
            if(winningMove[1] == null) {
                nextmove = null;
            } else {
                nextmove[0] = (Integer)(winningMove[1]);
                nextmove[1] = (Integer)(winningMove[2]);
            }
        }

        evaluateNum = 0;

        return nextmove;
    }


    // MinMax ab algorithm explanation:
    // https://www.hackerearth.com/blog/developers/minimax-algorithm-alpha-beta-pruning/
    // alpha for Max node, beta for Min node
    // calculate for white pieces
    private static Object[] MMAB(int depth, ChessBoard chessBoard, boolean Max, double alpha, double beta) {

        if(depth == 0){     // the bottom layer of MM tree
            Object[] againstscore = {AgainstBlackPiece(chessBoard, !Max), null, null};
            return againstscore;
        }

        ArrayList<int[]> allMoves = chessBoard.Moves();

        if(allMoves.size() == 0) {      // no move means it's leaf node, return
            Object[] againstscore = {AgainstBlackPiece(chessBoard, !Max), null, null};
            return againstscore;
        }

        Object[] next = new Object[3];

        // go through the MM tree
        if(Max) {
            next[0] = -1.0;     // initialize as negative

            // go through all moves
            for(int[] move : allMoves) {
                ChessBoard tempboard = new ChessBoard(chessBoard);      // temporary board for calculation
                tempboard.putPiece(move[1], move[0], false);

                // go down to search subtree
                Object[] tempMove = MMAB(depth - 1, tempboard, !Max, alpha, beta);

                // update alpha followed the algorithm
                if((Double)(tempMove[0]) > alpha) {
                    alpha = (Double)(tempMove[0]);
                }

                // Pruning node by beta followed the algorithm
                if((Double)(tempMove[0]) >= beta) {
                    return tempMove;
                }

                // find the node with max score
                if((Double)tempMove[0] > (Double)next[0]) {
                    next = tempMove;
                    next[1] = move[0];
                    next[2] = move[1];
                }
            }
        } else {
            // Initialize with the first node
            next[0] = 1e9;
            next[1] = allMoves.get(0)[0];
            next[2] = allMoves.get(0)[1];

            // go through all moves
            for(int[] move : allMoves) {
                ChessBoard tempChessBoard = new ChessBoard(chessBoard);
                tempChessBoard.putPiece(move[1], move[0], true);

                // go down to search subtree
                Object[] tempMove = MMAB(depth - 1, tempChessBoard, !Max, alpha, beta);

                // update beta
                if(((Double)tempMove[0]) < beta) {
                    beta = (Double)(tempMove[0]);
                }

                // Pruning node by alpha
                if((Double)(tempMove[0]) <= alpha) {
                    return tempMove;
                }

                // find the node with min score
                if((Double)tempMove[0] < (Double)next[0]) {
                    next = tempMove;
                    next[1] = move[0];
                    next[2] = move[1];
                }
            }
        }

        return next;       // the best node(move) in current depth
    }

    private static Object[] checkwinMove(ChessBoard chessBoard) {
        // find the move can win instantly

        ArrayList<int[]> allMoves = chessBoard.Moves();
        Object[] winMove = new Object[3];

        // go through all possible moves
        for(int[] move : allMoves) {
            evaluateNum++;

            // set a temporary board to find the move for white
            ChessBoard tempboard = new ChessBoard(chessBoard);
            tempboard.putPiece(move[1], move[0], false);

            if(sumScore(tempboard,false,false) >= winScore) {
                winMove[1] = move[0];
                winMove[2] = move[1];
                return winMove;
            }
        }
        return null;
    }

    private static int HorizontalScore(int[][] matrix, boolean blackplayer, boolean blackTurns){
        int continuous = 0;
        int block = 2; // if the continuous piece is blocked two side, set 2; one side, set 1; no blocked, set 0
        int score = 0;

        // go through all the rows
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                if(matrix[i][j] == (blackplayer ? 2 : 1)) {      // check if the square has piece
                    continuous++;
                } else if (matrix[i][j] == 0) { // check if is empty
                    if(continuous > 0){
                        block--;    // no block here
                        // score for continuous
                        score += getContinuousScore(continuous, block, blackplayer == blackTurns);
                        continuous = 0;     // reset
                        block = 1;      // next set will be 1 at maximum
                    }else {
                        // no continuous and it's empty, next set will be 1 at maximum
                        block = 1;
                    }
                } else if (continuous > 0) {    // enemy take the square, check continuous
                    score += getContinuousScore(continuous, block, blackplayer == blackTurns);
                    continuous = 0;     // reset
                    block = 2;      // 2 side since enemy may take
                } else {
                    block = 2;      // 2 side since enemy may take
                }
            }

            if(continuous > 0){
                score += getContinuousScore(continuous, block, blackplayer == blackTurns);
            }

            // reset for next calculation
            continuous = 0;
            block = 2;
        }
        return score;
    }

    private static int VerticalScore(int[][] matrix, boolean blackplayer, boolean blackTurns){
        int continuous = 0;
        int block = 2;
        int score = 0;

        // go through all columns, similar with Horizontal function
        for(int j = 0; j < matrix[0].length; j++) {
            for(int i = 0; i < matrix.length; i++) {
                if(matrix[i][j] == (blackplayer ? 2 : 1)) {     // check piece exist
                    continuous++;
                } else if(matrix[i][j] == 0) {
                    if(continuous > 0) {
                        block--;       // no block here
                        score += getContinuousScore(continuous, block, blackplayer == blackTurns);
                        continuous = 0;     // reset
                        block = 1;     // next set will be 1 at maximum
                    } else {
                        // no continuous and it's empty, next set will be 1 at maximum
                        block = 1;
                    }
                } else if(continuous > 0) {     // enemy take the square, check continuous
                    score += getContinuousScore(continuous, block, blackplayer == blackTurns);
                    continuous = 0;     // reset
                    block = 2;     // 2 side since enemy may take
                } else {
                    block = 2;     // 2 side since enemy may take
                }
            }
            if(continuous > 0) {
                score += getContinuousScore(continuous, block, blackplayer == blackTurns);
            }
            // reset
            continuous = 0;
            block = 2;

        }
        return score;
    }

    private static int DiagonalScore(int[][] matrix, boolean blackplayer, boolean blackTurns ) {

        int consecutive = 0;
        int blocks = 2;
        int score = 0;

        // go through from bottom-left to top-right
        for (int i = 0; i <= 2 * (matrix.length - 1); i++) {
            int startPoint = Math.max(0, i - matrix.length + 1);
            int endPoint = Math.min(matrix.length - 1, i);

            for (int j = startPoint; j <= endPoint; j++) {
                int k = i - j;

                if(matrix[j][k] == (blackplayer ? 2 : 1)) {      // check piece exist
                    consecutive++;
                } else if (matrix[j][k] == 0) {
                    if(consecutive > 0) {
                        blocks--;       // no block here
                        score += getContinuousScore(consecutive, blocks, blackplayer == blackTurns);
                        consecutive = 0;        // reset
                        blocks = 1;     // next set will be 1 at maximum
                    } else {
                        // no continuous and it's empty, next set will be 1 at maximum
                        blocks = 1;
                    }
                } else if(consecutive > 0) {         // enemy take the square, check continuous
                    score += getContinuousScore(consecutive, blocks, blackplayer == blackTurns);
                    consecutive = 0;
                    blocks = 2;     // 2 side since enemy may take
                } else {
                    blocks = 2;     // 2 side since enemy may take
                }
            }
            if(consecutive > 0) {
                score += getContinuousScore(consecutive, blocks, blackplayer == blackTurns);
            }
            consecutive = 0;
            blocks = 2;
        }

        // go through from top-left to bottom-right, similar to above
        for (int i = 1 - matrix.length; i < matrix.length; i++) {
            int startPoint = Math.max(0, i);
            int endPoint = Math.min(matrix.length + i - 1, matrix.length - 1);
            for (int j = startPoint; j <= endPoint; j++) {
                int k = j - i;

                if(matrix[j][k] == (blackplayer ? 2 : 1)) {
                    consecutive++;
                } else if(matrix[j][k] == 0) {
                    if(consecutive > 0) {
                        blocks--;
                        score += getContinuousScore(consecutive, blocks, blackplayer == blackTurns);
                        consecutive = 0;
                        blocks = 1;
                    } else {
                        blocks = 1;
                    }
                } else if(consecutive > 0) {
                    score += getContinuousScore(consecutive, blocks, blackplayer == blackTurns);
                    consecutive = 0;
                    blocks = 2;
                } else {
                    blocks = 2;
                }
            }
            if(consecutive > 0) {
                score += getContinuousScore(consecutive, blocks, blackplayer == blackTurns);
            }
            consecutive = 0;
            blocks = 2;
        }
        return score;
    }

    private static int getContinuousScore(int continuous, int block, boolean thisTurn) {
        int willWinscore = (int) 1e6;

        // my idea of giving the score is prioritizing the likelihood of winning in each situation
        if(block == 2 && continuous < 5)    // both side blocked, no score
            return 0;

        switch(continuous) {
            case 5: {
                return winScore;    // five continuous pieces, winning
            }
            case 4: {
                if(thisTurn) {      // 4 continuous piece in this turn, must be winning
                    return willWinscore;
                } else {        // in human turn
                    if(block == 0) {    // if two side are not blocked, will win in next move
                        return willWinscore / 4;
                    }else {         // if one side is blocked, the winner depends on place human put
                        return willWinscore / 100;
                    }
                }
            }
            case 3: {
                if(block == 0) {
                    // both side free, and in the current turn, will win;
                    // but the priority is lower than 4 pieces with both side free
                    if(thisTurn) {
                        return willWinscore / 5;
                    }
                    else{       //enemy's turn, human may block one side
                        return willWinscore / 10;
                    }
                } else {    // three with onside blocked
                    if(thisTurn) {
                        return willWinscore / 100;
                    } else {
                        return willWinscore / 1000;
                    }
                }
            }
            case 2: {
                if(block == 0) {
                    if(thisTurn) {
                        return willWinscore / 500;
                    } else {
                        return willWinscore / 1000;
                    }
                } else {
                    return willWinscore / 10000;
                }
            }
            case 1: {
                return willWinscore / 100000;
            }
        }
        // more than 5
        return winScore;
    }

    public static int getWinScore() {
        return winScore;
    }
}
