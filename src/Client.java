import java.util.ArrayList;
import java.util.List;

class Order {
    private List<Item> items;
    private float totalPrice;
    private float shippingCost;
    private List<OrderObserver> observers;

    public Order();

    public void addItem(Item item, int quantity);

    public void addItem(Item item);

    public void removeItem(Item item);

    public void attach(OrderObserver observer);

    public void detach(OrderObserver observer);

    public void notifyObservers();

    public void updateOrder();

    private void calculateTotalPrice();

    public float getTotalPrice();

    public void setTotalPrice(float price);

    public float getShippingCost();

    public void setShippingCost(float price);

    public List<Item> getItems();
}

interface OrderObserver {
    void update(Order order);
}

class PriceObserver implements OrderObserver {
    @Override
    public void update(Order order);
}

class QuantityObserver implements OrderObserver {
    @Override
    public void update(Order order);
}

class Item {
    private String name;
    private float price;

    public Item(String name, float price);

    public String getName();

    public float getPrice();
}

public class Client {
    public static void main(String[] args);
}
