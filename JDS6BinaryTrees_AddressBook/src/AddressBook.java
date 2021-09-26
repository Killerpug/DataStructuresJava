public interface AddressBook {
    public void addContact();
    public void displayContactInfo(TreeNode<Contact> contact);
    public TreeNode<Contact> searchContact(TreeNode<Contact> contact);
    public void editContact(TreeNode<Contact> contact);
    public void removeContact(TreeNode<Contact> contact);

}
