package com.campus.mapper;

import com.campus.pojo.BullyingAlert;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BullyingAlertMapper {

    @Insert("INSERT INTO BullyingAlerts(LocationCode, VoiceContent, OccurrenceTime, Address) VALUES (#{locationCode}, #{voiceContent}, #{occurrenceTime}, #{address})")
    void insertAlert(BullyingAlert alert);

    @Select("SELECT * FROM BullyingAlerts WHERE AlertID = #{id}")
    BullyingAlert selectAlertById(@Param("id") Integer id);

    @Select("SELECT * FROM BullyingAlerts")
    List<BullyingAlert> selectAllAlerts();

    @Update("UPDATE BullyingAlerts SET LocationCode=#{locationCode}, VoiceContent=#{voiceContent}, OccurrenceTime=#{occurrenceTime}, Address=#{address} WHERE AlertID=#{alertId}")
    Boolean updateAlert(BullyingAlert alert);

    @Update("UPDATE BullyingAlerts SET  VoiceContent=#{voiceContent}, OccurrenceTime=#{occurrenceTime}, Address=#{address} WHERE LocationCode=#{locationCode}")
    Boolean updateAlertByCode(BullyingAlert alert);

    @Delete("DELETE FROM BullyingAlerts WHERE AlertID=#{id}")
    Boolean deleteAlert(@Param("id") Integer id);

    @Select("SELECT * FROM BullyingAlerts WHERE LocationCode = #{locationCode} order by ReportedTime desc limit 1")
    BullyingAlert selectAlertByLocationCode(String locationCode);
}
