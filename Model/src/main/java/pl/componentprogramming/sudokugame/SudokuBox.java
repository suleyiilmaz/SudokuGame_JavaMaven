package pl.componentprogramming.sudokugame;

import java.util.List;

/**
 * Contain the fields of a box.
 *
 * @author Erwan
 */
public class SudokuBox extends SudokuGroup {

    public SudokuBox(List<SudokuField> group) {
        super(group);
    }

    @Override
    public String toString() {
        String res = "";
        int x = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res += group.get(x) + " ";
                x++;
            }
            res += "\n";
        }
        return res;
    }

}
