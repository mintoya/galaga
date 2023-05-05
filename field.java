import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class field extends JPanel {
    public field(JFrame frame){
        this.frame = frame;
        frame.addKeyListener(new keyReader(this));
    }
    public JFrame frame;
    ship currentBlock = new ship(15);
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        paintBlox(currentBlock,g);
    }
    public void moveship(int x, int y){
        currentBlock.move(x,y);
        frame.repaint();
    }

    protected void paintBlox(ship b, Graphics g){
        int size = b.getSize();
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

    }

}
