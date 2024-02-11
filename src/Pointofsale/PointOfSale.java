package pos2.src.Pointofsale;

public class PointOfSale {
    
    private String name;
    private int quantity,sold;
    private double price,sales;

    public PointOfSale(String name, int quantity, double price){
        this.name=name;
        this.quantity=quantity;
        this.price=price;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantityAdd(int add){
        this.quantity+=add;
    }

    public void setQuantitySold(int sold){
        this.quantity-=sold;
    }

    public void setSold(double sold){
        this.sold+=sold;
    }

    public int getSold(){
        return sold;
    }

    public void setSales(double sales){
        this.sales+=sales;
    }

    public double getSales(){
        return sales;
    }
}
