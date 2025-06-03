package User;
//这个类负责用户的登陆与注册的相关代码，并储存每个玩家的得分
//user.txt文件中储存玩家的用户名和密码，格式为：用户名:密码:分数
//scores.txt文件中储存玩家的用户名和分数，格式为：用户名:分数
import java.io.*;

public class User {
    private String username;
    private int score;
    private static final String USER_FILE = "users.txt"; // 存储用户信息的文件路径
    private static final String SCORE_FILE = "scores.txt"; // 存储分数信息的文件路径

    public User(String username) {
        this.username = username;
        this.score = loadScore(username); // 加载用户分数
    }

    // 从文件加载用户分数
    private int loadScore(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 3 && parts[0].equals(username)) {
                    return Integer.parseInt(parts[2]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0; // 默认分数为 0
    }

    // 用户注册
    public static void register_user(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(username + ":" + password );
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 用户登录
    public static Boolean login_user(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;// 登录失败
    }

    // 获取玩家名称
    public String getUsername() {
        return username;
    }

    // 获取玩家的分数
    public int getScore() {
        return score;
    }

    // 更新玩家的分数并保存到文件
    public void updateScore(int newScore) {
        this.score = newScore;
        saveScore();
    }

    // 将分数保存到文件
    private void saveScore() {
        StringBuilder tempContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 2 && parts[0].equals(username)) {
                    tempContent.append(parts[0]).append(":").append(parts[1]).append(":").append(score).append("\n");
                } else {
                    tempContent.append(line).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            writer.write(tempContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //初始化用户分数为0
    public static void ResetScore(String username) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE, true))) {
            writer.write(username + ":0"); // 初始分数为 0
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
