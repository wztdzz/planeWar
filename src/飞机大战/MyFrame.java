package 飞机大战;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

public class MyFrame extends Frame {
    Image bg = Util.getImage("images/background.jfif");
    Image plane = Util.getImage("images/plane1.png");
    Plane p = new Plane(plane,500,250,50 ,50,50);
    Shell [] shells = new Shell[30];
    Boom boom ; //声明爆炸
    Date startTime =new Date();
    Date endTime;
    int start = (int) startTime.getTime();
    int period;
    int end;
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.launchFrame();
    }

    public void launchFrame(){      //初始化窗口
        this.setTitle("飞机大战");
        this.setVisible(true);//使窗口可见
        this.setSize(2000,1000);
        this.setLocation(0,0);
        this.addWindowListener(new WindowAdapter() {    //增加关闭窗口动作
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        new PaintThread().start();  //启动窗口重画线程
        this.addKeyListener(new KeyMoniter());//启动键盘监听
        for (int i=0;i<30;i++)   //初始化炮弹
        {
           shells[i]=new Shell();
        }
    }

    @Override
    public void paint(Graphics g) {         //g即画笔 在窗口内画出东西
        g.drawImage(bg,0,0,2000,1000,null);  //设置背景
        p.drawMySelf(g);        //画出飞机
        for (int i=0;i<30;i++) {
            shells[i].drawMySelf(g);    //调用shell类的方法画出炮弹
            boolean peng =shells[i].getRec().intersects(p.getRec());    //识别两矩形是否交叉
            if (peng){
                p.live = false;     //将飞机定为死亡
                endTime = new Date();   //记录飞机死亡时间
                end = (int) endTime.getTime();
                period = (end-start)/1000;      //计算游戏时间
                boom=new Boom(p.x,p.y);      //创建爆炸对象
                boom.draw(g);       //实现爆炸轮播图效果

            }
        }
        //打印 爆炸之后的文字
        if(!p.live){
            printInfor(g,"YOU LOSE!!",50,900,500,Color.WHITE);
            printInfor(g,"坚持了"+period+"秒",50,900,550,Color.WHITE);
            p=null;
            System.gc();
        }


    }

    public void printInfor(Graphics g,String str,int size,int x,int y,Color c){

        Font oldFont =g.getFont();
        Color oldColor = g.getColor();
        Font font = new Font("宋体",Font.BOLD,size);

        g.setFont(font);
        g.setColor(c);
        g.drawString(str,x,y);

        g.setColor(oldColor);
        g.setFont(oldFont);

    }



    //键盘监听内部类
class KeyMoniter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            p.addDriction(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            p.minDriction(e);
        }
    }


    //重画线程
    class PaintThread  extends Thread{
        @Override
        public void run() {
            while(true){
                repaint();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private Image offScreenimage = null;
    public void update(Graphics g){  //双缓冲技术防止画面闪烁
        if(offScreenimage == null)
            offScreenimage = this.createImage(2000,1000);

        Graphics gOff = offScreenimage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenimage,0,0,null);
    }


}
