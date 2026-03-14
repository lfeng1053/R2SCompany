public class ItemList {
    private Item[] list;
    private int numOfItem;
    private static final int MAX = 100;

    public ItemList(){
        list = new Item[MAX];
        numOfItem = 0;
    }

    public boolean addItem(Item item){
        if(item == null || numOfItem >= MAX){
            return false;
        }
        list[numOfItem] = item;
        numOfItem++;
        return true;
    }

    public void displayAll(){
        for(int i = 0; i < numOfItem; i++){
            System.out.println(list[i].toString());
        }
    }

    public Item findItem(String creator){
        if(creator == null){
            return null;
        }
        for(int i = 0; i < numOfItem; i++) {
            if (creator.equals(list[i].getCreator())) {
                return list[i];
            }
        }
        return null;

    }

    public void displayItemByType(String type){

        if (type == null) return;
        String typeLower = type.trim().toLowerCase();
        for (int i = 0; i < numOfItem; i++) {
            String className = list[i].getClass().getSimpleName().toLowerCase();
            if (className.equals(typeLower)) {
                System.out.println(list[i].toString());
            }
        }
    }
}


