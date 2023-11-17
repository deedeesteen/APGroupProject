
public class Date {
    private int day;
    private int month;
    private int year;

    public Date() {
        day = 0;
        month = 0;
        year = 0;
    }

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return (day + "/" + month + "/" + year);
    }

    /*
     * Implement a method to parse the date (e.g., using SimpleDateFormat)
     * private static Date parseDate(String dateText) {
     * try {
     * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     * return dateFormat.parse(dateText);
     * } catch (ParseException e) {
     * // Handle parsing errors
     * e.printStackTrace();
     * return null;
     * }
     * }
     */
}
