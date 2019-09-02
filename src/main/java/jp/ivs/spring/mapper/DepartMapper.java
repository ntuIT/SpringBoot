package jp.ivs.spring.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;

import jp.ivs.spring.model.admin.Department;

public interface DepartMapper
{
    @Select(" SELECT * FROM departs ")
    List<Department> getAllDept();
}
