package jp.ivs.spring.mapper;

import jp.ivs.spring.model.admin.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EmployeeMapper
{
    @Select(" SELECT s.Id , s.Name , s.Gender , s.Birthday , s.Photo , s.DepartId , s.Email , s.Level , s.Phone , s.Salary, s.Level , s.Notes , s.DepartId , d.Name as 'DepartName' FROM staffs s  join departs d on s.DepartId = d.Id; ")
    List<Employee> getAllStaffs();
    @Select(" SELECT s.Id , s.Name , s.Gender , s.Birthday , s.Photo , s.DepartId , s.Email , s.Level , s.Phone , s.Salary, s.Level , s.Notes , s.DepartId , d.Name as 'DepartName' FROM staffs s  join departs d on s.DepartId = d.Id ORDER BY s.DepartId; ")
    List<Employee> getStaffsListForDept();
    @Select(" SELECT * FROM Staffs s WHERE s.Id = #{staffNo} ")
    Employee get1StaffByNo(int staffNo);
    @Delete(" DELETE FROM Staffs WHERE Id = #{staffNo} ")
    boolean drop1StaffByNo(int staffNo);
//    @Update(" UPDATE Staffs() WHERE Id = #{staffNo} ")
//    boolean editStaffsByNo(Employee staff);
//    @Insert(" INSERT INTO Staffs() VALUES (); ")
//    boolean addNewStaff(Employee staff);
}
