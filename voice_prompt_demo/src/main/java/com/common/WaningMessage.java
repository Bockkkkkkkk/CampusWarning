package com.common;

import lombok.Data;

/**
 * ClassName:waningMessage
 * Package:com.campus.pojo
 * Description:
 *
 * @Author: Bock
 * @Create:2024/6/1614:41
 * @Version 1.0
 */
@Data
public class WaningMessage {
    Location location;
    String voiceMessage;

    public WaningMessage(Location location, String voiceMessage) {
        this.location = location;
        this.voiceMessage = voiceMessage;
    }
}
