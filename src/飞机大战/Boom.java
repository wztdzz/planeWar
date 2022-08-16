package 飞机大战;

import java.awt.*;

public class Boom {

    public Boom(double x, double y) {
        this.x = x;
        this.y = y;
    }

    double x,y;
    static Image[] img = new Image[30];
    static {
        for (int i = 0; i < 30; i++) {
            img[i]= Util.getImage("images/boom/"+(i+1)+".gif");
            //防止懒加载
           img[i].getHeight(null);
        }
    }

    public void draw(Graphics g){
        int count =1;
        boolean a =true;


        if (!a){
         return;
        }

        if (count <=30 ) {
            g.drawImage(img[count], (int) x, (int) y,null);
        }else {
                a=false;
        }

    }

}
