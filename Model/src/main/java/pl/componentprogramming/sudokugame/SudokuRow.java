package pl.componentprogramming.sudokugame;

import java.util.List;

/**
 * Contain the fields of a row.
 *
 * @author Erwan
 */
public class SudokuRow extends SudokuGroup {

    public SudokuRow(List<SudokuField> group) {
        super(group);
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < group.size(); i++) {
            res += group.get(i) + " ";
        }
        return res;
    }
}
