//这个类负责用户的登陆与注册的相关代码，并储存每个玩家的得分、成就信息
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private int score;
    //private Map<String, Integer> achievements; // 存储每个成就的进度
    private static final String USER_FILE = "users.txt"; // 存储用户信息的文件路径
    //private static final String ACHIEVEMENT_FILE = "achievements.txt"; // 存储成就信息的文件路径

    public User(String username) {
        this.username = username;
        this.score = 0; // 初始化得分
        //this.achievements = new HashMap<>(); // 初始化成就
        //loadAchievements(); // 加载成就信息
    }
    
    // 用户注册
    public static void register_user(String username,String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) { // 追加模式写入用户信息到文件中
            writer.write(username + ":" + password); // 用户名和密码以逗号分隔
            writer.newLine(); // 换行
        } catch (IOException e) { // 捕获异常
            e.printStackTrace(); // 输出错误信息
        }
    }
    
    // 用户登录
    public static Boolean login_user(String username, String password) { // 静态方法，返回User对象
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) { // 读取用户信息文件
            String line; // 存储每一行的用户信息
            while ((line = reader.readLine()) != null) { // 逐行读取
                String[] parts = line.split(":"); // 以逗号分隔用户名和密码
                if (parts[0].equals(username) && parts[1].equals(password)) { // 检查用户名和密码是否匹配
                    return true; // 返回User对象
                }
            }
        } catch (IOException e) { // 捕获异常
            e.printStackTrace();// 输出错误信息
        }
        return false; // 登录失败返回false
    }
    //获取玩家名称
    public String getUsername() { // 方法返回玩家的用户名
        return username; // 返回用户名
    }

    //获取玩家的分数
    public int getScore() { // 方法返回玩家的分数
        return score; // 返回分数
    }

    //更新玩家的分数
    public void updateScore(int newScore) { // 方法接受一个新的分数作为参数
        this.score = newScore; // 更新分数
    }
}
