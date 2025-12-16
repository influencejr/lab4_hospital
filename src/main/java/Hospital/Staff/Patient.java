package Hospital.Staff;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Patient{

    private Integer id;
    private User user;
    private String role;
    private String description;

    public Patient createUser(Integer id,
                                    User user,
                                    String role,
                                    String description) {
        return new Patient(id, user, role, description);
    }

    @Override
    public String toString() {
        return "\nID: " + getId() +
                "\nFirst name: " + user.getFirstName() +
                "\nLast name: " + user.getLastName() +
                "\nRole: " + getRole() +
                "\nDescription: " + getDescription();
    }

}
