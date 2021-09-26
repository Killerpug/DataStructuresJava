import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        CommandLine  receiveCommands = new CommandLine();
        try {
            FileReader reader = new FileReader("MyFile.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();

            while ((line = bufferedReader.readLine()) != null) {
                String[] lineSplit = line.split("^\\s+");
                receiveCommands.getMessage("create book " + lineSplit[0]);       //book name
                for (int i = 0; i < lineSplit.length; i++){

                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean exit = false;
        System.out.println("Welcome to your library of contacts: open/create/delete contact books");
        Scanner scanner = new Scanner(System.in);
        while(!exit){
            System.out.print("home> ");
             exit = receiveCommands.getMessage(scanner.nextLine());
        }

        System.out.println("the end");
    }
}
