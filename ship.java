import java.awt.*;



public class ship {
    public position position = new position(0,0);
    private pixel[][] immage;
    private final pixel b = new pixel(new Color(0x00ABF5));
    private final pixel r = new pixel(new Color(0xFF0000));
    private final pixel g = new pixel(new Color(0x4BFF00));
    private final pixel c = new pixel();
    private final int size;
    pixel[][] blueShip = {
            {c,b,c},
            {c,b,c},
            {b,g,b},
            {r,b,r},
    };
    public ship(int topLength){
        immage = blueShip;
        size = topLength/immage[0].length;

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
}
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
    public void change(int x, int y) {this.x+=x;this.y+=y;}
}

