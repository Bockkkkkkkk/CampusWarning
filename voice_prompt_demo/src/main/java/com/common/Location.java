package com.common;


import lombok.Data;

@Data
public class Location {
    private String locationCode;
    private String locationName;
    private String status;

    public Location(String locationCode, String locationName, String status) {
        this.locationCode = locationCode;
        this.locationName = locationName;
        this.status = status;
    }
}