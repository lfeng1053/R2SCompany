public class Program {
    private Product[] products;
    private byte numOfProduct;
    private final byte MAX = 100;

    public Program(){
        products = new Product[MAX];
        numOfProduct = 0;
    }

    public void addProduct(Product product){
        if(product == null || numOfProduct >= MAX){
            return;
        }
        products[numOfProduct] = product;
        numOfProduct++;
    }

    public void displayProducts(){
        for(int i =0; i < numOfProduct; i++){
            System.out.println(products[i].toString());
        }
    }
    public Product findProduct(int id) {
        for(int i = 0; i < numOfProduct; i++){
            if(products[i].getId() == id){
                return products[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Program program = new Program();

        program.addProduct(new Electronics(2, "Laptop", 1500f, "Dell"));
        program.addProduct(new Clothing(3, "T-Shirt", 25f, "M"));
        System.out.println("--- All products ---");
        program.displayProducts();
        System.out.println("--- Find product id=2 ---");
        Product found = program.findProduct(2);
        if (found != null) {
            System.out.println(found);
        }
    }
}