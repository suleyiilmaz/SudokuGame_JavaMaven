package pl.componentprogramming.sudokugame;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Store the value of a field in the board.
 *
 * @author Erwan
 */
public class SudokuField implements Serializable, Cloneable, Comparable {

    private int value;

    /**
     * Return the value of the field.
     *
     * @return value
     */
    public int getFieldValue() {
        return this.value;
    }

    /**
     * Set the value of the field.
     *
     * @param value of the field
     */
    public void setFieldValue(int value) {
        this.value = value;
    }

    /**
     * Return the value of the field in a String.
     *
     * @return a string of the value
     */
    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof SudokuField)) {
            return false;
        }

        SudokuField sudokuField = (SudokuField) o;

        return new EqualsBuilder()
                .append(value, sudokuField.value)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 45)
                .append(value)
                .toHashCode();
    }

    @Override
    public int compareTo(Object o) {
        SudokuField sudokuField2 = (SudokuField) o;
        if (this.value > sudokuField2.value) {
            return 1;
        } else if (this.value < sudokuField2.value) {
            return -1;
        } else {
            return 0;
        }
    }
}
