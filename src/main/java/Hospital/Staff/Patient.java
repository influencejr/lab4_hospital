package Hospital.Staff;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Patient{

    private Long id;
    private User user;
    private String role;
    private String description;
    private int appointment_day;
    private int appointment_month;
    private int appointment_hour;
    private int appointment_minute;

    public Patient createUser(Long id,
                                    User user,
                                    String role,
                                    String description,
                                    int appointment_day,
                                    int appointment_month,
                                    int appointment_hour,
                                    int appointment_minute) {
        return new Patient(id, user, role, description, appointment_day, appointment_month, appointment_hour, appointment_minute);
    }

    @Override
    public String toString() {
        return "\nID: " + getId() +
                "\nFirst name: " + user.getFirstName() +
                "\nLast name: " + user.getLastName() +
                "\nRole: " + getRole() +
                "\nDescription: " + getDescription() +
                "\nAppointment day, month, hour, minute: " + getAppointment_day() + ", " + getAppointment_month() + ", " + getAppointment_hour() + ":" + getAppointment_minute();
    }

}
