package br.com.fiap.tech.challenge;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class AssertionConcern implements Serializable {
    @Serial
    private static final long serialVersionUID = -5898477763983021330L;

    protected AssertionConcern(){
    }

    public void validate(){
        //will be replaced by feature that add bean validation
    }

    protected void assertArgumentEquals(Object object1, Object object2, String message) {
        if (!object1.equals(object2)) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentFalse(boolean value, String message) {
        if (value) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentLength(String value, int maximum, String message) {
        int length = value.trim().length();
        if (length > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentLength(String value, int minimum, int maximum, String message) {
        int length = value.trim().length();
        if (length < minimum || length > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentNotEmpty(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentNotEquals(Object object1, Object object2, String message) {
        if (object1.equals(object2)) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentNotNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
    
    protected void assertArgumentAtLeast(double value, double minimum, String message){
        if (value < minimum){
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentAtLeast(float value, float minimum, String message){
        if (value < minimum){
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentAtLeast(int value, int minimum, String message){
        if (value < minimum){
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentAtLeast(long value, long minimum, String message){
        if (value < minimum){
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentAtLeast(BigDecimal value, BigDecimal minimum, String message){
        if (value.compareTo(minimum) < 0){
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(double value, double minimum, double maximum, String message) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(float value, float minimum, float maximum, String message) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(int value, int minimum, int maximum, String message) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(long value, long minimum, long maximum, String message) {
        if (value < minimum || value > maximum) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentRange(BigDecimal value, BigDecimal minimum, BigDecimal maximum, String message) {
        if (value.compareTo(minimum) < 0 || value.compareTo(maximum) > 0) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertArgumentTrue(boolean value, String message) {
        if (!value) {
            throw new IllegalArgumentException(message);
        }
    }

    protected void assertStateFalse(boolean value, String message) {
        if (value) {
            throw new IllegalStateException(message);
        }
    }

    protected void assertStateTrue(boolean value, String message) {
        if (!value) {
            throw new IllegalStateException(message);
        }
    }
}
