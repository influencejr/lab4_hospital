package Hospital.DataBase.DAO;

import Hospital.Staff.StaffMember;

import java.util.List;

public interface StaffMemberDAO extends GeneralDAO<StaffMember> {

    List<StaffMember> findAll();
}
