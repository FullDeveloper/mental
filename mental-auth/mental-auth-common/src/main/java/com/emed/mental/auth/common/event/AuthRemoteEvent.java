package com.emed.mental.auth.common.event;

/*
import org.springframework.cloud.bus.event.RemoteApplicationEvent;
*/

import java.util.List;

/**
 * @Author: 周润斌
 * @Date: create in 上午 10:17 2018/2/2 0002
 * @Description: RemoteApplicationEvent 被传输的消息
 */
public class AuthRemoteEvent /*extends RemoteApplicationEvent*/ {

    private List<String> allowedClient;

    //jackson序列化反序列化必须有无参构造函数
    public AuthRemoteEvent() {
    }

    /*public AuthRemoteEvent(Object source, String originService, String destinationService, List<String> allowedClient) {
        // source is the object that is publishing the event
        // originService is the unique context ID of the publisher
        super(source, originService, destinationService);
        this.allowedClient = allowedClient;
    }*/

    public List<String> getAllowedClient() {
        return allowedClient;
    }

    public void setAllowedClient(List<String> allowedClient) {
        this.allowedClient = allowedClient;
    }
}
