package 飞机大战;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{

    boolean up,down,left,right;
    boolean live = true;


    public void addDriction(KeyEvent e){
          switch (e.getKeyCode()){
              case KeyEvent.VK_UP:
                  up=true;
                  break;
              case KeyEvent.VK_DOWN:
                  down=true;
                  break;
              case KeyEvent.VK_LEFT:
                  left=true;
                  break;
              case KeyEvent.VK_RIGHT:
                  right=true;
                  break;
          }
    }
    public void minDriction(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                up=false;
                break;
            case KeyEvent.VK_DOWN:
                down=false;
                break;
            case KeyEvent.VK_LEFT:
                left=false;
                break;
            case KeyEvent.VK_RIGHT:
                right=false;
                break;
        }
    }


    @Override
    public void drawMySelf(Graphics g) {
        if(!live){return;}
        super.drawMySelf(g);

        if (left){
            x-=speed;
        }
        if (right){
            x+=speed;
        }
        if (up){
            y-=speed;
        }
        if (down){
            y+=speed;
        }
    }


    public Plane(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
    }

    public Plane(Image img, int x, int y, int speed, int width, int hight) {
        super(img, x, y, speed, width, hight);
    }
}
