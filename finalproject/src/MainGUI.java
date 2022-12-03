import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {

    private int difficulty;
    private boolean computerSelected;

    private JPanel mainPanel = new JPanel();
    private JPanel boardPanel;
    private JButton startButton;
    private JRadioButton normalSelection;
    private JRadioButton hardSelection;
    private JRadioButton humanSelection;
    private JRadioButton computerSelection;
    private ButtonGroup difficultyBg;
    private ButtonGroup startplayerBg;

    private JTextField chessboardInput;

    public MainGUI(int width, int height ){

        gamemainPanel();

        setSize(width,height);
        setTitle("Gomoku(Five in Row) Final Project");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void gamemainPanel(){
        // set the main GUI(start game settings)

        BoxLayout inputBox = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(inputBox);

        mainPanel.add(Box.createVerticalStrut(25));

        JLabel welcome = new JLabel("Welcome to Gomoku(Five in Row) Game");
        welcome.setFont(new Font("Calibri", Font.BOLD, 20));
        welcome.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(welcome);

        mainPanel.add(Box.createVerticalStrut(25));

        JLabel instruction = new JLabel("Please select your game:");
        instruction.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        mainPanel.add(instruction);

        mainPanel.add(Box.createVerticalStrut(25));     // margin

        // first panel for difficulty
        JPanel settingfirst = new JPanel();
        JLabel diffSelection = new JLabel("Please select the difficulty:");
        difficultyBg = new ButtonGroup();
        normalSelection = new JRadioButton("Normal level");
        hardSelection = new JRadioButton("Hard level");
        settingfirst.add(diffSelection);
        settingfirst.add(normalSelection);
        settingfirst.add(hardSelection);
        difficultyBg.add(normalSelection);
        difficultyBg.add(hardSelection);
        mainPanel.add(settingfirst);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        settingfirst.setBorder(blackline);

        // second panel for who go first
        JPanel settingsecond = new JPanel();
        JLabel playerSeletion = new JLabel("Please select who go first:");
        startplayerBg = new ButtonGroup();
        humanSelection = new JRadioButton("Human");
        computerSelection = new JRadioButton("Computer");
        settingsecond.add(playerSeletion);
        settingsecond.add(humanSelection);
        settingsecond.add(computerSelection);
        startplayerBg.add(humanSelection);
        startplayerBg.add(computerSelection);
        mainPanel.add(settingsecond);
        settingsecond.setBorder(blackline);


        // third panel for chessboard size
        JPanel settingthird = new JPanel();
        JLabel chessboardSet = new JLabel("Please enter the size of chessboard:");
        chessboardInput = new JTextField(10);
        chessboardInput.setText("19");
        settingthird.add(chessboardSet);
        settingthird.add(chessboardInput);
        mainPanel.add(settingthird);
        settingthird.setBorder(blackline);

        mainPanel.add(Box.createVerticalStrut(25));

        startButton = new JButton("Game starts!");
        startButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
        startButton.setBackground(Color.RED);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(true);
        mainPanel.add(startButton);

        mainPanel.add(Box.createVerticalStrut(25));

        add(mainPanel);
    }

    public void listenStart(ActionListener listener) {
        startButton.addActionListener(listener);
    }
    public void addPanel(JPanel panel) {
        boardPanel = panel;
    }
    public void showGUI() {
        setContentPane(boardPanel);
        validate();
        pack();
    }

    public int[] passSettings() {
        if( normalSelection.isSelected() ) {
            difficulty = 3;
        } else difficulty = 4;

        // computer first or human first
        computerSelected = computerSelection.isSelected();
        int computerStart = computerSelected ? 1 : 0;

        int size = Integer.parseInt(chessboardInput.getText());

        int[] settings = {difficulty, computerStart, size};

        return settings;
    }

}
