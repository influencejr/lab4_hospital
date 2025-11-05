package Hospital.Staff;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class StaffMember {

    private Long id;
    private User user;
    private String role;
    private String description;

    public StaffMember createUser(Long id,
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
