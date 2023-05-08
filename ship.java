import java.awt.*;




class pixel{
    public boolean iscolored;
    private Color c;
    public Color getColor(){
        return c;
    }
    public pixel(){iscolored = false;}
    public pixel(Color c){iscolored = true;
        this.c = c;}
}
class position {
    private int x,y;
    public position(int x,int y){
        this.x = x;this.y = y;
    }
    public position(int[] a){
        x = a[0];y=a[1];
    }
    public int[] get(){
        return new int[]{y,x};
    }
    public int[] getRev(){return new int[]{x,y};
    }
    public void change(double x, double y) {this.x+=x;this.y+=y;}
}


public class ship {
    protected position position = new position(0,0);
    protected pixel[][] immage;
    private final pixel b = new pixel(new Color(0x00ABF5));
    private final pixel r = new pixel(new Color(0xFF0000));
    private final pixel g = new pixel(new Color(0x4BFF00));
    private final pixel c = new pixel();
    protected int size;
    pixel[][] blueShip = {
            {c,b,c},
            {c,b,c},
            {b,g,b},
            {r,b,r},
    };
    private field field;
    public ship(int topLength,field f){
        field = f;
        immage = blueShip;
        size = topLength/immage[0].length;

    }
    public ship(){}
    public position getPosition(){
        return position;
    }

    public pixel[][] getImmage() {
        return immage;
    }
    public void move(int x, int y){
        position.change(x,y);
    }

    public int getSize() {
        return size;
    }
    public void shoot(){
        bullet shot= new bullet(position,field);
        shot.setDir(0,-2);
        bullet shot1= new bullet(position,field);
        shot1.setDir(1,-2);
        bullet shot2= new bullet(position,field);
        shot2.setDir(-1,-2);

        field.addShot(shot);
        field.addShot(shot1);
        field.addShot(shot2);

    }
}

class bullet extends ship{
    private velocity dir;
    private int dammege;
    public bullet(position p,field f){
        super();
        position = new position(p.getRev());
        pixel y = new pixel(new Color(0xD97F0A));
        immage = new pixel[][]{{y}};
        size = f.player.getSize();
    }
    public void move(){
        position.change(dir.x,dir.y);
    }
    public void setDir(double x, double y){
        dir = new velocity(x,y);
    }
    public velocity getDir(){
        return dir;
    }
    class velocity{
        double x;double y;
        public velocity(double x,double y){
            this.x = x;this.y = y;
        }
    }
}



