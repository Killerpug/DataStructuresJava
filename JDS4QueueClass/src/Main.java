public class Main {
    public static void main(String []args){
        int num = 97;
        String str ="";
        for(int i=num; i<=122; i++) {
            str += Character.toString((char) i);

        }
        ALQueue<String> queue1 = new ALQueue<>();
        for(int j=0 ; j<5;j++){
            for(int i=0; i<=25; i++) {
                queue1.enqueue(Character.toString(str.charAt(i)));
            }
            for(int i=0; i<=25; i++) {
                System.out.print(queue1.dequeue());
            }
        }





    }
}
