import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class field extends JPanel {
    public field(JFrame frame){
        this.frame = frame;
        inputReader reader = new inputReader(this);
    }
    public JFrame frame;
    final int pixelSize = 10;
    ship currentBlock = new ship();
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        paintBlox(currentBlock,g);
    }
    public void moveship(int x, int y){
        currentBlock.move(x,y);
        frame.repaint();
    }
    protected void paintBlox(ship b, Graphics g){
        int size = pixelSize;
        for (int i = 0; i < b.getImmage().length; i++) {
            for (int j = 0; j < b.getImmage()[i].length; j++) {
                int[] pos = b.position.get();
                if(b.getImmage()[i][j].iscolored){
                    g.setColor(b.getImmage()[i][j].getColor());
                    g.fillRect(
                            size*(j)+((pos[1]*size)),
                            size*(i)+((pos[0]*size)),
                            size,size);

                }
            }

        }
    }
}

class inputReader {
    public inputReader(field field){
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getKeyCode());
                switch(e.getKeyCode()){
                    case(38)->{
                        field.moveship(0,-10);}//up
                    case(39)->{
                        field.moveship(-10,0);}//right
                    case(37)->{
                        field.moveship(10,0);}//left
                    case(40)->{
                        field.moveship(0,10);}//down
                    case(32)->{}//space
                }

            }
        });
    }

}
