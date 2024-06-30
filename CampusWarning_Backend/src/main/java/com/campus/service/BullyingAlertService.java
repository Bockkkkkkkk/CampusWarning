package com.campus.service;

import com.campus.pojo.BullyingAlert;

import java.util.List;

public interface BullyingAlertService {
    void createAlert(BullyingAlert alert);
    BullyingAlert getAlertById(Integer id);
    List<BullyingAlert> getAllAlerts();
    Boolean updateAlert(BullyingAlert alert);
    Boolean updateAlertByCode(BullyingAlert alert);
    Boolean deleteAlert(Integer id);
    BullyingAlert getAlertByLocationCode(String locationCode);
}
