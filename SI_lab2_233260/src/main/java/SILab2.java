import java.util.List;

class Item {
    String name;
    int quantity; //numerical
    int price;
    double discount;

    public Item(String name, int quantity, int price, double discount) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}


public class SILab2 {
    public static double checkCart(List<Item> allItems, String cardNumber){
        if (allItems == null){ // 1
            throw new RuntimeException("allItems list can't be null!"); // 2
        }

        double sum = 0; // 3

        for (int i = 0; i < allItems.size(); i++){ // 4.1 4.2 4.3
            Item item = allItems.get(i); // 5
            if (item.getName() == null || item.getName().length() == 0){ // 6
                throw new RuntimeException("Invalid item!"); // 7
            }

            if (item.getPrice() > 300 || item.getDiscount() > 0 || item.getQuantity() > 10){ // 8
                sum -= 30; // 9
            }

            if (item.getDiscount() > 0){ // 10
                sum += item.getPrice()*(1-item.getDiscount())*item.getQuantity(); // 11
            }
            else {
                sum += item.getPrice()*item.getQuantity(); // 12
            }

        }
        if (cardNumber != null && cardNumber.length() == 16) { // 13
            String allowed = "0123456789"; // 14
            char[] chars = cardNumber.toCharArray(); // 15
            for (int j = 0; j < cardNumber.length(); j++) { // 16.1 16.2 16.3
                char c = cardNumber.charAt(j); // 17
                if (allowed.indexOf(c) == -1) { // 18
                    throw new RuntimeException("Invalid character in card number!"); // 19
                }
            }
        }
        else{
            throw new RuntimeException("Invalid card number!"); // 20
        }

        return sum; // 21

    }
}