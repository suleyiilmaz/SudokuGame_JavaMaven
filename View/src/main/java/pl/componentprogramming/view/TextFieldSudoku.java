package pl.componentprogramming.view;

import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * TextFieldSudoku is a TextField with more functionnalities related to
 * SudokuGame.
 *
 * @author Erwan
 */
public class TextFieldSudoku extends TextField {

    private int row;
    private int column;

    public TextFieldSudoku(int row, int column, int size) {
        super();
        this.row = row;
        this.column = column;
        this.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
        this.setPrefSize(size, size);
        this.setLayoutX(this.column * size);
        this.setLayoutY(this.row * size);
    }

    /**
     * Return row number.
     *
     * @return row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Return column number.
     *
     * @return column
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * Initialise text value in a textfield and change the color of the
     * background.
     *
     * @param value to display
     */
    public void setTextValue(int value) {
        if (value != 0) {
            this.setText(Integer.toString(value));
            this.setEditable(false);
            Paint color = Paint.valueOf("00FFFF");
            this.setStyle("-fx-control-inner-background: #" + color.toString().substring(2));
        }
    }

    /**
     * Try if a value is number between 1(include) and 9(include).
     *
     * @param value to try
     * @return the value in integer if it's correct, 0 if not
     */
    public int tryAValue(String value) {
        if(value.matches("[0-9]")){
            int intValue = Integer.valueOf(value);
            if (intValue > 0 && intValue <= 9) {
                return intValue;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
