import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class field extends JPanel {
    private final boolean isMouseControllled;

    public field(JFrame frame,boolean isMouseControllled){
        this.isMouseControllled = isMouseControllled;
        this.frame = frame;
        frame.add(this);
        setBounds(frame.getBounds());
        frame.addKeyListener(new keyReader(this));
        addEnemys();
    }
    public JFrame frame;

    ArrayList<position> places = new ArrayList<>();
    public line path = new line(this,places,new position(-5,-5),20);
    public void addEnemys(){
        enemies.addAll(path.subjcts);
    }


    ArrayList<bullet> shots = new ArrayList<>();
    ArrayList<enemy> enemies = new ArrayList<>();
    public ship player = new ship(15,this);
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        paintBlox(player,g);
        paintEnemys(g);
        paintShots(g);
        damageEnemies();
    }
    public void addShot(bullet b){
        shots.add(b);
    }
    public void moveShip(int x, int y){
        player.move(x,y);
        frame.repaint();
    }
    public void next(int f){
        if(isMouseControllled){
            position mp = mousepos();
            position fp = new position(frame.getLocationOnScreen().x,frame.getLocationOnScreen().y);
            mp = new position(mp.getX()-fp.getX(),mp.getY()-fp.getY());
            position adj = mp.divideBy(5);
            player.nextStep(adj);}

        ArrayList<bullet> temp = new ArrayList<>(shots);
        for(bullet b:temp){
            if (b!=null) {
                if (b.getDir().x==0) {
                    if(isDiv(f,b.getDir().y)){b.move(1);}
                }
                else if(b.getDir().y==0){
                    if(isDiv(f,b.getDir().x)){b.move(0);}
                }
                else {
                    if (isDiv(f,b.getDir().x)) {
                        b.move(0);
                    }
                    if (isDiv(f,b.getDir().y)) {
                        b.move(1);
                    }
                    //System.out.println(isDiv(f,b.getDir().y));
                }
            }
        }
        shots = temp;


        clean();
        frame.repaint();
    }
    public boolean isDiv(int a,int b){
        if(b==0){return true;}
        return a%Math.abs(b)==0;

    }


    public void damageEnemies(){
        int hitbox = 4;
        for (enemy e : enemies) {
            for (int o = 0; o < shots.size(); o += 1) {
                bullet s = shots.get(o);
                if (s!=null&&s.getPosition().realDistanceFrom(e.getCenterpos()) < hitbox) {
                    shots.remove(o);
                    o -= 1;
                    e.dammage(15);
                }
            }
        }
    }
    public position mousepos(){
        return new position (MouseInfo.getPointerInfo().getLocation());}
    public void clean(){
        for (int i = 0; i < shots.size(); i++) {
        if(shots.get(i)!=null&&shots.get(i).getPosition().get()[0]<0){
            shots.remove(i);
            i-=1;
        }
        }
        for (int i = 0; i < enemies.size(); i++) {
            if(enemies.get(i).isDead()){
                enemies.remove(i);
                i-=1;
            }
        }
        if(path.isEmpty()){
            path = new line(this,places,new position(-20,-20),10);
            addEnemys();
        }

    }

    private void paintEnemys(Graphics g){
        for (enemy e:enemies) {
            paintBlox(e,g);
        }
    }

    protected void paintShots(Graphics g){
        for (bullet b:shots) {
            paintBlox(b,g);
        }
    }

    protected void paintBlox(ship b, Graphics g){

        if (b!=null) {
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
}
