package jp.ivs.spring.mapper;

import jp.ivs.spring.model.RecordDetail;
import jp.ivs.spring.model.admin.Record;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface RecordMapper
{
    @Select("select * from recorddetails ORDER BY Date DESC")
    List<RecordDetail> getAllRecords();
    @Select("SELECT * FROM recorddetails ORDER BY departNo ASC, Date DESC")
    List<RecordDetail> getAllSortByDept();

    @Select("select * from recorddetails where RecordNo = #{recordNo} ")
    RecordDetail get1Record(int recordNo);

    @Select("SELECT * FROM recorddetails WHERE StaffNo = #{staffNo}")
    List<RecordDetail> getRecordsByStaff(int staffNo);

    @Select("SELECT * FROM recorddetails WHERE DepartNo = #{deptNo}")
    List<RecordDetail> getRecordsByDept(int deptNo);

    public final String INSERT_COMMON = " INSERT INTO bookstore.records (Type, Reason, Date, StaffId) VALUES ";
    @Update(INSERT_COMMON + " (true, 'test', '2020-03-29', 1); ")
    boolean addRecord(Record record);

    public final String UPDATE_COMMON = " UPDATE bookstore.records t SET ";
    public final String FULL_UPDATE = " t.Type = false, t.Reason = 'test ', t.Date = '2020-03-28', t.StaffId = 2 ";
    public final String TYPE_UPDATE = "";
    public final String REASON_UPDATE = "";
    public final String DATE_UPDATE = "";
    public final String STAFF_UPDATE = "";
    public final String UPDATE_CONDITION = " WHERE t.Id = #{record.recordNo}; ";
    @Update(UPDATE_COMMON + FULL_UPDATE + ";")
    boolean editRecord(Record record);

    @Delete("DELETE FROM bookstore.records WHERE Id = #{recordNo}")
    boolean dropRecord(int recordNo);
}
