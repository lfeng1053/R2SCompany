import  java.util.Scanner;
public class Item {

    protected String id;
    protected int value;
    protected String creator;
    public Item(){}
    public Item(String id, int value, String creator){
        this.id = id;
        this.value = value;
        this.creator = creator;
    }

    //Getter
    public String getId() { return id;}

    public int getValue() { return value;}

    public String getCreator() { return creator;}
// Setter
    public void setId(String id) { this.id = id;}

    public void setValue(int value) { this.value = value;}

    public void setCreator(String creator) {this.creator = creator; }

    public void input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID: ");
        id = scanner.nextLine();
        System.out.println("Value: ");
        value = Integer.parseInt(scanner.nextLine());
        System.out.println("Creator: ");
        creator = scanner.nextLine();
    }
    @Override
    public String toString(){
        return "Item[id=" + id + ", value=" + value + ", creator=" + creator + "]";
    }

}
