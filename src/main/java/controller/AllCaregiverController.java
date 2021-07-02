package controller;

import datastorage.DAOFactory;
import datastorage.CaregiverDAO;
import datastorage.TreatmentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Caregiver;
import utils.DateConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AllCaregiverController {
    @FXML
    private TableView<Caregiver> tableView;
    @FXML
    private TableColumn<Caregiver, String> colFirstname;
    @FXML
    private TableColumn<Caregiver, String> colSurname;
    @FXML
    private TableColumn<Caregiver, Long> colCid;
    @FXML
    private TableColumn<Caregiver, String> colTeleNr;


    @FXML
    Button btnDelete;
    @FXML
    Button btnAdd;
    @FXML
    TextField txtSurname;
    @FXML
    TextField txtFirstname;
    @FXML
    TextField txtTeleNr;


    private ObservableList<Caregiver> tableviewContent = FXCollections.observableArrayList();
    private CaregiverDAO dao;

    public void initialize() {
        readAllAndShowInTableView();

     this.colCid.setCellValueFactory(new PropertyValueFactory<Caregiver, Long>("cid"));

        //CellValuefactory zum Anzeigen der Daten in der TableView
        this.colFirstname.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("firstName"));
        //CellFactory zum Schreiben innerhalb der Tabelle
        this.colFirstname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colSurname.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("surname"));
        this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());

        this.colTeleNr.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("teleNr"));

        //Anzeigen der Daten
        this.tableView.setItems(this.tableviewContent);

    }

    @FXML
    public void handleOnEditFirstname(TableColumn.CellEditEvent<Caregiver, String> event){
        event.getRowValue().setFirstName(event.getNewValue());
        doUpdate(event);
    }

    @FXML
    public void handleOnEditSurname(TableColumn.CellEditEvent<Caregiver, String> event){
        event.getRowValue().setSurname(event.getNewValue());
        doUpdate(event);
    }

    private void doUpdate(TableColumn.CellEditEvent<Caregiver, String> t) {
        try {
            dao.update(t.getRowValue());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void readAllAndShowInTableView() {
        this.tableviewContent.clear();
        this.dao = DAOFactory.getDAOFactory().createCaregiverDAO();
        List<Caregiver> allCaregivers;
        try {
            allCaregivers = dao.readAll();
            for (Caregiver c : allCaregivers) {
                this.tableviewContent.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDeleteRow() {
        Caregiver selectedItem = this.tableView.getSelectionModel().getSelectedItem();
        try {
            dao.deleteById(selectedItem.getCid());
            this.tableView.getItems().remove(selectedItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void handleAdd() {
        String firstname = this.txtFirstname.getText();
        String surname = this.txtSurname.getText();
        String teleNr = this.txtTeleNr.getText();
        try {
            Caregiver c = new Caregiver(firstname, surname, teleNr);
            dao.create(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        readAllAndShowInTableView();
        clearTextfields();
    }

    private void clearTextfields() {
        this.txtFirstname.clear();
        this.txtSurname.clear();
        this.txtTeleNr.clear();
    }
}