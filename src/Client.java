import java.util.ArrayList;
import java.util.List;

// Classe de l'objet Order
class Order {
    private List<Item> items;

    private int id;
    private float totalPrice;
    private float shippingCost;
    private List<OrderObserver> observers;

    public Order() {
        items = new ArrayList<>();
        totalPrice = 0.0f;
        shippingCost = 10.0f; // Valeur par défaut du coût d'expédition
        observers = new ArrayList<>();
        OrderObserver priceObserver = new PriceObserver();
        OrderObserver quantityObserver = new QuantityObserver();

        // Attachement des observateurs à l'objet Order
        this.attach(priceObserver);
        this.attach(quantityObserver);
    }

    public Order(int ID) {
        id = ID;
        items = new ArrayList<>();
        totalPrice = 0.0f;
        shippingCost = 10.0f; // Valeur par défaut du coût d'expédition
        observers = new ArrayList<>();
        OrderObserver priceObserver = new PriceObserver();
        OrderObserver quantityObserver = new QuantityObserver();

        // Attachement des observateurs à l'objet Order
        this.attach(priceObserver);
        this.attach(quantityObserver);
    }

    public void addItem(Item item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            items.add(item);
        }
        System.out.println("The item " + item.getName() + " had been added " + quantity + " times.");
        updateOrder();
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println("The item " + item.getName() + " had been added ");
        updateOrder();
    }


    public void removeItem(Item item) {
        items.remove(item);
        updateOrder();
    }

    public void attach(OrderObserver observer) {
        observers.add(observer);
    }

    public void detach(OrderObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }

    public void updateOrder() {
        calculateTotalPrice();
        notifyObservers();
    }

    private void calculateTotalPrice() {
        float total = 0.0f;
        for (Item item : items) {
            total += item.getPrice();
        }
        totalPrice = total;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float Price) {
        this.totalPrice = Price;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(float Price) {
        this.shippingCost = Price;
    }

    public List<Item> getItems() {
        return items;
    }
}

// Interface OrderObserver
interface OrderObserver {
    void update(Order order);
}

// Classe PriceObserver
class PriceObserver implements OrderObserver {
    @Override
    public void update(Order order) {
        if (order.getTotalPrice() > 200.0f) {
            order.setTotalPrice(order.getTotalPrice() -20.0f); // Appliquer une réduction de 20 $
        }
    }
}

// Classe QuantityObserver
class QuantityObserver implements OrderObserver {
    @Override
    public void update(Order order) {
        if (order.getItems().size() > 5) {
            order.setShippingCost(0.0f); // Définir le coût d'expédition à zéro
        } else {
            order.setShippingCost(10.0f); // Utiliser le coût d'expédition par défaut
        }
    }
}

// Classe Item
class Item {
    private String name;
    private float price;

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }
}

// Classe principale (client)
public class Client {
    public static void main(String[] args) {
        // Création de l'objet Order
        Order order = new Order();

        // Ajout d'articles à la commande
        order.addItem(new Item("Item 1", 50.0f));
        order.addItem(new Item("Item 2", 70.0f));
        order.addItem(new Item("Item 4", 30.0f));

        System.out.println("Total Price: $" + order.getTotalPrice());
        System.out.println("Shipping Cost: $" + order.getShippingCost());

        order.addItem(new Item("Item 3", 90.0f),2);
        System.out.println("Total Price: $" + order.getTotalPrice());
        System.out.println("Shipping Cost: $" + order.getShippingCost());

        order.addItem(new Item("Item 5", 110.0f));
        System.out.println("Total Price: $" + order.getTotalPrice());
        System.out.println("Shipping Cost: $" + order.getShippingCost());

    }
}
