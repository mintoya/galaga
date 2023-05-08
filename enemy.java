import java.util.ArrayList;

public class enemy extends ship{
    public pixel[][] blueShip = {
            {r,r,r,r},
            {c,r,r,c},
            {r,c,c,r},
            {r,c,c,r},
    };
    public int velocity = 1;
    public int placeInLine;
    public void setPlaceInLine(int a){
        placeInLine = a;
    }

    public enemy(int topLength,field f){
        super(topLength,f);
    }
    public enemy(int topLength,field f,int p){
        super(topLength,f);
        placeInLine = p;
    }
}
class line{
    ArrayList<enemy> subjcts;
    private ArrayList<position> places;
    private field field;
    public line(field f,ArrayList<position> places){
        this.places = places;
        field = f;
        subjcts.add(new enemy(15,f));
        subjcts.add(new enemy(15,f));
        subjcts.add(new enemy(15,f));
        subjcts.add(new enemy(15,f));
    }
    public void nextStep(){
        for (enemy a:subjcts){
            position to = a.position.difference(places.get(0));
            a.position.add(to.limit(1));
        }
    }
}
