public class ProductManagement {
    private static final int MAX_PRODUCTS = 10;
    private final Product[] products;
    private int count;

    public ProductManagement() {
        this.products = new Product[MAX_PRODUCTS];
        this.count = 0;
    }

    public void addProduct(Product product) {
        if (count >= MAX_PRODUCTS) {
            throw new IllegalArgumentException("Cannot add more than " + MAX_PRODUCTS + " products.");
        }
        if (product.getPrice() < 0 || product.getQuantityInStock() < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative.");
        }
        for (int i = 0; i < count; i++) {
            if (products[i].getProductID() == product.getProductID()) {
                throw new IllegalArgumentException("Product with this ID already exists.");
            }
        }
        products[count++] = product;
        System.out.println("Product added successfully.");
    }

    public Product getProductById(int productID) throws ProductNotFoundException {
        for (int i = 0; i < count; i++) {
            if (products[i].getProductID() == productID) {
                return products[i];
            }
        }
        throw new ProductNotFoundException("Product with ID " + productID + " not found.");
    }

    public void updateQuantity(int productID, int newQuantity) throws ProductNotFoundException {
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Price and quantity must be non-negative.");
        }
        for (int i = 0; i < count; i++) {
            if (products[i].getProductID() == productID) {
                products[i].setQuantityInStock(newQuantity);
                System.out.println("Quantity updated successfully.");
                return;
            }
        }
        throw new ProductNotFoundException("Product with ID " + productID + " not found.");
    }
}
