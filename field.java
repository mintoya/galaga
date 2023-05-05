import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class field extends JPanel {
    final int pixelSize = 10;
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        p.draw(g,heldBlock,score);
        paintBlox(currentBlock,g,currentBlock.getColor());
        drawGrid(g);
        paintCast(g);
    }
    protected void paintBlox(ship b, Graphics g, Color c){
        int size = pixelSize;
        for (int i = 0; i < b.getImmage().length; i++) {
            for (int j = 0; j < b.getImmage()[i].length; j++) {
                int[] pos = b.position.get();
                if(b.getBlock()[j][i]){
                    g.setColor(c);
                    g.fillRect(size*(i)+((pos[0]*size)),
                            size*(j-extraLines)+((pos[1]*size)),
                            size,size);

                }
            }

        }
    }
}