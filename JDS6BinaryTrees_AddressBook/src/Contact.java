import java.util.Scanner;

public class Contact <T>{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String eMail;
    private String relation;
    private String birthDate;
    private int Age;
    private Integer key = this.hashCode();

    public Contact(){ }

    //getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return eMail; }
    public String getRelation() { return relation; }
    public String getBirthDate() { return birthDate; }
    public int getAge() { return Age; }
    public Integer getObjectKey(){ return  key; }

    //setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setEmail(String eMail) { this.eMail = eMail; }
    public void setRelation(String relation) { this.relation = relation; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public void setAge(int age) { Age = age; }
    public void setKey(Integer key) { this.key = key; }
}
