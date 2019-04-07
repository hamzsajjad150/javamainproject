 /* 
Name:       Javier Nievas 
Assignment: Assignment(******************)
Program:    PROG24178 
Date:       3-Apr-2019
 
Description: [program description in your own words] 
*/
package GPA;
/**
 *
 * @author Javier Nievas
 */
public class Course {
    /**
     * @param args the command line arguments
     */

    private int CourseID;
    private String courseName;
    private String proffesor;
    private int credits;
    private double gpa;
    private String alpha;

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProffesor() {
        return proffesor;
    }

    public void setProffesor(String proffesor) {
        this.proffesor = proffesor;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }
    
    


}
