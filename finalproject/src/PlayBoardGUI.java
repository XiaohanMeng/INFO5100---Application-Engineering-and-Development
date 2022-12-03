import javax.swing.*;
import java.awt.*;

public class PlayBoardGUI {
    private JPanel playpanel = new JPanel();

    private JPanel chess;

    public PlayBoardGUI(ChessBoardGUI bd, int height){
        chess = bd;

        JPanel instruction = new JPanel();
        instruction.setPreferredSize(new Dimension(120, height));
        BoxLayout boxLayout = new BoxLayout(instruction, BoxLayout.Y_AXIS);
        instruction.setLayout(boxLayout);
        instruction.add(Box.createVerticalStrut((height/2) - 100));

        JLabel wel = new JLabel("<html>Connect 5 pieces in any direction to win!!!</html>");
        instruction.add(wel);

        instruction.add(Box.createVerticalStrut(15));

        JLabel colorinstr1 = new JLabel("<html>Black: human</html>");
        instruction.add(colorinstr1);

        instruction.add(Box.createVerticalStrut(5));

        JLabel colorinstr2 = new JLabel("<html>White: computer</html>");
        instruction.add(colorinstr2);


        playpanel.add(instruction);
        playpanel.add(chess);
    }

    public JPanel getPlaypanel(){
        return playpanel;
    }
}
