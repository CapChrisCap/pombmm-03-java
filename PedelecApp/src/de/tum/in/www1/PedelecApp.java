package de.tum.in.www1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import de.tum.in.www1.model.Reservation;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PedelecApp extends Application {

    private String title = "PedelecApp";

    @Override
    public void start(Stage primaryStage) {

        final VBox vboxLayout = new VBox(20);
        vboxLayout.setAlignment(Pos.CENTER);
        final Scene scene = new Scene(vboxLayout, 600, 500);

        final ImageView imageView = new ImageView();
        imageView.setImage(new Image(getClass().getResourceAsStream("Pedelec.png")));
        final Text pedelecNametext = new Text("Pedelec Magma 12");
        final Text chooseText = new Text("Choose Starting Time:");
        final DatePicker datePicker = new DatePicker();
        final TextField timeTextField = new TextField("2:00 pm");
        timeTextField.setMaxWidth(100);
        final Button reserveButton = new Button("Reserve");

        vboxLayout.getChildren().add(imageView);
        vboxLayout.getChildren().add(pedelecNametext);
        vboxLayout.getChildren().add(chooseText);
        vboxLayout.getChildren().add(datePicker);
        vboxLayout.getChildren().add(timeTextField);
        vboxLayout.getChildren().add(reserveButton);

        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();

        reserveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                System.out.println("Reserve Button clicked");
                final LocalDate datePickerValue = datePicker.getValue();
                final String datepickerTextValue = timeTextField.getText();
                final String pedelecName = pedelecNametext.getText();
                final String confirmationMessage = "Please confirm your reservation of " + pedelecName + " at " +
                        datePickerValue.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + datepickerTextValue;
                final ButtonType reserveButton = new Alert(Alert.AlertType.CONFIRMATION, "" +
                        confirmationMessage).showAndWait().get();

                if (reserveButton == ButtonType.OK) {
                    final Reservation reservation = new Reservation();
                    reservation.setBike(pedelecName);
                    reservation.setStartDate(datePickerValue);
                    reservation.setStartTime(timeTextField.getText());
                    reservation.save();
                    System.out.println("Reservation confirmed");
                }
            }
        });
    }



    public static void main(String[] args) {
        launch(args);
    }
}