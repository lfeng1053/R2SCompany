public class Painting extends Item {
    private int height;
    private int width;
    private boolean isWaterColor;
    private boolean isFramed;

    public Painting() {
        super();
    }

    public Painting(String id, int value, String creator, int height, int width,
                    boolean isWaterColor, boolean isFramed) {
        super(id, value, creator);
        this.height = height;
        this.width = width;
        this.isWaterColor = isWaterColor;
        this.isFramed = isFramed;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isWaterColor() {
        return isWaterColor;
    }

    public void setWaterColor(boolean waterColor) {
        isWaterColor = waterColor;
    }

    public boolean isFramed() {
        return isFramed;
    }

    public void setFramed(boolean framed) {
        isFramed = framed;
    }

    @Override
    public void input() {
        super.input();
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Height: ");
        height = Integer.parseInt(sc.nextLine());
        System.out.print("Width: ");
        width = Integer.parseInt(sc.nextLine());
        System.out.print("Water color? (true/false): ");
        isWaterColor = Boolean.parseBoolean(sc.nextLine());
        System.out.print("Framed? (true/false): ");
        isFramed = Boolean.parseBoolean(sc.nextLine());
    }

    @Override
    public String toString() {
        return "Painting[" + super.toString() + ", height=" + height + ", width=" + width
                + ", waterColor=" + isWaterColor + ", framed=" + isFramed + "]";
    }
}

