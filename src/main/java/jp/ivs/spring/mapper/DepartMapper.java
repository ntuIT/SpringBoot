package jp.ivs.spring.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

import jp.ivs.spring.model.admin.Department;
import org.apache.ibatis.annotations.Update;

public interface DepartMapper
{
    @Select(" SELECT * FROM Departs ")
    List<Department> getAllDept();
    @Update(" UPDATE Depart SET Name=#{name} WHERE Id=#{id}; ")
    boolean editDeptName(Department department);
    @Insert(" INSERT INTO Depart(Id, Name) VALUES(#{id} , #{name}); ")
    boolean addDeptName(Department department);
}
