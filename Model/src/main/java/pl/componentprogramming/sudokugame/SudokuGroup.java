package pl.componentprogramming.sudokugame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Contain the fields.
 *
 * @author Erwan
 */
public abstract class SudokuGroup implements Serializable, Cloneable {

    protected List<SudokuField> group = Arrays.asList(new SudokuField[9]);
    //protected SudokuField[] group = new SudokuField[9];

    public SudokuGroup(List<SudokuField> group) {
        this.group = group;
    }

    /**
     * Verify if a group of number is correct.
     *
     * @return true if correct
     */
    public boolean verify() {
        ArrayList<SudokuField> fields = new ArrayList<>();
        int i = 0;
        boolean res = true;
        while (i < this.group.size() && res) {
            if (!fields.contains(this.group.get(i))) {
                fields.add(this.group.get(i));
            } else {
                res = false;
            }
            i++;
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof SudokuGroup)) {
            return false;
        }

        SudokuGroup sudokuGroup = (SudokuGroup) o;

        return new EqualsBuilder()
                .append(group, sudokuGroup.group)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 45)
                .append(group)
                .toHashCode();
    }
}
