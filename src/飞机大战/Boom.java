package 飞机大战;

import java.awt.*;

public class Boom {

    public Boom(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double x,y;
    static Image[] img = new Image[16];
    static {
        for (int i = 0; i < 16; i++) {
            img[i]= Util.getImage("images/boom/"+(i+1)+".gif");
            //防止懒加载
           img[i].getHeight(null);
        }
    }
    int count =0;
    boolean live=true;
    public void draw(Graphics g){
        if (!live){
            return;
        }
        if (count<16){
            g.drawImage(img[count], (int) x, (int) y,null);
            count++;
        }else {
            live=false;
        }
    }

}
