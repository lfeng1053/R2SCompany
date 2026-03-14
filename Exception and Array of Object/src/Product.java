public class Product {
    private int productID;
    private String name;
    private double price;
    private int quantityInStock;

    public Product(int productID, String name, double price, int quantityInStock){
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void displayProductInfo() {
        System.out.println("Product ID: " + productID);
        System.out.println("Name: " + name);
        System.out.println("Price: $" + price);
        System.out.println("Quantity in stock: " + quantityInStock);
    }

    @Override
    public String toString() {
        return "Product{ID=" + productID + ", name='" + name + "', price=" + price + ", quantity=" + quantityInStock + "}";
    }
}
