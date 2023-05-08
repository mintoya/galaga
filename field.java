import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class field extends JPanel {
    public field(JFrame frame){
        this.frame = frame;
        frame.addKeyListener(new keyReader(this));
    }
    public JFrame frame;

    ArrayList<bullet> shots = new ArrayList<>();
    public ship player = new ship(15,this);
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        clean(0);
        paintBlox(player,g);
        paintShots(g);
    }
    public void addShot(bullet b){
        shots.add(b);
    }
    public void moveship(int x, int y){
        player.move(x,y);
        frame.repaint();
    }
    public void next(){
        for(bullet b:shots){
            b.move();
        }
        frame.repaint();
    }
    public void clean(int index){
        if(index>shots.size()-1){
            return;
        }
        if(shots.get(index).getPosition().get()[0]<0){
            shots.remove(index);
        }
        if(index<shots.size()-1){
            clean(index+1);
        }

    }

    protected void paintShots(Graphics g){
        for (bullet b:shots) {
            paintBlox(b,g);
        }
    }
    protected void paintBlox(ship b, Graphics g){
        int size = b.getSize();
        for (int i = 0; i < b.getImmage().length; i++) {
            for (int j = 0; j < b.getImmage()[i].length; j++) {
                int[] pos = b.getPosition().get();
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
