package 飞机大战;

import java.awt.*;

public class Shell extends GameObject {
    double degree;

    @Override
    public void drawMySelf(Graphics g) {
        Color c= g.getColor();
        g.setColor(Color.yellow);
        g.fillOval(x,y,width,hight);

        //使炮弹沿任意角度飞行
        x+=speed*Math.cos(degree);
        y+=speed*Math.sin(degree);

        //实现边界碰撞回弹
        if (y>=1000 ||y<=0){
            degree=-degree;
        }
        if (x>=2000||x<=0){
            degree=Math.PI+degree;
        }

        g.setColor(c);
    }

    public Shell(){
        degree =Math.random()*Math.PI*2;
        x=1000;
        y=300;

        width = 10;
        hight = 10;
        speed = 35;

    }
}
