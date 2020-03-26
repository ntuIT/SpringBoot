package jp.ivs.spring.mapper;

import jp.ivs.spring.model.admin.Record;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface RecordMapper
{
    @Select("select * from records")
    List<Record> getAllRecords();

    @Select("select * from records where records.Id = #{recordNo} ")
    Record getRecordByNo(int recordNo);
}
