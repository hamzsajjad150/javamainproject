package GPA;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class CourseList extends ArrayList<Course> {

    private Course course;
    private File file = new File("courses.dat");

    protected final int COURSENAME_SIZE = 15;
    protected final int PROFFNAME_SIZE = 15;
    protected final int ALPHA_SIZE = 2;
    
    protected final long RECORD_SIZE = 60;
    protected RandomAccessFile raf;

    public CourseList() {
        long index = 0L;
        try {
                            System.out.println("Path: " + file.getPath());      //check point
            raf = new RandomAccessFile(file, "rw");
                            System.out.println("raf length: " + raf.length());
            if (raf.length() == 0) {
                index = 1;
            } else {
                index = raf.length() / RECORD_SIZE;
            }

                        System.out.println("Index : " + index);           // check point
            raf.seek(0);
            int i = 0;
            while (i < index) {
                course = readRecord();
                add(course);
                ++i;
            }
        } catch (IOException e) {
            System.out.println("Constructor " + e.getMessage());
        }
    }

    private Course readRecord() {
        try {
            course = new Course();
            course.setCourseID(raf.readInt());
            course.setCourseName(readString(COURSENAME_SIZE));
            course.setProffesor(readString(PROFFNAME_SIZE));
            course.setCredits(raf.readInt());
            course.setGpa(raf.readDouble());
            course.setAlpha(readString(ALPHA_SIZE));

        } catch (IOException ex) {
            System.out.println("Error to read file in readRecord(): " + ex.getMessage());
        }

        return course;
    }

    public void writeRecord(Course localCourse) {
        try {
            
            int courseId = (int) ((long) ((int) raf.getFilePointer()) / RECORD_SIZE);
            String courseName = prepField(localCourse.getCourseName(), COURSENAME_SIZE);
            String proffName = prepField(localCourse.getProffesor(), PROFFNAME_SIZE);
            int credits = localCourse.getCredits();

            raf.seek(raf.length());

            raf.writeInt(courseId);
            raf.writeChars(courseName);
            raf.writeChars(proffName);
            raf.writeInt(credits);

        } catch (IOException ex) {
            System.out.println("Error writting in write " + ex.getMessage());
        }
    }

    public void writePartialCollection(int index) {
        try {
            raf.setLength((long) index * RECORD_SIZE);
            for (int i = index; i < size(); ++i) {
                writeRecord((Course) get(i));
            }
        } catch (IOException ex) {
            System.out.println("Error writing collection " + ex.getMessage());
        }
    }

    public String prepField(String value, int size) {
        if (value.length() < size) {
            int numSpaces = size - value.length();
            for (int i = 0; i < numSpaces; ++i) {
                value = value + " ";
            }
        } else {
            value = value.substring(0, size);
        }
        return value;
    }

    public String readString(int size) throws IOException {
        String n = "";
        for (int i = 0; i < size; ++i) {
            n = n + String.valueOf(raf.readChar());
        }
        return n;
    }

    public ArrayList<Course> searchEstate(String estate) {
        ArrayList estateList = new ArrayList<Course>();
        for (Course w : this) {
            if (!w.getCourseName().trim().equalsIgnoreCase(estate)) {
                continue;
            }
            estateList.add(w);
        }
        return estateList;
    }
}
