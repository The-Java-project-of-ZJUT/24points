package User;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GraphicUser {
    public static void main(String[] args) {
        JFrame frame = new JFrame("用户登录与注册"); // 创建一个窗口对象，设置标题为“用户登录界面”
        frame.setSize(500, 600); // 设置窗口大小为 300x200 像素
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口关闭时退出程序 

        frame.setLocationRelativeTo(null);// 将窗口居中显示

        // 背景照片文件路径
        String imagePath = "D:\\大学\\Java\\ProjectOne\\24points\\User\\BackPictue2.jpg"; 
        File imageFile = new File(imagePath);

        try {
            // 读取图片文件
            BufferedImage backgroundImage = ImageIO.read(imageFile);
            // 创建自定义 JPanel 用于显示背景图片
            JPanel backgroundPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // 绘制背景图片
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            };
            // 设置布局管理器
            backgroundPanel.setLayout(new BorderLayout());
            frame.setContentPane(backgroundPanel);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "无法加载背景图片", "错误", JOptionPane.ERROR_MESSAGE);
        }

        // 创建面板和标签

        frame.setVisible(true); // 设置窗口可见


    }
}
