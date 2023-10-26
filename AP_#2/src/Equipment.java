public class Equipment {

    public String Category;

    public boolean Availability;

    public double cost;

    public Date Date;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public boolean isAvailability() {
        return Availability;
    }

    public void setAvailability(boolean availability) {
        Availability = availability;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }

    public Equipment() {
        this.Category = "";
        this.Availability = false;
        this.cost = 0.0;
        this.Date = new Date();
    }

    public Equipment(String c, boolean a, double cost, Date Date) {
        this.Availability = a;
        this.Category = c;
        this.cost = cost;
        this.Date = new Date(10, 20, 2023);
    }


    public void EquipmentForm()
    {
         
    }
}
