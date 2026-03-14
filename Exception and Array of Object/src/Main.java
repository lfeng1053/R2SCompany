public class Main {
    public static void main(String[] args) {
        ProductManagement pm = new ProductManagement();

        // Add products successfully
        try {
            pm.addProduct(new Product(1, "Laptop", 999.99, 5));
            pm.addProduct(new Product(2, "Mouse", 29.99, 50));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Retrieve product by ID - success
        try {
            Product p = pm.getProductById(1);
            p.displayProductInfo();
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Retrieve product - invalid ID (ProductNotFoundException)
        try {
            pm.getProductById(999);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Update quantity - success
        try {
            pm.updateQuantity(2, 100);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Update quantity - invalid ID (ProductNotFoundException)
        try {
            pm.updateQuantity(999, 10);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Add product with negative price (IllegalArgumentException)
        try {
            pm.addProduct(new Product(3, "Keyboard", -19.99, 20));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Add product with negative quantity (IllegalArgumentException)
        try {
            pm.addProduct(new Product(4, "Monitor", 299.99, -5));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
