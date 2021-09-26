public class Main {
    public static void main(String []args){

        int number = 5;
        int littleNumber = 1;
        int bigNumber = 10;
        int notWantedNumber = 500;

        SetBag<Integer> bagA = new SetBag<>(5);
        SetBag<Integer> bagB = new SetBag<>(5);

        bagA.add(number);
        bagA.add(number);
        bagA.add(bigNumber);
        bagB.add(bigNumber);
        bagB.add(littleNumber);
        bagB.add(notWantedNumber);

        System.out.println(bagA);
        System.out.println( bagA.union(bagB));
        System.out.println( bagA.intersection(bagB));
        System.out.println( bagA.difference(bagB)); //rip

    }

}
