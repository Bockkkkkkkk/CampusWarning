package com.campus.controller;

import com.campus.common.BaseResponse;
import com.campus.common.ErrorCode;
import com.campus.common.ResultUtils;
import com.campus.pojo.BullyingAlert;
import com.campus.pojo.Location;
import com.campus.pojo.WaningMessage;
import com.campus.service.BullyingAlertService;
import com.campus.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/bullying-alerts")
public class BullyingAlertController {

    private final BullyingAlertService bullyingAlertService;

    @Autowired
    private LocationService locationService;

    @Autowired
    public BullyingAlertController(BullyingAlertService bullyingAlertService) {
        this.bullyingAlertService = bullyingAlertService;
    }

    @PostMapping("/warning")
    public BaseResponse<String> warning(@RequestBody WaningMessage waningMessage) {
        waningMessage.getLocation().setStatus("abnormal");
        BullyingAlert bullyingAlert = new BullyingAlert();
        bullyingAlert.setLocationCode(waningMessage.getLocation().getLocationCode());
        bullyingAlert.setAddress(waningMessage.getLocation().getLocationName());
        bullyingAlert.setVoiceContent(waningMessage.getVoiceMessage());
        bullyingAlert.setStatus("Unprocessed");
        bullyingAlert.setOccurrenceTime(LocalDateTime.now());
        if (locationService.updateLocation(waningMessage.getLocation())) {
            bullyingAlertService.createAlert(bullyingAlert);
            return ResultUtils.success("更新预警信息成功");
        } else {
            return ResultUtils.error(ErrorCode.FAIL, "更新地点信息失败，记录可能不存在");
        }
    }
    // 创建预警信息
    @PostMapping
    public BaseResponse<String> createAlert(@RequestBody BullyingAlert alert) {
        try {
            bullyingAlertService.createAlert(alert);
            return ResultUtils.success("创建预警信息成功");
        } catch (Exception e) {
            return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "创建预警信息失败", e.getMessage());
        }
    }

    // 获取单个预警信息
    @GetMapping("/{id}")
    public BaseResponse<BullyingAlert> getAlertById(@PathVariable Integer id) {
        BullyingAlert alert = bullyingAlertService.getAlertById(id);
        if (alert != null) {
            return ResultUtils.success(alert);
        } else {
            return ResultUtils.error(ErrorCode.FAIL, "预警信息未找到");
        }
    }

    // 根据locationcode获取单个预警信息
    @GetMapping("/getAlertByLocationCode/{locationCode}")
    public BaseResponse<BullyingAlert> getAlertByLocationCode(@PathVariable String locationCode) {
        BullyingAlert alert = bullyingAlertService.getAlertByLocationCode(locationCode);
        if (alert != null) {
            return ResultUtils.success(alert);
        } else {
            return ResultUtils.error(ErrorCode.FAIL, "预警信息未找到");
        }
    }
    // 获取所有预警信息
    @GetMapping
    public BaseResponse<List<BullyingAlert>> getAllAlerts() {
        List<BullyingAlert> alerts = bullyingAlertService.getAllAlerts();
        return ResultUtils.success(alerts);
    }

    // 更新预警信息
    @PutMapping("/{id}")
    public BaseResponse<String> updateAlert(@PathVariable Integer id, @RequestBody BullyingAlert updatedAlert) {
        updatedAlert.setAlertId(id);
        if (bullyingAlertService.updateAlert(updatedAlert)) {
            return ResultUtils.success("更新预警信息成功");
        } else {
            return ResultUtils.error(ErrorCode.FAIL, "更新预警信息失败，记录可能不存在");
        }
    }

    // 删除预警信息
    @DeleteMapping("/{id}")
    public BaseResponse<String> deleteAlert(@PathVariable Integer id) {
        if (bullyingAlertService.deleteAlert(id)) {
            return ResultUtils.success("删除预警信息成功");
        } else {
            return ResultUtils.error(ErrorCode.FAIL, "删除预警信息失败，记录可能不存在");
        }
    }
}