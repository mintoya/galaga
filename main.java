import javax.swing.*;

public class main {
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        JPanel gField = new field(frame);
        frame.setLayout(null);

        frame.add(gField);
        gField.setBounds(0,0,100,100);
        frame.setVisible(true);
        frame.repaint();
    }
}