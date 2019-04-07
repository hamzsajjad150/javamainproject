package GPA;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class WineCollection extends ArrayList<Wine> {

    private Wine wine;
    private File file = new File("wines.dat");
    protected int FIELD_SIZE = 10;
    protected long RECORD_SIZE = 60;
    protected RandomAccessFile raf;

    public WineCollection() {
        try {
            System.out.println("Path: "  + file.getPath());      //check point
            raf = new RandomAccessFile(file, "rw");
            long recNum = raf.length() / RECORD_SIZE;
            System.out.println("Recnum : " + recNum);           // check point
            raf.seek(0);
            int i = 0;
            while ( i < recNum) {
                wine = readRecord();
                add(wine);
                ++i;
            }
        } catch (IOException e) {
            System.out.println("Constructor " + e.getMessage());
        }
    }

    private Wine readRecord() {
        try {
            
            wine = new Wine();
            wine.setCourseID(raf.readInt());
            wine.setCourseName(readString(FIELD_SIZE));
            wine.setGrape(readString(FIELD_SIZE));
            wine.setYear(raf.readInt());
            wine.setPrice(raf.readDouble());
            wine.setQuantity(raf.readInt());
        } catch (IOException ex) {
            System.out.println("readRecord " + ex.getMessage());
        }
        
        return wine;
    }

    public void writeRecord(Wine newWine) {
        try {
            int wineId = (int) ((long) ((int) raf.getFilePointer()) / RECORD_SIZE);
            String estate = prepField(newWine.getCourseName(), FIELD_SIZE);
            String grape = prepField(newWine.getGrape(), FIELD_SIZE);
            int year = newWine.getYear();
            double price = newWine.getPrice();
            int quantity = newWine.getQuantity();
            raf.seek(raf.length());
            raf.writeInt(wineId);
            raf.writeChars(estate);
            raf.writeChars(grape);
            raf.writeInt(year);
            raf.writeDouble(price);
            raf.writeInt(quantity);
        } catch (IOException ex) {
            System.out.println("writeRecord " + ex.getMessage());
        }
    }

    public void writePartialCollection(int index) {
        try {
            raf.setLength((long) index * RECORD_SIZE);
            for (int i = index; i < size(); ++i) {
                writeRecord((Wine) get(i));
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

    public ArrayList<Wine> searchEstate(String estate) {
        ArrayList<Wine> estateList = new ArrayList<Wine>();
        for (Wine w : this) {
            if (!w.getCourseName().trim().equalsIgnoreCase(estate)) {
                continue;
            }
            estateList.add(w);
        }
        return estateList;
    }
}
