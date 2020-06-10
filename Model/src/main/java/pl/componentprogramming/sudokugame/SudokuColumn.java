package pl.componentprogramming.sudokugame;

import java.util.List;

/**
 * Contain the fields of a column.
 *
 * @author Erwan
 */
public class SudokuColumn extends SudokuGroup {

    public SudokuColumn(List<SudokuField> group) {
        super(group);
    }

    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < group.size(); i++) {
            res += group.get(i) + "\n";
        }
        return res;
    }

}
