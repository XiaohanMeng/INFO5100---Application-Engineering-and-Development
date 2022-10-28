import javax.swing.*;
import java.awt.*;

public class UIFrame extends JFrame {
    private Double[] data = new Double[3];
    private WeightedGrade wg = new WeightedGrade();

    private JTextField tf_total = new JTextField(20);
    private JTextField tf_earned = new JTextField(10);
    private JTextField tf_per = new JTextField(10);


    public UIFrame(String title){
        super(title);

        // set Frame layout to make sure it has some margin
        setLayout(new FlowLayout(1,20,50));

        inputPanel();

        outputPanel();

        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inputPanel(){
        JPanel input = new JPanel();
        input.setSize(new Dimension(500,300));

        // set layout for input panel
        GridLayout layout = new GridLayout(2,2);
        input.setLayout(layout);

        // Total Grade
        JPanel total = new JPanel(new GridLayout(2,1));
        total.setPreferredSize(new Dimension(200,50));
        JLabel jl_total = new JLabel("Total Grade");
        total.add(jl_total);
        total.add(tf_total);

        // Earned Grade
        JPanel earned = new JPanel(new GridLayout(2,1));
        total.setPreferredSize(new Dimension(200,50));
        JLabel jl_earned = new JLabel("Earned Grade");
        earned.add(jl_earned);
        earned.add(tf_earned);

        // Percentage
        JPanel per = new JPanel(new GridLayout(2,1));
        total.setPreferredSize(new Dimension(200,50));
        JLabel jl_per = new JLabel("Percentage(%)");
        per.add(jl_per);
        per.add(tf_per);

        // set margin for input panel
        layout.setHgap(25);
        layout.setVgap(25);

        // add components for input panel
        input.add(total);
        input.add(earned);
        input.add(per);

        // add the input panel to the Frame
        add(input);
    }

    private void outputPanel(){

        // set the layout for output panel
        GridLayout outputLayout = new GridLayout(2,1);
        JPanel output = new JPanel(outputLayout);
        output.setSize(new Dimension(500,300));

        // set the margin for output panel
        outputLayout.setHgap(25);
        outputLayout.setVgap(20);

        JLabel jl_score = new JLabel();

        JButton jb = new JButton("Click to calculate Score");
        jb.setBackground(Color.RED);    // set the color red
        jb.setBorderPainted(false); // make the color above the button (for Mac)
        jb.setFocusPainted(false);  // remove the focus when click the button
        jb.setOpaque(true);     // set the button able to red


        // button action listener, it activates when the button is clicked
        jb.addActionListener((e) -> {
            this.data[0] = Double.parseDouble(tf_total.getText());
            this.data[1] = Double.parseDouble(tf_earned.getText());
            this.data[2] = Double.parseDouble(tf_per.getText());

            wg = new WeightedGrade(this.data[0], this.data[1], this.data[2]);
            wg.CalculateGrades();   // use method in WeightedGrade class to calculate result

            jl_score.setText("<html>Weighted Score:<br/>" + wg.getTotalWeightedGrade() + "</html>");

            jl_score.setHorizontalAlignment(SwingConstants.CENTER);     // set score center

            jl_score.setVisible(true);

        });

        // add components for output panel
        output.add(jb);
        output.add(jl_score);

        // add the out panel to the Frame
        add(output);
    }
}
