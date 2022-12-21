package sample.admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.Main;
import sample.Person;
import javafx.fxml.Initializable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import static sample.Person.file;
import static sample.Person.line;

public class AdminPanel implements Initializable {
    @FXML
    private Button addVoter;
    @FXML
    private Button removeVoter;

    @FXML
    private TableView<Person> tableView;
    @FXML
    private TableColumn<Person,String> colName;
    @FXML
    private TableColumn<Person, String>colSex;
    @FXML
    private TableColumn<Person, String>colPhoneNumber;
    @FXML
    private TableColumn<Person, String>colVoterID;
    @FXML
    private TableColumn<Person, String>colPassword;
    @FXML
    private TextField name;
    @FXML
    private TextField sex;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField voterID;
    @FXML
    private TextField password;
    @FXML
    private Button adminLogOut;
    private LocalDate date;


    @FXML
    public void initialize(URL location, ResourceBundle resources) {
       /* //Toggle Group for gender
        sex=new ToggleGroup();
        this.maleRadioButton.setToggleGroup(sex);
        this.femaleRadioButton.setToggleGroup(sex);
        this.otherRadioButton.setToggleGroup(sex);*/

        //Sets Up Column to the table
        colName.setCellValueFactory(new PropertyValueFactory<Person, String>("voterName"));
        colSex.setCellValueFactory(new PropertyValueFactory<Person, String>("voterSex"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<Person, String>("voterPhoneNumber"));
        colVoterID.setCellValueFactory(new PropertyValueFactory<Person, String>("voterVoterID"));
        colPassword.setCellValueFactory(new PropertyValueFactory<Person, String>("voterPassword"));

        //loads dummy data
        try {
            tableView.setItems(getPeople());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Update the table to allow modification
        tableView.setEditable(true);
        colName.setCellFactory(TextFieldTableCell.forTableColumn());
        colSex.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhoneNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        colVoterID.setCellFactory(TextFieldTableCell.forTableColumn());
        colPassword.setCellFactory(TextFieldTableCell.forTableColumn());

        //This will allow select multiple row at once
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    private ObservableList<Person> getPeople() throws FileNotFoundException {
        ObservableList<Person> people = FXCollections.observableArrayList();
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\voters.txt", "rwd");
            Person.countLines();
            System.out.println(line);
            for(int i=1;i<line;i+=6){
                Person person = new Person(raf.readLine().substring(6), raf.readLine().substring(8), raf.readLine().substring(10), raf.readLine().substring(10), raf.readLine().substring(10),false);
                raf.readLine();
                people.add(person);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return people;
    }

    //this Method will help admin to double click and edit
    public void changeNameEvent(TableColumn.CellEditEvent edittedCell){
        Person personSelected= tableView.getSelectionModel().getSelectedItem();
        personSelected.setVoterName(edittedCell.getNewValue().toString());
    }
    public void changeSexEvent(TableColumn.CellEditEvent edittedCell){
        Person personSelected= tableView.getSelectionModel().getSelectedItem();
        personSelected.setVoterName(edittedCell.getNewValue().toString());
    }
    public void changePhoneNumberEvent(TableColumn.CellEditEvent edittedCell){
        Person personSelected= tableView.getSelectionModel().getSelectedItem();
        personSelected.setVoterName(edittedCell.getNewValue().toString());
    }
    public void changeVoterIDEvent(TableColumn.CellEditEvent edittedCell){
        Person personSelected= tableView.getSelectionModel().getSelectedItem();
        personSelected.setVoterName(edittedCell.getNewValue().toString());
    }
    public void changePasswordEvent(TableColumn.CellEditEvent edittedCell){
        Person personSelected= tableView.getSelectionModel().getSelectedItem();
        personSelected.setVoterName(edittedCell.getNewValue().toString());
    }


    //Method That will add new voter
   @FXML
    public void addVoter(ActionEvent event) throws FileNotFoundException {
        Person newPerson=new Person(name.getText(), sex.getText(), phoneNumber.getText(),voterID.getText(),password.getText(),true);

        //get all items from the table as a list and add the new person to the list
        tableView.getItems().add(newPerson);
    }

    //Removes selected rows
    @FXML
    public void removeVoter(ActionEvent event){
        ObservableList<Person> selectedRows, allPeople;
        allPeople= tableView.getItems();

        //Gives rows that were selected
        selectedRows=tableView.getSelectionModel().getSelectedItems();

        //Loop over the selected rows and removes the person object from the table
        for (Person person: selectedRows){
            allPeople.remove(person);
        }
    }
    public void adminLogOut(ActionEvent event) throws  Exception{
        Main ma=new Main();
        ma.changeScene("FirstPage.fxml");
    }



}
