public class Statue  extends Item{
    private int weight;
    private String color;

    public Statue(){
        super();
    }

    public Statue(String id, int value, String creator, int weight, String color){
        super();
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void input(){
        super.input();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Weight: ");
        weight = Integer.parseInt(scanner.nextLine());
        System.out.println("Color: ");
        color = scanner.nextLine();

    }

    @Override
    public String toString(){
        return "Statue[" + super.toString() + ", weight=" + weight + ", color=" + color + "]";
    }
}
