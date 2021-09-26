public class Challenge {
    private int numberOfContestants;
    private int removeMth;
    private int counter = 1;
    private Node<Integer> front = null;
    private Node<Integer> current = null;
    private Node<Integer> previous = null;      //Scan list

    public String josephusProblem(int numberOfContestants, int removeMth) {
        if (numberOfContestants <= 0 || removeMth <= 0) return  "perrada";      //Arithmetic exception, values <1 not valid
        this.numberOfContestants = numberOfContestants;
        this.removeMth = removeMth;
        playerList();                            //Make the node list
        previous = current;                      //Initialize LinkedList scanner
        current = front;
        while (this.numberOfContestants != 1) {
            System.out.print(remove());          //Remove nodes in Mth position
        }
        return "the winner is: " + front.nodeValue;// the winner is the last one standing
    }

    private void playerList() {
        for(int i = 0; i < numberOfContestants; i++) {
            Node<Integer> player = new Node<>(i + 1);
            if(current == null){       //first player
                front = player;
                current = player;
                continue;           //Continue ends up the current cycle and skips the remaining code until next cycle
            }
            previous = current;        //update of list
            current = player;
            previous.nextNode = current;    //Linking previous with current node
        }
        current.nextNode = front;       // Link last value with first one, Circular list
    }

    private String remove() {
        String loser = "";
        if ((counter % removeMth) == 0) {                        //node is in mthPosition, erase it
            loser = current.nodeValue.toString() + ", ";        // add the node to losers list
            numberOfContestants--;
            previous.nextNode = current.nextNode;       //Kill Link of current node because it was a divisor of removeMth
        } else {     //node is NOT in mth position
            previous = current;
        }
        current = current.nextNode;     //update current node
        counter++;
        front = front.nextNode;         //update front (needed when printing winner, it stays with a delayed value)
        return loser;
    }


}
