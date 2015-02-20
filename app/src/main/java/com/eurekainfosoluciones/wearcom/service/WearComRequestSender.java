package com.eurekainfosoluciones.wearcom.service;

import android.content.Context;
import android.content.Intent;
import com.eurekainfosoluciones.wearcom.RequestSender;
import com.eurekainfosoluciones.wearcom.model.DataRequest;
import com.eurekainfosoluciones.wearcom.model.MessageRequest;

public class WearComRequestSender implements RequestSender {

    private final Context context;

    public WearComRequestSender(Context context) {
        this.context = context;
    }

    @Override
    public void sendMessage(MessageRequest message) {
        Intent intent = generateIntentToStartService(ActionType.SEND_MESSAGE, message.getActionToBeNotified());
        intent.putExtra(WearComIntentService.EXTRA_DATA, message.getBody());
        context.startService(intent);
    }

    @Override
    public void putData(DataRequest request) {
        Intent intent = generateIntentToStartService(ActionType.PUT_DATA_REQUEST, request.getActionToBeNotified());
        intent.putExtra(WearComIntentService.EXTRA_DATA, request.getData());
        context.startService(intent);
    }

    private Intent generateIntentToStartService(ActionType actionType, String actionToNotify) {
        Intent intent = new Intent(context, WearComIntentService.class);
        intent.putExtra(WearComIntentService.EXTRA_ACTION_TYPE, actionType);
        intent.putExtra(WearComIntentService.EXTRA_ACTION_TO_NOTIFY, actionToNotify);
        return intent;
    }
}
