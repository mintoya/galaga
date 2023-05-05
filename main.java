import javax.swing.*;

public class main {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        JPanel gField = new field(frame);
        frame.setLayout(null);

        frame.add(gField);
        frame.setBounds(0,0,500,500);
        gField.setBounds(0,0,1000,1000);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.repaint();
    }
}