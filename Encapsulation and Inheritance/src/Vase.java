public class Vase extends Item{
    private int height;
    private String material;

    public Vase(){
        super();
    }
    public Vase(String id, int value, String creator, int height, String material){
        super(id, value, creator);
        this.height = height;
        this.material = material;
    }
// Setter
    public void setHeight(int height) { this.height = height;}

    public void setMaterial(String material) { this.material = material;}
    // Getter

    public int getHeight() { return height;}

    public String getMaterial() { return material;}

    @Override
    public void input(){
        super.input();
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Height: ");
        height = Integer.parseInt(scanner.nextLine());
        System.out.println("Material: ");
        material = scanner.nextLine();

    }

    @Override
    public String toString(){
        return "Vase[" + super.toString() + ", height=" + height + ", material=" + material + "]";
    }

}


