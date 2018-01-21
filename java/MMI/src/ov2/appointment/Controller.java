package ov2.appointment;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.ResourceBundle;

public class Controller implements Initializable {


    public static final ArrayList<String> repeatChoices = new ArrayList<String>() {{
        add("Never repeat");
        add("Every day");
        add("Every other day");
        add("Every week");
        add("Every year");
        add("Custom...");
    }};

    public static final ArrayList<String> roomChoices = new ArrayList<String>() {{
        add("Room 1");
        add("Room 2");
        add("Room 3");
        add("Room 4");
        add("Room 5");
        add("Room 6");
        add("Room 7");
        add("Room 8");
        add("Room 9");
        add("Room 10");
        add("Room 11");
        add("Room 12");
    }};


    @FXML
    private TextField startHour, startMinute, endHour, endMinute;

    @FXML
    private DatePicker dateChoice;

    @FXML
    private ListView<Appointment> appointmentList;

    @FXML
    private ComboBox repeatChoice;

    @FXML
    private ComboBox roomChoice;

    @FXML
    private TextField purposeText;

    @Override
    public void initialize (URL url, ResourceBundle rb) {
        assert appointmentList != null;
        assert repeatChoice != null;
        assert roomChoice != null;
        assert dateChoice != null;

        dateChoice.setValue(LocalDate.now());
        repeatChoice.setValue(repeatChoices.get(0));

        roomChoice.getItems().addAll(FXCollections.observableArrayList(roomChoices));
        repeatChoice.getItems().addAll(FXCollections.observableArrayList(repeatChoices));
    }


    public void createAppointment(Event event) {
        validateForm();

        if (roomChoice.getValue() == null) {
            roomChoice.setValue("Room 1");
        }

        LocalTime fromTime = LocalTime.of(
                Integer.parseInt(startHour.getText()),
                Integer.parseInt(startMinute.getText())
        );
        LocalTime toTime = LocalTime.of(
                Integer.parseInt(endHour.getText()),
                Integer.parseInt(endMinute.getText())
        );
        Appointment appointment = new Appointment(
                this.purposeText.getText(),
                this.roomChoice.getValue().toString(),
                this.dateChoice.getValue(),
                fromTime,
                toTime,
                this.repeatChoice.getItems().indexOf(this.repeatChoice.getValue())
        );


        appointmentList.getItems().addAll(FXCollections.observableArrayList(appointment));

    }

    private void validateForm() {
        LocalTime start = LocalTime.of(Integer.parseInt(startHour.getText()), Integer.parseInt(startMinute.getText()));
        LocalTime end = LocalTime.of(Integer.parseInt(endHour.getText()), Integer.parseInt(endMinute.getText()));

        if(start.compareTo(end) >= 0) {
            throw new IllegalStateException("check start and end time");
        }

        LocalDate date = LocalDate.from(dateChoice.getValue());

        if(date.compareTo(LocalDate.now()) < 0) {
            throw new IllegalStateException("must set to future date");
        }

        if(!repeatChoices.contains(repeatChoice.getValue())) {
            throw new IllegalArgumentException(repeatChoice.getValue() + " is not a legal choice");
        }

        if(roomChoice.getValue() == null) {
            throw new IllegalArgumentException("You must choose room");
        }

        if(!roomChoices.contains(roomChoice.getValue())) {
            throw new IllegalArgumentException(roomChoice.getValue() + " is not a legal choice");
        }

    }

}
