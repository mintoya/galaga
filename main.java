import javax.swing.*;

public class main extends Thread {
    public static void main(String[] args)
    {
        main game = new main();
        game.start();
    }
    public void run()
    {
        JFrame frame = new JFrame();
        frame.setBounds(0,0,500,1080);
        // Always setBounds before initialising field
        field gField = new field(frame,true);
        frame.setLayout(null);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.repaint();
        int i = 0;
        while (true){
            try{
                synchronized (this){
                    wait(10);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i+=1;
            gField.next();

            gField.path.nextStep(i);

        }
    }
}