package com.campus.mapper;

import org.apache.ibatis.annotations.*;
import com.campus.pojo.Location;

import java.util.List;

@Mapper
public interface LocationMapper {

    @Insert("INSERT INTO locations (LocationCode, LocationName, Status) VALUES (#{locationCode}, #{locationName}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "locationCode") // 假设LocationCode是自增的，这里做相应配置
    int insertLocation(Location location);

    @Select("SELECT * FROM locations WHERE LocationCode = #{locationCode}")
    Location selectLocationByCode(String locationCode);

    @Update("UPDATE locations SET LocationName = #{locationName}, Status = #{status} WHERE LocationCode = #{locationCode}")
    Boolean updateLocation(Location location);

    @Delete("DELETE FROM locations WHERE LocationCode = #{locationCode}")
    Boolean deleteLocation(String locationCode);

    @Select("SELECT * FROM locations")
    List<Location> getAllLocations();
}