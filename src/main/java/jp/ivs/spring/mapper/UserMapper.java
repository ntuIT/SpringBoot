package jp.ivs.spring.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import java.util.List;

import jp.ivs.spring.model.admin.UserAdmin;

public interface UserMapper
{
    @Select(" SELECT * FROM Users ")
    List<UserAdmin> getAllUser();
}
