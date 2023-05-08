import java.util.ArrayList;

public class enemy extends ship{
    public pixel[][] blueShip = {
            {r,r,r,r},
            {c,r,r,c},
            {r,c,c,r},
            {r,c,c,r},
    };
    public int velocity = 3;
    public int realVelocity = velocity;
    public int placeInLine=0;
    public void setPlaceInLine(int a){
        placeInLine = a;
    }

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
    private field field;
    public line(field f,ArrayList<position> places){
        this.places = places;
        field = f;

        subjcts.add(new enemy(15,f));
        subjcts.add(new enemy(15,f));
        subjcts.add(new enemy(15,f));
        subjcts.add(new enemy(15,f));
        places.add(new position(50 ,100));
        places.add(new position(0,100));
        places.add(new position(0,0));
    }
    public void nextStep(){
        for (enemy a:subjcts){
            position to = a.position.difference(places.get(a.placeInLine));
            a.position.add(to.limit(a.velocity));
            if(a.position.equals(places.get(a.placeInLine))&&a.placeInLine<places.size()-1){
                a.placeInLine+=1;
                a.velocity = a.realVelocity;
            }
            if(a.position.realDistanceFrom(places.get(a.placeInLine))<=Math.pow(a.velocity,2)){
                a.velocity=1;
            }
            System.out.println(a.position);
        }
    }
}
