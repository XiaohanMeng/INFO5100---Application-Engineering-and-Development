import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;


public class ChessBoardGUI extends JPanel {

    private Graphics2D g2D;
    private BufferedImage img;

    private int boardWidth; // length of the board
    private int boardSize;
    private int squareWidth; // length of a square

    public ChessBoardGUI(int boardWidth, int boardSize) {

        this.boardWidth = boardWidth;
        this.boardSize = boardSize;
        this.squareWidth = boardWidth / boardSize; // calculate the width of each square

        // chessboard
        img = new BufferedImage(boardWidth, boardWidth, BufferedImage.TYPE_INT_ARGB); // set the chessboard
        g2D = (Graphics2D) img.getGraphics();
        Color boardcolor = new Color(242,176,109);
        g2D.setColor(boardcolor);
        g2D.fillRect(0,0, boardWidth, boardWidth); // paint

        // chessboard lines
        g2D.setColor(Color.black);
        // Vertical
        for(int i = 0; i < boardSize; i++) {
            g2D.drawLine((i + 1) * squareWidth, 0, (i + 1) * squareWidth, boardWidth);
        }
        // Horizontal
        for(int i = 0; i < boardSize; i++) {
            g2D.drawLine(0, (i + 1) * squareWidth, boardWidth, (i + 1) * squareWidth);
        }
    }

    public int calcuCorrespPos(int x) {
        // get the corresponding position of the chessboard
        if(x >= boardWidth) x = boardWidth - 1;

        int pos = (int) ( x * boardSize / boardWidth);

        return pos;
    }

    public Dimension getPreferredSize() {
        return new Dimension(boardWidth, boardWidth);
    }
    public void showWinner(int winner) {
        FontMetrics metrics = g2D.getFontMetrics(g2D.getFont());

        // three winner conditions
        String wintext = winner == 2 ? "Human Win!" : (winner == 1 ? "Computer Win!" : "Standoff!");

        // String settings
        g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2D.setFont(new Font(g2D.getFont().getName(), Font.PLAIN, 48));
        g2D.setColor(Color.black);
        int x = (boardWidth / 2  - metrics.stringWidth(wintext) * 2);
        int y = boardWidth / 2;

        // human win: green, others conditions: red
        g2D.setColor(winner == 2 ? Color.green : Color.red);
        g2D.drawString(wintext, x, y);

        repaint();

    }
    public void setPiece(int posX, int posY, boolean black) {

        // piece outside the board
        if(posX >= boardSize || posY >= boardSize)
            return;

        // set color and draw
        g2D.setColor(black ? Color.black : Color.white);
        g2D.fillOval((int)(squareWidth *(posX+0.05)), (int)(squareWidth *(posY+0.05)),
                (int)(squareWidth *0.9), (int)(squareWidth *0.9));

        repaint();
    }

    @Override
    protected void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);

        Graphics2D g2D = (Graphics2D) graphics.create();

        // game chessboard
        g2D.drawImage(img, 0, 0, boardWidth, boardWidth, null);

        // border set red
        g2D.setColor(Color.red);
        g2D.setStroke(new BasicStroke(4));
        g2D.drawRect(0, 0, boardWidth, boardWidth);
    }

    public void attachListener(MouseListener listener) {
        addMouseListener(listener);
    }
}
