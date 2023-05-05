public class position {
    private int x,y;
    public position(int x,int y){
        this.x = x;this.y = y;
    }
    public position(int[] a){
        x = a[0];y=a[1];
    }
    public int[] get(){
        return new int[]{x,y};
    }
    public void change(int[] ammount) {
        if (ammount.length != 2) {return;}
        x+=ammount[0];y+=ammount[1];
    }
}
