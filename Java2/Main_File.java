import java.util.*;

public class Main_Test {

    public static void main(String[] args) {

        List<Item> list = new ArrayList<>();

        list.add(new Item(1, "Apple", 2.5, "Fruit"));
        list.add(new Item(2, "Celery", 2.75, "Vegetable"));
        list.add(new Item(3, "Orange", 2.5, "Fruit"));
        list.add(new Item(4, "Milk", 6.0, "Dairy"));
        list.add(new Item(5, "Cheese", 8.5, "Dairy"));
        list.add(new Item(6, "Grapes", 3.5, " Fruit"));

        Collections.sort(list, new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.getPrice() < o2.getPrice())
                    return -1;
                else if (o1.getPrice() > o2.getPrice())
                    return 1;
                else
                    return 0;
            }
        });
        Iterator<Item> itr = list.iterator();

        System.out.println("Output 1: Item List:");

        while (itr.hasNext()) {
            System.out.println(itr.next().getName());
        }
        System.out.println("---------------------------------");


        System.out.println("Output 2: Most Expensive Item:");
        Comparator<Item> comparator = Comparator.comparing(Item::getPrice);
        Item maxObject = list.stream().max(comparator).get();

        System.out.println(maxObject);
        System.out.println("---------------------------------");


        System.out.println("Output 3:List Operation:");
        ListIterator<Item> iterate = null;

        list.get(2).setPrice(3.5);
        list.add(new Item(7, "Strawberry", 4.5, "Fruit"));
        itr = list.listIterator();

        while (itr.hasNext()) {
            Item next = itr.next();
            System.out.println(next.getName() + " costs " + next.getPrice());
        }
        System.out.println("---------------------------------");


        System.out.println("Output 4:‘Mapping’ the items:");
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter category");
        String category = in.nextLine();

        Map<Integer, Item> itemInCategory = new HashMap<>();
        itr = list.listIterator();

        while (itr.hasNext()) {
            Item next = itr.next();
            if (next.getCategory().equals(category)) {
                itemInCategory.put(next.getId(), next);
            }
        }

        for (Integer key : itemInCategory.keySet()) {
            Item item = itemInCategory.get(key);
            System.out.println(item.getName() + " costs " + item.getPrice());
        }
    }
}
