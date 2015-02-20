package com.eurekainfosoluciones.wearcom;

import com.eurekainfosoluciones.wearcom.model.DataRequest;
import com.eurekainfosoluciones.wearcom.model.MessageRequest;

public interface RequestSender {

    void sendMessage(MessageRequest message);
    void putData(DataRequest request);

}
