package pl.componentprogramming.view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import pl.componentprogramming.dao.FileSudokuBoardDao;
import pl.componentprogramming.dao.SudokuBoardDaoFactory;
import pl.componentprogramming.sudokugame.SudokuBoard;
import pl.componentprogramming.sudokugame.BacktrackingSudokuSolver;
import pl.componentprogramming.sudokugame.SudokuDifficulty;
import pl.componentprogramming.sudokugame.SudokuDifficultySetter;

public class FXMLController implements Initializable {

    SudokuBoard sudoku;
    BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
    SudokuDifficultySetter difficultySetter = new SudokuDifficultySetter();
    TextFieldSudoku[][] textFields = new TextFieldSudoku[9][9];
    ArrayList<Locale> locales = new ArrayList<>();
    ResourceBundle bundle;
    int localeUsed = 0;
    int finish=0;
    
    @FXML
    AnchorPane sudokuPane;
    @FXML
    Button languageChooser;
    @FXML
    Menu menuFile;
    @FXML
    MenuItem menuEasy;
    @FXML
    MenuItem menuMedium;
    @FXML
    MenuItem menuHard;
    @FXML
    MenuItem menuSave;
    @FXML
    MenuItem menuOpen;
    @FXML
    MenuItem menuClose;
    @FXML
    Button checkButton;
    @FXML
    Label finishLabel;
    
    @FXML
    private void handleChangeLanguage(ActionEvent event) {
        switch (localeUsed) {
            case 2:
                changeBundle(locales.get(0));
                localeUsed=0;
                break;
            case 0:
                changeBundle(locales.get(1));
                localeUsed=1;
                break;
            default:
                changeBundle(locales.get(2));
                localeUsed=2;
                break;
        }
    }
    
    @FXML
    private void handleNewEasy(ActionEvent event) {
        clearSudokuPane();
        createNewSudoku();
        difficultySetter.setDifficulty(sudoku, SudokuDifficulty.EASY);
        initFields();
        drawLine();
    }

    @FXML
    private void handleNewMedium(ActionEvent event) {
        clearSudokuPane();
        createNewSudoku();
        difficultySetter.setDifficulty(sudoku, SudokuDifficulty.MEDIUM);
        initFields();
        drawLine();
    }

    @FXML
    private void handleNewHard(ActionEvent event) {
        clearSudokuPane();
        createNewSudoku();
        difficultySetter.setDifficulty(sudoku, SudokuDifficulty.HARD);
        initFields();
        drawLine();
    }
    
    @FXML
    private void handleSave(ActionEvent event) {
        final FileChooser dialog = new FileChooser(); 
        final File file = dialog.showSaveDialog(sudokuPane.getScene().getWindow()); 
        if (file != null) { 
            SudokuBoardDaoFactory sBDF = new SudokuBoardDaoFactory();
            FileSudokuBoardDao dao = (FileSudokuBoardDao) sBDF.getFileDao(file.getAbsolutePath());
            dao.write(sudoku);
        }
    }
    
    @FXML
    private void handleOpen(ActionEvent event) {
        finish=0;
        finishLabel.setText("");
        final FileChooser dialog = new FileChooser(); 
        final File file = dialog.showOpenDialog(sudokuPane.getScene().getWindow()); 
        if (file != null) { 
            SudokuBoardDaoFactory sBDF = new SudokuBoardDaoFactory();
            FileSudokuBoardDao dao = (FileSudokuBoardDao) sBDF.getFileDao(file.getAbsolutePath());
            sudoku = dao.read();
            clearSudokuPane();
            initFields();
            drawLine();
        }
    }
    
    @FXML
    private void handleCheck(ActionEvent event) {
        int i=0;
        boolean res=true;
        while(i<9 && res){
            res = sudoku.getRow(i).verify();
            i++;
        }
        i=0;
        while(i<9 && res){
            res = sudoku.getColumn(i).verify();
            i++;
        }
        i=0;
        int j =0;
        while(i<9 && res){
            while(j<9 && res){
                res = sudoku.getBox(i,j).verify();
                j+=3;
            }
            i+=3;
        }
        if(res){
            finishLabel.setText(bundle.getString("win"));
            finish=1;
        } else {
            finishLabel.setText(bundle.getString("lose"));
            finish=-1;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initBundles();
        createNewSudoku();
        difficultySetter.setDifficulty(sudoku, SudokuDifficulty.MEDIUM);
        initFields();
        drawLine();
    }
    
    public void initBundles(){
        locales.add(new Locale("en", "UK"));
        locales.add(new Locale("fr", "FR"));
        locales.add(new Locale("tr", "TR"));
        bundle = ResourceBundle.getBundle("bundles.strings", locales.get(0));
    }
    
    public void changeBundle(Locale locale){
        bundle = ResourceBundle.getBundle("bundles.strings", locale);
        languageChooser.setText(bundle.getString("language"));
        menuFile.setText(bundle.getString("file"));
        menuEasy.setText(bundle.getString("easy"));
        menuMedium.setText(bundle.getString("medium"));
        menuHard.setText(bundle.getString("hard"));
        menuSave.setText(bundle.getString("save"));
        menuOpen.setText(bundle.getString("open"));
        menuClose.setText(bundle.getString("close"));
        checkButton.setText(bundle.getString("check"));
        if(finish==1){
            finishLabel.setText(bundle.getString("win"));
        } else if(finish==-1){
            finishLabel.setText(bundle.getString("lose"));
        }
    }
    
    public void initFields(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final TextFieldSudoku textFieldSudoku = new TextFieldSudoku(i,j,50);
                textFields[i][j]=textFieldSudoku;
                textFieldSudoku.setTextValue(sudoku.get(i,j));
                
                textFieldSudoku.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        int value = textFieldSudoku.tryAValue(newValue);
                        if(value!=0){
                            if(!sudoku.set(textFieldSudoku.getRow(),textFieldSudoku.getColumn(),value)){
                                Platform.runLater(new Runnable(){
                                    @Override
                                    public void run(){
                                        textFieldSudoku.clear();
                                    }
                                });
                            }
                        } else {
                            sudoku.set(textFieldSudoku.getRow(),textFieldSudoku.getColumn(),0);
                            Platform.runLater(new Runnable(){
                                    @Override
                                    public void run(){
                                        textFieldSudoku.clear();
                                    }
                                });
                        }
                    }
                });
                
                sudokuPane.getChildren().add(textFieldSudoku);
            }
        }
    }
    
    public void clearSudokuPane(){
        sudokuPane.getChildren().clear();
    }
    
    public void createNewSudoku(){
        finish=0;
        finishLabel.setText("");
        sudoku = new SudokuBoard();
        sudoku.initialiseRandomRow();
        solver.solve(sudoku);
    }

    public void drawLine() {
        Line l = new Line();
        l.setStrokeWidth(2);
        l.setStartX(150);
        l.setStartY(0.0f);
        l.setEndX(150);
        l.setEndY(450.0f);
        sudokuPane.getChildren().add(l);
        Line l2 = new Line();
        l2.setStrokeWidth(2);
        l2.setStartX(300);
        l2.setStartY(0.0f);
        l2.setEndX(300);
        l2.setEndY(450.0f);
        sudokuPane.getChildren().add(l2);
        Line l3 = new Line();
        l3.setStrokeWidth(2);
        l3.setStartX(0);
        l3.setStartY(150);
        l3.setEndX(450);
        l3.setEndY(150);
        sudokuPane.getChildren().add(l3);
        Line l4 = new Line();
        l4.setStrokeWidth(2);
        l4.setStartX(0);
        l4.setStartY(300);
        l4.setEndX(450);
        l4.setEndY(300);
        sudokuPane.getChildren().add(l4);
    }
}
