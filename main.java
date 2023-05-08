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
        field gField = new field(frame);
        frame.setLayout(null);

        frame.add(gField);
        frame.setBounds(0,0,500,500);
        gField.setBounds(0,0,1000,1000);
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
            if(i%10==1){
                gField.path.nextStep();
            }
        }
    }
}