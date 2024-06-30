package com.campus.controller;
import com.campus.common.BaseResponse;
import com.campus.common.ErrorCode;
import com.campus.common.ResultUtils;

import com.campus.pojo.Location;
import com.campus.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    // 创建地点信息
    @PostMapping
    public BaseResponse<String> createLocation(@RequestBody Location location) {
        try {
            locationService.addLocation(location);
            return ResultUtils.success("创建地点信息成功");
        } catch (Exception e) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "创建地点信息失败", e.getMessage());
        }
    }

    // 获取单个地点信息
    @GetMapping("/{code}")
    public BaseResponse<Location> getLocationByCode(@PathVariable String code) {
        Location location = locationService.getLocationByCode(code);
        if (location != null) {
            return ResultUtils.success(location);
        } else {
            return ResultUtils.error(ErrorCode.FAIL, "地点信息未找到");
        }
    }

    // 获取所有地点信息
    @GetMapping
    public BaseResponse<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return ResultUtils.success(locations);
    }

    // 更新地点信息
    @PutMapping
    public BaseResponse<String> updateLocation(@RequestBody Location updatedLocation) {
        if (locationService.updateLocation(updatedLocation)) {
            return ResultUtils.success("更新地点信息成功");
        } else {
            return ResultUtils.error(ErrorCode.FAIL, "更新地点信息失败，记录可能不存在");
        }
    }



    // 删除地点信息
    @DeleteMapping("/{code}")
    public BaseResponse<String> deleteLocation(@PathVariable String code) {
        if (locationService.deleteLocation(code)) {
            return ResultUtils.success("删除地点信息成功");
        } else {
            return ResultUtils.error(ErrorCode.FAIL, "删除地点信息失败，记录可能不存在");
        }
    }
}
