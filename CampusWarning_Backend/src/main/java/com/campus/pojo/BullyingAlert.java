package com.campus.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BullyingAlert {
    private Integer alertId;
    private String locationCode;
    private String voiceContent;
    private LocalDateTime occurrenceTime;
    private String address;
    private String status;
    private String disposalDetails;
}