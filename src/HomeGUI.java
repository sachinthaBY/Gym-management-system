import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class HomeGUI extends Application {

    private static MyGymManager manager;

    public static void main(String[] args){
        launch(args);
    }

    public static void setManager(MyGymManager manager0){
        manager = manager0;
    }

    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("Gym Management System");

        TableView tableView = new TableView();

        TableColumn<String, DefaultMember> column1 = new TableColumn<>("First Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<String, DefaultMember> column2 = new TableColumn<>("Membership No");
        column2.setCellValueFactory(new PropertyValueFactory<>("membershipNo"));

        TableColumn<String, DefaultMember> column3 = new TableColumn<>("Membership Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("membershipStartDate"));

        TableColumn<String, DefaultMember> column4 = new TableColumn<>("Weight");
        column4.setCellValueFactory(new PropertyValueFactory<>("weight"));

        TableColumn<String, DefaultMember> column5 = new TableColumn<>("Height");
        column5.setCellValueFactory(new PropertyValueFactory<>("height"));


        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);


        for (DefaultMember m : this.manager.getMemberList()){
            tableView.getItems().add(m);
        }

        //tableView.getItems().add(new DefaultMember("12", ));
        Label label1 = new Label("Name: ");
        TextField textName = new TextField();
        Button nameSearchButton = new Button("Search");
        Button allButton = new Button("All");
        Label label2 = new Label("Number: ");
        TextField textNo = new TextField();
        Button noSearchButton = new Button("Search");

        nameSearchButton.setOnAction(new EventHandler<ActionEvent>(){
           public void handle(ActionEvent e){
               tableView.getItems().clear();
               List<DefaultMember> found = manager.getMemberByFirstName(textName.getText());
               for (DefaultMember m : found){
                   tableView.getItems().add(m);
               }
           }
        });

        noSearchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                tableView.getItems().clear();
                DefaultMember found = manager.getMemberByMembershipNo(textNo.getText());
                tableView.getItems().add(found);
            }
        });

        allButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                tableView.getItems().clear();
                for (DefaultMember m : manager.getMemberList()) {
                    tableView.getItems().add(m);
                }
            }
        });

        HBox hb1 = new HBox();
        hb1.getChildren().addAll(label1, textName, nameSearchButton);
        hb1.setSpacing(10);
        hb1.setAlignment(Pos.CENTER);

        HBox hb2 = new HBox();
        hb2.getChildren().addAll(label2, textNo, noSearchButton);
        hb2.setSpacing(10);
        hb2.setAlignment(Pos.CENTER);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(hb1, hb2, allButton, tableView);
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
