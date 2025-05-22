//定义游戏模式的抽象类，子类可以根据具体的游戏模式实现不同的游戏规则
public abstract class GameMood {
    public abstract void play();

}

//定义经典模式的子类，实现简单模式的游戏规则
class ClassicalMood extends GameMood {
    //实现简单模式的游戏规则
    public void play() {
        //实现简单模式的游戏规则
    }
}

//定义计时模式的子类，实现计时模式的游戏规则
class TimedMood extends GameMood {
    //实现计时模式的游戏规则
    public void play() {
        //实现计时模式的游戏规则
    }
}

//定义积分模式的子类，实现积分模式的游戏规则
class PointMood extends GameMood {
    //实现积分模式的游戏规则
    public void play() {
        //实现积分模式的游戏规则
    }
}

