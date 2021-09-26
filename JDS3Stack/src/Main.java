
public class Main {
    public static void main(String []args){
        int number1 = 1;
        int number2 = 2;
        int number3 = 3;
        LLStack<Integer> stack1 = new LLStack<>();
        stack1.push(number1);
        stack1.push(number2);
        stack1.push(number3);
        System.out.println(stack1.pop());
        stack1.pop();
        stack1.pop();
        stack1.pop();

    }
}
