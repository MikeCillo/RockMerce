package LogicTier.Entità;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Checkout {
    private int idCheckout;

    private double totalPrice=0.00;

    private String sendDate;

    private String orderDate;

    private int cartId;

    private Customer customer;

    ArrayList<Guitar> guitars=new ArrayList<>();


    public int getCartId() {
        return cartId;
    }

   public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public void addGuitar(Guitar guitar){
        this.guitars.add(guitar);
        this.totalPrice+=guitar.getPrice();
    }

    public void setOrderDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.orderDate= today.format(dateTimeFormatter);
    }


    public void setSendDate(){
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusMonths(2);

        List<LocalDate> listOfDates = startDate.datesUntil(endDate).collect(Collectors.toList());
        Random random=new Random();

        LocalDate date=(listOfDates.get(random.nextInt(listOfDates.size())));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.sendDate= date.format(dateTimeFormatter);
    }


    public int getIdCheckout() {
        return idCheckout;
    }

    public void setIdCheckout(int idCheckout) {
        this.idCheckout = idCheckout;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ArrayList<Guitar> getGuitars() {
        return guitars;
    }

    public void setGuitars(ArrayList<Guitar> guitars) {
        this.guitars = guitars;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
