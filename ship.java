import java.awt.*;



public class ship {
    public position position;
    private pixel[][] immage;
    private final pixel b = new pixel(new Color(0x00ABF5));
    private final pixel r = new pixel(new Color(0xFF0000));
    private final pixel g = new pixel(new Color(0x4BFF00));
    private final pixel c = new pixel();
    pixel[][] blueShip = {
            {c,b,c},
            {c,b,c},
            {b,g,b},
            {r,b,r},
    };

    public pixel[][] getImmage() {
        return immage;
    }
}
class pixel{
    public boolean iscolored;
    private Color c;
    public pixel(){iscolored = false;}
    public pixel(Color c){iscolored = true;
    this.c = c;}
}
