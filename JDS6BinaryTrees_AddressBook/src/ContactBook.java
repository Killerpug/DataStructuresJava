import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ContactBook implements AddressBook{
    private Tree<Contact> contactTree = new Tree<>();
    public String name = "";

    public void addContact (){
        Contact newContact = new Contact();                 //create empty contact
        Scanner scan = new Scanner(System.in);

        //fill contact data
        System.out.print("Name: ");
        newContact.setFirstName( scan.nextLine() );
        System.out.print("Last Name: ");
        newContact.setLastName( scan.nextLine() );
        System.out.print("Phone Number: ");
        newContact.setPhoneNumber( scan.nextLine() );
        System.out.print("E-Mail: ");
        newContact.setEmail( scan.nextLine() );
        System.out.print("Relation: ");
        newContact.setRelation( scan.nextLine() );
        System.out.print("birthday (dd/mm/yyyy): ");
        newContact.setBirthDate( scan.nextLine() );

        //add contact to tree

        TreeNode<Contact> newContactNode = new TreeNode<>(Util.radixHashing(newContact.getFirstName()) ,newContact);   //Create the new node for the contact
        contactTree.insertNode(newContactNode);
    }

    public void displayContactInfo(TreeNode<Contact> contact) {

        System.out.println("Name: " + contact.nodeValue.getFirstName());
        System.out.println("Last name: " + contact.nodeValue.getLastName());
        System.out.println("Phone number: " + contact.nodeValue.getPhoneNumber());
        System.out.println("E-mail: " + contact.nodeValue.getEmail());
        System.out.println("Relation: " + contact.nodeValue.getRelation());
        System.out.println("Birthday: " + contact.nodeValue.getBirthDate());


    }

    public TreeNode<Contact> searchContact(TreeNode<Contact> contact) {
         return contactTree.searchNode(contactTree.rootNode, contact);
    }

    public void editContact(TreeNode<Contact> contact) {
        Contact newContact = new Contact();                 //create empty contact
        Scanner scan = new Scanner(System.in);
        newContact.setFirstName(contact.nodeValue.getFirstName());      //name cannot change due to hashing method
        System.out.print("Last Name: ");
        newContact.setLastName( scan.nextLine() );
        System.out.print("Phone Number: ");
        newContact.setPhoneNumber( scan.nextLine() );
        System.out.print("E-Mail: ");
        newContact.setEmail( scan.nextLine() );
        System.out.print("Relation: ");
        newContact.setRelation( scan.nextLine() );
        System.out.print("birthday (dd/mm/yyyy): ");
        newContact.setBirthDate( scan.nextLine() );

        //add contact to tree
        TreeNode<Contact> contactReplace = new TreeNode<>(Util.radixHashing(newContact.getFirstName()) ,newContact);   //Create the new node for the contact
        contactTree.insertNode(contactReplace);
        contactTree.removeNode(contact);

    }

    public void removeContact(TreeNode<Contact> contact) {
        contactTree.removeNode(contact);
    }

    public  Tree<Contact> viewContactTree(){
        return contactTree;
    }

    public int maxKeyNode(){
        return contactTree.maximum(contactTree.rootNode).nodeKey;
    }

    public void saveInorder(TreeNode<Contact> rootNode, FileWriter writer)
    {
        if (rootNode != null) {
            saveInorder(rootNode.leftChild,writer);     //save left child
            Contact entry = rootNode.nodeValue;
            try {
                writer.write(entry.getFirstName() +"^");
                writer.write(entry.getLastName() + "^");
                writer.write(entry.getPhoneNumber() + "^");
                writer.write(entry.getEmail() + "^");
                writer.write(entry.getBirthDate() + "^");
                writer.write(entry.getRelation() + "^");

            }catch (IOException e){
                System.out.println("cannot write, sorry");
            }
            saveInorder(rootNode.rightChild, writer);   //save right child
        }
    }

}
