package 飞机大战;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

//工具类
public class Util {   //私有构造器 防止外部创建对象
    private Util(){}

    public static Image getImage(String path){
        Image img =null;
        URL u = Util.class.getClassLoader().getResource(path);
        try {
            img = ImageIO.read(u);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }
}
