package com.eurekainfosoluciones.wearcom;

import com.eurekainfosoluciones.wearcom.model.MessageRequest;

public interface MessageSender {

    void sendMessage(MessageRequest message);

}
