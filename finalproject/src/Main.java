public class Main {

    public static void main(String[] args) {

        int width = 500;
        MainGUI mainGUI = new MainGUI(width, width);

        mainGUI.setVisible(true);

        mainGUI.listenStart(arg0 -> {

            // Get the settings from the Main GUI manager.
            int[] settings = mainGUI.passSettings();
            int depth = settings[0];
            boolean computerStarts = settings[1] == 1;
            int size = settings[2];

            System.out.println("The settings of the game:\n" +
                    "  The difficult level is: " + depth + "\n"
                    + "  Computer Makes the first move: " + computerStarts );

            ChessBoard chessBoard = new ChessBoard(width, size);

            final Gamemanager gamemanager = new Gamemanager(chessBoard);

            PlayBoardGUI pb = new PlayBoardGUI(chessBoard.getGUI(), width);

            mainGUI.addPanel(pb.getPlaypanel());

            mainGUI.showGUI();

            gamemanager.setDifficulty(depth);
            gamemanager.setPlayerfirst(computerStarts);

            gamemanager.startGame();
        });

    }
}
