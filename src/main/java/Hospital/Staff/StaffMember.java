package Hospital.Staff;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StaffMember {

    private Integer id;
    private User user;
    private String role;
    private String description;

    public StaffMember createUser(Integer id,
                                    User user,
                                    String role,
                                    String description) {
        return new StaffMember(id, user, role, description);
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
