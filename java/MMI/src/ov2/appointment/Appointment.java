package ov2.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Appointment {

	private StringProperty purposeProperty = new SimpleStringProperty();
	private StringProperty roomProperty = new SimpleStringProperty();
	private IntegerProperty repetitionProperty = new SimpleIntegerProperty();

	public Appointment(String purpose) {
		this.setPurpose(purpose);
	}

    public Appointment(String purpose, String room, LocalDate date, LocalTime from, LocalTime to, Integer repetition) {
        this.purposeProperty.set(purpose);
        this.roomProperty.set(room);
        this.setDate(date);
        this.setFrom(from);
        this.setTo(to);
        this.repetitionProperty.set(repetition);
    }

	private Property<LocalDate> dateProperty = new ObjectPropertyBase<LocalDate>(null) {

		@Override
		public Object getBean() {
			return this;
		}

		@Override
		public String getName() {
			return "dato";
		}
	};
	private Property<LocalTime> fromProperty = new ObjectPropertyBase<LocalTime>(null) {

		@Override
		public Object getBean() {
			return this;
		}

		@Override
		public String getName() {
			return "fra";
		}
	};
	private Property<LocalTime> toProperty = new ObjectPropertyBase<LocalTime>(null) {

		@Override
		public Object getBean() {
			return this;
		}

		@Override
		public String getName() {
			return "til";
		}
	};
	private Property<LocalDate> endProperty = new ObjectPropertyBase<LocalDate>(null) {

		@Override
		public Object getBean() {
			return this;
		}

		@Override
		public String getName() {
			return "slutt";
		}
	};

	public String getPurpose() {
		return purposeProperty.getValue();
	}

	public void setPurpose(String formal) {
		purposeProperty.setValue(formal);
	}

	public StringProperty formalProperty() {
		return purposeProperty;
	}

	public String getRoom() {
		return roomProperty.getValue();
	}

	public void setRoom(String rom) {
		roomProperty.setValue(rom);
	}

	public StringProperty roomProperty() {
		return roomProperty;
	}

	public LocalDate getDate() {
		return dateProperty.getValue();
	}

	public void setDate(LocalDate dato) {
		dateProperty.setValue(dato);
	}

	public Property<LocalDate> DateProperty() {
		return dateProperty;
	}

	public LocalTime getFrom() {
		return fromProperty.getValue();
	}

	public void setFrom(LocalTime fra) {
		fromProperty.setValue(fra);
	}

	public Property<LocalTime> fromProperty() {
		return fromProperty;
	}

	public LocalTime getTo() {
		return toProperty.getValue();
	}

	public void setTo(LocalTime til) {
		toProperty.setValue(til);
	}

	public Property<LocalTime> toProperty() {
		return toProperty;
	}

	public Integer getRepetition() {
		return repetitionProperty.getValue();
	}

	public void setRepetition(Integer repetisjon) {
		repetitionProperty.setValue(repetisjon);
	}

	public IntegerProperty repetitionProperty() {
		return repetitionProperty;
	}

	public LocalDate getEnd() {
		return endProperty.getValue();
	}

	public void setEnd(LocalDate end) {
		endProperty.setValue(end);
	}

	public Property<LocalDate> endProperty() {
		return endProperty;
	}

	public String toString() {

        return getPurpose() + " (" + getRoom() + ", " + getDate() + ", " + getFrom() + ", " + getTo() + ", " +
                Controller.repeatChoices.get(getRepetition()) +
                ")";
        //return this.getPurpose() + " (" + getRoom() + ", " + getDate().getDayOfMonth() + "/" + getDate()
        // .getMonthValue() + ")";
    }


}
