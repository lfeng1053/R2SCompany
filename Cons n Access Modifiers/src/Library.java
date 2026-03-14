public class Library {

    public static void main(String[] args) {
        Book book1 = new Book("A", "A", 1999);
        Book book2 = new Book("B", "B", 1999);
        Book book3 = new Book("C", "C", 1999);

        displayBookInfo(book1);
        displayBookInfo(book2);
        displayBookInfo(book3);
    }

    private static void displayBookInfo(Book book) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Publication Year: " + book.getPublicationYear());
    }
}

