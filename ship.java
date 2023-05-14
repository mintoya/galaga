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

    public position(position p) {
        this.x = p.x;
        this.y = p.y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public position(int x, int y){
        this.x = x;this.y = y;
    }
    public position(Point p){
        this.x = p.x;
        this.y = p.y;
    }
    public position(int[] a){
        x = a[0];y=a[1];
    }
    public int[] get(){
        return new int[]{y,x};
    }
    public int[] getRev(){return new int[]{x,y};
    }
    public position divideBy(int d){
        return new position(
                this.x/d,
                this.y/d);
    }
    public void change(double x, double y) {this.x+=x;this.y+=y;}
    public position difference(position p){
        int newx = -(this.x-p.x);
        int newy = -(this.y-p.y);
        return new position(newx,newy);
    }


    public void add(position p) {
        this.y+=p.y;
        this.x+=p.x;
    }
    public position rAdd(position p){
        return new position(this.x+p.x,this.y+p.y);
    }

    public position limit(int i){
        if(y!=0){y = y/Math.abs(y);y*=i;}
        if(x!=0){x = x/Math.abs(x);x*=i;}

        return this;
    }
    public boolean equals(Object p){
        if(!p.getClass().equals(this.getClass())){
            return false;
        }
        position temp =  (position)p;
        return (
                temp.x==x&&temp.y==y
                );
    }
    public double realDistanceFrom(position p){
        return Math.pow((Math.pow((x-p.x),2)+Math.pow((y-p.y),2)),.5);
    }
    public String toString(){
        return ("x: "+x+"y: "+y);
    }
}


public class ship {
    protected position position = new position(250/5,700/5);

    protected pixel[][] immage;
    protected final pixel b = new pixel(new Color(0x00ABF5));
    protected final pixel r = new pixel(new Color(0xFF0000));
    protected final pixel g = new pixel(new Color(0x4BFF00));
    protected final pixel c = new pixel();
    protected int size;
    public pixel[][] blueShip = {
            {c,b,c},
            {c,b,c},
            {b,g,b},
            {r,b,r},
    };
    protected field field;
    public ship(int topLength,field f){
        field = f;
        immage = blueShip;
        size = topLength/immage[0].length;

    }
    public void nextStep(position p){
        position to =position.difference(p);
        position.add(to.limit(1));
    }
    public ship(){}
    public position getPosition(){
        return position;
    }
    public position getCenterpos(){
        return new position(
                position.getX()+immage.length/2,
                position.getY()+immage[0].length/2
        );
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
    public void shootGun(String type){
        bullet[] shotgun = new bullet[1];
        switch (type){
            case("shotgun")->{
                shotgun = new bullet[]{
                        new bullet(position, field,
                                new velocity(0, -2)),


                        new bullet(position, field,
                                new velocity(15, -2)),
                        new bullet(position, field,
                                new velocity(-15, -2)),
                        new bullet(position, field,
                                new velocity(30, -3)),
                        new bullet(position, field,
                                new velocity(-30, -3))
                };}
        }
        for (bullet b:shotgun) {
            field.addShot(b);
        }
    }
}

class bullet extends ship{
    private velocity dir;
    private int health = 100;
    public bullet(position p,field f,velocity dir){
        super();
        this.dir = dir;
        position = new position(p.getRev());
        pixel y = new pixel(new Color(0x650000));
        immage = new pixel[][]{{y}};
        size = f.player.getSize();
    }

    public void move(int a){
        if(a==0){
            position.change(Math.signum(dir.x),0);
        }else{
            position.change(0,Math.signum(dir.y));
        }
    }
    public velocity getDir(){
        return dir;
    }

}
class velocity{
    int x;int y;
    public velocity(int x,int y){
        this.x = x;this.y = y;
    }
}



