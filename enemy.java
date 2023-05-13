import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class enemy extends ship{
    public pixel[][] blueShip = {
            {r,r,r,r},
            {c,r,r,c},
            {r,c,c,r},
            {r,c,c,r},
    };
    private int health = 100;

    public void dammage(int d){
        health-=d;
        velocity = 2;
    }
    public boolean isDead(){
        return health<1;
    }
    public int velocity = 1;
    public int realVelocity = velocity;
    public int placeInLine=0;

    public enemy(int topLength,field f){
        super(topLength,f);
        immage = blueShip;
    }
    public enemy(int topLength,field f,int p){
        super(topLength,f);
        immage = blueShip;
        placeInLine = p;
    }
}
class line{
    ArrayList<enemy> subjcts = new ArrayList<>();
    private ArrayList<position> places;
    //region Description
    private position[] sSquare = {
            new position(-20,10),
            new position(100,10),
            new position(100,30),
            new position(-20,30),
            new position(-20,50),
            new position(100,50),
            new position(100,70),
            new position(-20,70),
            new position(-20,90),
            new position(100,90),
            
    };
    //endregion
    private int step =0;
    public boolean isEmpty(){
        for (enemy e:subjcts) {
            if(!e.isDead()){
                return false;
            }
        }
        return true;
    }

    public line(field f,ArrayList<position> places){

        this.places = places;

        subjcts.add(new enemy(15,f));
        subjcts.add(new enemy(15,f));
        subjcts.add(new enemy(15,f));
        subjcts.add(new enemy(15,f));
        places.add(new position(0 ,50));
        places.add(new position(50,100));
        places.add(new position(100,50));
        places.add(new position(50,0));
        this.places = a_to_A(sSquare);
    }
    public line(field f,ArrayList<position> places,position p,int number){

        this.places = places;
        for (int i = 0; i < number; i++) {
            subjcts.add(new enemy(15,f));
        }
        for (enemy e:subjcts) {
            e.position = new position(p);
        }
        this.places = a_to_A(sSquare);
    }
    public void nextStep(int togo){
        for (int i = 0; i < step / 30 && i < subjcts.size(); i += 1) {
            enemy a = subjcts.get(i);
            if(togo%a.velocity==0)
            {
                position to = a.position.difference(places.get(a.placeInLine));
                a.position.add(to.limit(1));

                if (a.position.equals(places.get(a.placeInLine)) && a.placeInLine < places.size() - 1) {
                    a.placeInLine += 1;
                }
                if (a.placeInLine == places.size() - 1 && a.position.equals(places.get(places.size() - 1))) {
                    a.placeInLine = 0;
                    a.velocity = a.realVelocity;
                }
            }

        }
        step += 1;

    }
    public void area(field f, int h, int w){
        subjcts.clear();
        int sa = 15,sb = 20;
        position[] p = new position[h*w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                p[(i*h)+j] = new position(
                        i*sb,j*sa
                );
                System.out.println(p[(i*h)+j]);
                subjcts.add(new enemy(15,f,i*h+j));
            }
        }
        places = a_to_A(p);
    }
    public void nextPosition(int togo){
        for (int i = 0; i < step / 30 && i < subjcts.size(); i += 1) {
            enemy a = subjcts.get(i);
            if(togo%a.velocity==0)
            {
                position to = a.position.difference(places.get(a.placeInLine));
                a.position.add(to.limit(1));
            }

        }
        step += 1;
    }

    protected ArrayList<position> a_to_A(position[] b){
        return new ArrayList<>(List.of(b));
    }
}