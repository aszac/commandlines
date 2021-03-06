package uk.ac.reading.tq011338.parser;
public class Value {

    public static Value VOID = new Value(new Object());

    final Object value;

    public Value(Object value) {
        this.value = value;
    }

    public Boolean asBoolean() {
        return (Boolean)value;
    }

    public String asString() {
        return String.valueOf(value);
    }
    
    public Double asDouble() {
        return (Double)value;
    }
    
    public boolean isDouble() {
        return value instanceof Double;
    }
    
    public int hashCode() {

        if(value == null) {
            return 0;
        }

        return this.value.hashCode();
    }

    public boolean equals(Object o) {

        if(value == o) {
            return true;
        }

        if(value == null || o == null || o.getClass() != value.getClass()) {
            return false;
        }

        Value that = (Value)o;

        return this.value.equals(that.value);
    }


}