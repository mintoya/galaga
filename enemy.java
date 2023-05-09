import java.util.ArrayList;

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
        placeInLine = p;
    }
}
class line{
    ArrayList<enemy> subjcts = new ArrayList<>();
    private ArrayList<position> places;
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
    }
    public line(field f,ArrayList<position> places,position p){

        this.places = places;

        subjcts.add(new enemy(15,f));
        subjcts.add(new enemy(15,f));
        subjcts.add(new enemy(15,f));
        subjcts.add(new enemy(15,f));
        places.add(new position(0 ,50));
        places.add(new position(50,100));
        places.add(new position(100,50));
        places.add(new position(50,0));
        for (enemy e:subjcts) {
            e.position = new position(p);
        }
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
}
