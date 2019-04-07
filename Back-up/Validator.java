package GPA;

public class Validator {

    private String value;

    public Validator() {
    }

    public Validator(String value) {
        setValue(value);
    }

    public void setValue(String value) {
        value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return value;
    }

    public boolean isValidInteger() {
        try {
            int num = Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    public boolean isValidInteger(String val) {
        try {
            int num = Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    public boolean isValidDouble() {
        try {
            double num = Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    public boolean isValidDouble(String val) {
        try {
            double num = Double.parseDouble(val);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

    public boolean isValidBoolean() {
        return ("true".equalsIgnoreCase(value)) || ("false".equalsIgnoreCase(value));
    }

    public boolean isValidBoolean(String val) {
        return ("true".equalsIgnoreCase(val)) || ("false".equalsIgnoreCase(val));
    }
}
