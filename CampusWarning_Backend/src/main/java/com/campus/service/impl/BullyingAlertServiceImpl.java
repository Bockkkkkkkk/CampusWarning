package com.campus.service.impl;

import com.campus.mapper.BullyingAlertMapper;
import com.campus.pojo.BullyingAlert;
import com.campus.service.BullyingAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BullyingAlertServiceImpl implements BullyingAlertService {

    @Autowired
    private BullyingAlertMapper bullyingAlertMapper;

    @Override
    public void createAlert(BullyingAlert alert) {
        bullyingAlertMapper.insertAlert(alert);
    }

    @Override
    public BullyingAlert getAlertById(Integer id) {
        return bullyingAlertMapper.selectAlertById(id);
    }

    @Override
    public List<BullyingAlert> getAllAlerts() {
        return bullyingAlertMapper.selectAllAlerts();
    }

    @Override
    public Boolean updateAlert(BullyingAlert alert) {
        return bullyingAlertMapper.updateAlert(alert);
    }

    @Override
    public Boolean updateAlertByCode(BullyingAlert alert) {
        return bullyingAlertMapper.updateAlertByCode(alert);
    }

    @Override
    public Boolean deleteAlert(Integer id) {
        return  bullyingAlertMapper.deleteAlert(id);
    }

    @Override
    public BullyingAlert getAlertByLocationCode(String locationCode) {
        return bullyingAlertMapper.selectAlertByLocationCode(locationCode);
    }
}
