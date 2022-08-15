package 飞机大战;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class MyFrame extends Frame {
    public void launchFrame(){      //初始化窗口
        this.setTitle("飞机大战");
        this.setVisible(true);//使其可见
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
    }
    Image bg = Util.getImage("images/background.jfif");
    Image plane = Util.getImage("images/plane1.png");
    Plane p = new Plane(plane,100,100,100 ,50,50);
    @Override
    public void paint(Graphics g) {
    //g 就是一只画笔
    g.drawImage(bg,0,0,2000,1000,null);
    p.drawMySelf(g);
    }
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.launchFrame();
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
    public void update(Graphics g){
        if(offScreenimage == null)
            offScreenimage = this.createImage(2000,1000);

        Graphics gOff = offScreenimage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenimage,0,0,null);
    }


}
