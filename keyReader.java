import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class keyReader extends KeyAdapter {
    private final field listener;
    private volatile boolean goingx = false;
    private volatile boolean goingy = false;

    public keyReader(field a) {
        listener = a;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        final int up = 38,down = 40, left = 37,right = 39;
        int visible_move = 2;

        if(e.getKeyCode()==up||e.getKeyCode()==down){
            if (goingy) {
                return;
            }
        }
        if(e.getKeyCode()==right||e.getKeyCode()==left){
            if (goingx) {
                return;
            }
        }

        switch (e.getKeyCode()) {
            case(up)->{go(0, -visible_move);}
            case(right)->{go(visible_move, 0);}
            case(left)->{go(-visible_move, 0);}
            case(down)->{go(0, visible_move);}
            case(32)->{listener.addShot(
                    new bullet(listener.player.getPosition(),listener)
            );}//space
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        final int up = 38,down = 40, left = 37,right = 39;
        switch (e.getKeyCode()) {
            case(up), (down) ->{stop(1);}

            case(right), (left) ->{stop(0);}

            case (32)->{} // space
        }
    }

    private void go(int x, int y) {
        Thread thread;
        if(x!=0){
            goingx = true;
            thread = new Thread(() -> {
                while (goingx) {
                    waitFor(50);
                    listener.moveship(x, y);
                }
            });
        }
        else{
            goingy = true;
            thread = new Thread(() -> {
                while (goingy) {
                    waitFor(50);
                    listener.moveship(x, y);
                }
            });
        }

        thread.start();
    }

    public void waitFor(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stop(int i) {
        if (i==0){
            goingx = false;
        }else{
            goingy = false;
        }

    }
}
