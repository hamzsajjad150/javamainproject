
package GPA;

public class Wine {
    
    private int CourseID;
    private String courseName;
    private String grape;
    private int year;
    private double price;
    private int quantity;

    public int getCourseID() {
        return CourseID;
    }
    
    public void setCourseID(int courseID) {
        this.CourseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String estate) {
        this.courseName = estate;
    }

    public String getGrape() {
        return grape;
    }

    public void setGrape(String grape) {
        this.grape = grape;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

