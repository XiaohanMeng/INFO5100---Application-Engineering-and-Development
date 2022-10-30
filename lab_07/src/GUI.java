import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GUI extends JFrame {
    private JPanel input = new JPanel();
    private JPanel output = new JPanel();
    public ArrayList<String> filedata = new ArrayList<>();
    public GUI(String title){
        super(title);

        // set Frame layout with some margin
        setLayout(new GridLayout(1,2,10,10));

        // split planes
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, input, output);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(240);

        //Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension(100, 200);
        input.setMinimumSize(minimumSize);
        output.setMinimumSize(minimumSize);

        inputPanel();
        outputPanel();

        add(splitPane);

        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void inputPanel(){

        // set layout
        BoxLayout inputBox = new BoxLayout(input, BoxLayout.Y_AXIS);
        input.setLayout(inputBox);

        input.add(Box.createVerticalStrut(25));     // margin

        JLabel jl_input = new JLabel("Reading File name:");
        jl_input.setAlignmentX(JLabel.CENTER_ALIGNMENT);    // set label center
        input.add(jl_input);

        input.add(Box.createVerticalStrut(25));     // margin

        JTextField jtf_input = new JTextField(15);
        jtf_input.setText("annual.csv");
        jtf_input.setMaximumSize(jtf_input.getPreferredSize());     // keep jtf from expanding
        input.add(jtf_input);

        input.add(Box.createVerticalStrut(25));     // margin

        JButton inputButton = new JButton("Click to read from file");
        inputButton.setAlignmentX(JButton.CENTER_ALIGNMENT);    // set button center
        inputButton.setBackground(Color.RED);    // set the color red
        inputButton.setBorderPainted(false); // make the color above the button (for Mac)
        inputButton.setFocusPainted(false);  // remove the focus when click the button
        inputButton.setOpaque(true);     // set the button able to red

        JTextArea inputArea = new JTextArea();
        JScrollPane inputsp = new JScrollPane(inputArea);   // JTextArea is placed in a JScrollPane

        // wrap lines
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);

        inputArea.setText("First five lines of file:\n");

        inputButton.addActionListener(e -> {
            Files files = new Files();

            String pathname = jtf_input.getText();

            // read csv file
            ArrayList<String> inputData = files.readFiles(pathname);

            int count = 0;
            int lines = 5;

            // show data in GUI
            while(lines > count){
                inputArea.append(inputData.get(count)+"\n");
                count++;
            }
            filedata.addAll(inputData.subList(0,lines)); // store data for future operation
        });

        input.add(inputButton);
        input.add(Box.createVerticalStrut(25));     // margin
        input.add(inputsp);
        input.add(Box.createVerticalStrut(25));     // margin
    }

    private void outputPanel(){

        // set layout
        BoxLayout outputBox = new BoxLayout(output, BoxLayout.Y_AXIS);
        output.setLayout(outputBox);

        output.add(Box.createVerticalStrut(25));     // margin

        JLabel jl_output = new JLabel("File name to write:");
        jl_output.setAlignmentX(JLabel.CENTER_ALIGNMENT);    // set label center
        output.add(jl_output);

        output.add(Box.createVerticalStrut(25));     // margin

        JTextField jtf_output = new JTextField(15);
        jtf_output.setMaximumSize(jtf_output.getPreferredSize());     // keep jtf from expanding
        output.add(jtf_output);

        output.add(Box.createVerticalStrut(25));     // margin

        JButton outputButton = new JButton("Click to write to file");
        outputButton.setAlignmentX(JButton.CENTER_ALIGNMENT);    // set button center
        outputButton.setBackground(Color.RED);    // set the color red
        outputButton.setBorderPainted(false); // make the color above the button (for Mac)
        outputButton.setFocusPainted(false);  // remove the focus when click the button
        outputButton.setOpaque(true);     // set the button able to red

        JTextArea outputArea = new JTextArea(5,10);
        JScrollPane outputsp = new JScrollPane(outputArea);   // JTextArea is placed in a JScrollPane.

        // wrap lines
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);

        outputArea.setText("First five lines of new file:\n");

        outputButton.addActionListener(e -> {
            Files files = new Files();

            String pathname = jtf_output.getText();

            int line_c = 0;
            int lines = 5;  // lines number
            int col = 3;    // columns number

            String writedata = ""; // data storage

            // get first 5 lines
            while(lines > line_c){
                String data = filedata.get(line_c);
                System.out.println(data);

                List<String> list = Arrays.asList(data.split(",")).subList(0,col);    // get first 3 columns
                String outputdata = Arrays.toString(list.toArray()).replace("[", "").replace("]", "");
                outputArea.append(outputdata + "\n");   // show data in GUI
                writedata += outputdata + "\n";
                
                line_c++;
            }
            // write to new file
            files.writeFiles(pathname,writedata+"\n");
        });

        output.add(outputButton);
        output.add(Box.createVerticalStrut(25));     // margin
        output.add(outputsp);
        output.add(Box.createVerticalStrut(25));     // margin

    }
}
