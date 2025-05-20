import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

//此方法实现随机二十四点数的生成
public class GameLogic {
    Random rand = new Random();

    double[] cards =  new double[4];
    String solution;

    //随机生成四个数并判断是否可以构成24点，若无二十四点则持续循环
    private void dealSolution(){
        do{
            for(int i=0; i<4; i++){
                cards[i] = (double)rand.nextInt(13) + 1;
            }
            solution = solve24Points(cards);
        }while(solution == null);
    }

    //该方法的核心方法
    public static String solve24Points(double[] nums){
        /*
        *构造一个嵌套的ArrayList数组，负责存放所有的情况
        *构造了若干方法负责找到所有的情况
        *方法的具体功能可以参考各方法的注释
        * 对于4种数字的排列共有4! = 24种方法
        */
        ArrayList<ArrayList<Double>> permutation = genPermutations(nums);
        char[] operations = {'+', '-', '*', '/'};
        /*
        * 实现循环查找是否满足二十四点
        * 有数学关系可知对于任意的4个数做运算有五种括号方式，即：
        * 1.((a op b) op c) op d
        * 2.(a op (b op c)) op d
        * 3.(a op b) op (c op d)
        * 4.a op ((b op c) op d)
        * 5.a op (b op (c op d))
        * 若有则输出计算方式
        * 若无则放回null继续下一轮查找
        */
        for(ArrayList<Double> list : permutation){
            for(char op1 : operations){
                for(char op2 : operations){
                    for(char op3 : operations){
                        double result = operate(operate(operate(list.get(0), list.get(1), op1), list.get(2), op2), list.get(3), op3);
                        if(Math.abs(result - 24.0) < 0.0001){
                            return "((" + list.get(0).intValue() + " " + op1 + " " + list.get(1).intValue() + ") " + op2 + " " + list.get(2).intValue() + ") " + op3 + " " + list.get(3).intValue();
                        }

                        result = operate(operate(list.get(0), list.get(1), op1), operate(list.get(2), list.get(3), op2), op3);
                        if(Math.abs(result - 24.0) < 0.0001){
                            return "((" + list.get(0).intValue() + " " + op1 + " " + list.get(1).intValue() + ") " + op2 + " " + list.get(2).intValue() + ") " + op3 + " " + list.get(3).intValue();
                        }

                        result = operate(operate(list.get(0), operate(list.get(1), list.get(2), op1), op2), list.get(3), op3);
                        if(Math.abs(result - 24.0) < 0.0001){
                            return "((" + list.get(0).intValue() + " " + op1 + " " + list.get(1).intValue() + ") " + op2 + " " + list.get(2).intValue() + ") " + op3 + " " + list.get(3).intValue();
                        }

                        result = operate(list.get(0), operate(operate(list.get(1), list.get(2), op1), list.get(3), op2), op3);
                        if(Math.abs(result - 24.0) < 0.0001){
                            return "((" + list.get(0).intValue() + " " + op1 + " " + list.get(1).intValue() + ") " + op2 + " " + list.get(2).intValue() + ") " + op3 + " " + list.get(3).intValue();
                        }

                        result = operate(list.get(0), operate(list.get(1), operate(list.get(2), list.get(3), op1), op2), op3);
                        if(Math.abs(result - 24.0) < 0.0001){
                            return "((" + list.get(0).intValue() + " " + op1 + " " + list.get(1).intValue() + ") " + op2 + " " + list.get(2).intValue() + ") " + op3 + " " + list.get(3).intValue();
                        }
                    }
                }
            }
        }
        return null;
    }

    //实现基础的运算，即数字相加减乘除
    private static double operate(double a, double b, char op){
        double result;
        switch (op) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            default:
                result = 0;
        }
        return result;
    }

    /*
    * 以下三个方法实现了递归查找所有24种情况
    * （但是说句实话主播也没怎么搞明白，反正就是实现了，具体可以自己看课程，我学的稀里糊涂的）
    * 方法一：
    * 创建一个嵌套的数组和一个数组
    * 将前面生成好的随机数字加入数组中
    * 开始进行递归查找（permute方法）
    * 最后返回嵌套数组的结果，一共24个
    */
    private static ArrayList<ArrayList<Double>> genPermutations(double[] arr){
        ArrayList<ArrayList<Double>> result = new ArrayList<>();
        ArrayList<Double> nums = new ArrayList<>();

        for(double num : arr){
            nums.add(num);
        }
        permute(nums, 0, result);
        return result;
    }
    /*
    * 方法二：
    * 递归的核心
    */
    private static void permute(ArrayList<Double> nums, int start, ArrayList<ArrayList<Double>> result) {
        if (start == nums.size() - 1) {
            result.add(new ArrayList<>(nums));
            return;
        }

        for (int i = start; i < nums.size(); i++) {
            //交换start和i
            swap(nums, start, i);
            //进行递归
            permute(nums, start + 1, result);
            //回溯（回复交换），保持数组的初始状态
            swap(nums, start, i);
        }
    }

    /*
    * 实现数字的相互交换
    */
    private static void swap(ArrayList<Double> nums, int i, int j) {
        double temp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, temp);
    }

    //以下三个方法时主播为了测试代码可行性而写的，在整个项目中大概没有实际意义，写GUI的友友可以删除
    public GameLogic(){
        dealSolution();
    }

    public static void main(String[] args) {
        System.out.println(new GameLogic());
    }

    @Override
    public String toString() {
        return "GameLogic{" +
                "rand=" + rand +
                ", cards=" + Arrays.toString(cards) +
                ", solution='" + solution + '\'' +
                '}';
    }
}
