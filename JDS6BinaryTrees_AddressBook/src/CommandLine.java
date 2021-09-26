import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CommandLine {
    public CBooks<Integer,ContactBook> library = new CBooks<>();

    public Boolean getMessage(String commandQuery){
        String[] splitCommand = Util.approveCommand(commandQuery);
        if(splitCommand == null)                             //command is not approved try new
            System.out.println("Unsupported command: too many arguments, type one command at a time");
        else {                                               //command is approved interpret it

            if ((splitCommand[0].equals("exit")) ) {
                saveContacts();
                System.out.println("Contacts Saved");       //Save Contacts In notepad
                return true;                                //EXIT PROGRAM
            }
            else if ((splitCommand.length < 3)) {
                System.out.println("too few arguments");
            } else {
                interpretCommandBook(splitCommand);
            }
        }
        return false;
    }

    private void saveContacts() {
        try {
            FileWriter writer = new FileWriter("MyFile.txt");

                Node<Integer,ContactBook>[] iterateBooks = library.viewMap();
                for(int i = 0; i < library.tableSize; i++){
                    if(iterateBooks[i] != null){
                        writer.write(System.getProperty( "line.separator" ));
                        writer.write( iterateBooks[i].nodeValue.name + "^");
                        Tree<Contact> iterateContacts = iterateBooks[i].nodeValue.viewContactTree();
                        iterateBooks[i].nodeValue.saveInorder(iterateContacts.rootNode, writer);
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("nothing in here");
        }
    }

    private void interpretCommandBook(String[] args){       //Book operations
        String operation = args[0] + args[1];
        String subOp = args[2];
        switch (operation) {
            case "openbook":
                ContactBook currentBook = openBook(subOp);
                if(currentBook != null) openBookMenu(currentBook);
                break;
            case "deletebook":
                deleteBook(subOp);
                break;
            case "createbook":
                createBook(subOp);
                break;
            default:
                System.out.println("Command not supported");
        }
    }

    private void interpretCommandContact(String[] args, ContactBook currentBook){        //Contact operations
        String operation = "";
        TreeNode<Contact> contactKey = new TreeNode<>(0);
        if (args.length > 1) {
            operation = args[0] + args[1];
            if (args.length > 2) {
                String subOp = args[2];
                contactKey = new TreeNode<>(Util.radixHashing(subOp));  //assign radix key
            }
        }
        switch (operation) {

            case "addcontact":
                addContact(currentBook);
                break;
            case "infocontact":
                infoContact(contactKey, currentBook);
                break;
            case "deletecontact":
                deleteContact(contactKey, currentBook);
                break;
            case "searchcontact":
                searchContact(contactKey, currentBook);
                break;
            case "editcontact":
                editContact(contactKey, currentBook);
                break;
            default:
                System.out.println("Command not supported");
        }
    }

    //CONTACT OPERATIONS
    private void addContact(ContactBook currentBook) {
        currentBook.addContact();
    }

    private void infoContact(TreeNode<Contact> key, ContactBook currentBook) {

        currentBook.displayContactInfo(currentBook.searchContact(key));
    }

    private void deleteContact(TreeNode<Contact> key, ContactBook currentBook) {
        TreeNode<Contact> deleteContact = currentBook.searchContact(key) ;
        if (deleteContact != null)  {
            System.out.println("Contact " + deleteContact.nodeValue.getFirstName() + " deleted");
            currentBook.removeContact(deleteContact);
        }
    }
    private void searchContact(TreeNode<Contact> key, ContactBook currentBook) {
        TreeNode<Contact> target = currentBook.searchContact(key);
        System.out.println("contact matches: ");
        if(target != null ) System.out.println(target.nodeValue.getLastName());
        while ((target.rightChild != null) && target.nodeKey == target.rightChild.nodeKey){
             System.out.println(target.rightChild.nodeValue.getLastName() );
            target = target.rightChild;
        }

    }
    private void editContact(TreeNode<Contact> key, ContactBook currentBook) {
        TreeNode<Contact> editContact = currentBook.searchContact(key) ;
        if(editContact != null) {
            System.out.println("Editing " + editContact.nodeValue.getFirstName() + " contact");
            currentBook.editContact(editContact);
        }
    }


    private void openBookMenu(ContactBook currentBook) {
        Scanner scanner = new Scanner(System.in);
        boolean exitBook = false;
        while(!exitBook) {
            System.out.print("home/" + currentBook.name + "> ");
            String[] splitContactCommand = Util.approveCommand(scanner.nextLine());
            if (splitContactCommand == null)
                System.out.println("Unsupported command: too many arguments, type one command at a time");
            else {                                                                                            //command is approved interpret it
                if ((splitContactCommand[0].equals("exit"))) {     //EXIT BOOK
                    System.out.println("exiting " + currentBook.name + " book");
                    exitBook = true;
                    continue;
                }
                interpretCommandContact(splitContactCommand, currentBook);                                   //perform operation with contact
            }
        }
    }

    //BOOK OPERATIONS
    private ContactBook openBook(String subOp) {
        Integer hashKey = contactBookAt(subOp);
        if (library.containsKey(hashKey)) {
            System.out.println(subOp + " book found. add/delete/edit/search/info from your " + subOp +" contacts");
            return library.get(hashKey);
        } else {
            System.out.println("Book not found");
            return null;
        }
    }

    private void deleteBook(String subOp) {
        Integer hashKey = contactBookAt(subOp);
        if (library.containsKey(hashKey)) {
            library.delete(hashKey);
            System.out.println(subOp + " book deleted");
        } else System.out.println(subOp + " Book not found");
    }

    private void createBook(String subOp) {
        ContactBook newBook = new ContactBook();
        newBook.name = subOp;
        Integer hashKey = contactBookAt(subOp);
        library.add(hashKey, newBook);
        System.out.println("book " + subOp + " created");
    }
    private Integer contactBookAt(String position){                                     //radix hashing
        return Util.radixHashing(position);
    }

}
