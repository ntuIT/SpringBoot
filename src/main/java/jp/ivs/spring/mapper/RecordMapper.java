package jp.ivs.spring.mapper;

import jp.ivs.spring.model.RecordDetail;
import jp.ivs.spring.model.admin.Record;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface RecordMapper
{   //tất cả các query đều sort kết quả theo ngày
    @Select("select * from recorddetails ORDER BY Date DESC")
    List<RecordDetail> getAllRecords();
    @Select("SELECT * FROM recorddetails ORDER BY departNo ASC, Date DESC")
    List<RecordDetail> getAllSortByDept(); //lấy hết, sort theo DeptNo

    @Select("select * from recorddetails where RecordNo = #{recordNo} ")
    RecordDetail get1Record(int recordNo); //chỉ lấy 1 bản

    public static final String NORMAL = " not(Type is null) "; //điều kiện cơ bản lọc ra những record không bị lỗi
    @Select("SELECT * FROM recorddetails WHERE " +NORMAL+ " AND DepartNo = #{deptNo} ORDER BY Date DESC;")
    List<RecordDetail> getRecordsByDept(int deptNo); //lấy những bản có chung Dept
    @Select("SELECT * FROM recorddetails where " +NORMAL+ " AND DepartNo = #{deptNo} ORDER BY Date DESC limit 10")
    List<RecordDetail> get10SortByDept(int deptNo); //lấy 10 bản đầu tiên cùng Dept

    @Select("SELECT * FROM recorddetails WHERE " +NORMAL+ " AND StaffNo = #{staffNo} ORDER BY departNo ASC, Date DESC")
    List<RecordDetail> getRecordsByStaff(int staffNo); //lấy những bản có chung staff


    //region chức năng thêm xóa sửa
    public final String INSERT_COMMON = " INSERT INTO bookstore.records (Type, Reason, Date, StaffId) VALUES ";
    @Update(INSERT_COMMON + " (#{type}, #{reason}, #{date}, #{staffId}); ")
    boolean addRecord(Record record);

    public final String UPDATE_COMMON = " UPDATE bookstore.records t SET ";
    public final String TYPE_UPDATE = " t.Type = #{type} ";
    public final String REASON_UPDATE = " t.Reason = #{reason} ";
    public final String DATE_UPDATE = " t.Date = #{date} ";
    public final String STAFF_UPDATE = " t.StaffId = #{staffId} ";
    public final String FULL_UPDATE = TYPE_UPDATE + " , " + REASON_UPDATE + " , " + DATE_UPDATE + " , " + STAFF_UPDATE;
    public final String UPDATE_CONDITION = " WHERE t.Id = #{Id}; ";
    @Update(UPDATE_COMMON + FULL_UPDATE + UPDATE_CONDITION)
    boolean editRecord(Record record);

    @Delete("DELETE FROM bookstore.records WHERE Id = #{recordNo}")
    boolean dropRecord(int recordNo);
    //endregion thêm xóa sửa
}
