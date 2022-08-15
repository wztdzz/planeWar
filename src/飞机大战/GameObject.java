package 飞机大战;


import java.awt.*;

//游戏物体的根类
public class GameObject {

    Image img; //对应图片
    int x,y;//坐标
    int speed;//速度
    int width,hight;//长度宽度

    public void drawMySelf(Graphics g){
        g.drawImage(img,x,y,width,hight,null);
    }

    //返回对应的矩形
    public Rectangle getRec(){
        return new Rectangle(x,y,width,hight);
    }

    public GameObject(){}


    public GameObject(Image img, int x, int y, int speed, int width, int hight) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.hight = hight;
    }

    public GameObject(Image img) {
        this.img = img;
        if (img!=null){
            this.width= img.getWidth(null);
            this.hight= img.getHeight(null);
        }

    }
    public GameObject(Image img, int x, int y, int speed) {
        this(img);
        this.x = x;
        this.y = y;
        this.speed = speed;
    }
}


