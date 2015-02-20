package com.eurekainfosoluciones.wearcom.service;

import android.content.Context;
import android.content.Intent;
import com.eurekainfosoluciones.wearcom.MessageSender;
import com.eurekainfosoluciones.wearcom.model.MessageRequest;

public class WearMessageSender implements MessageSender {

    private final Context context;

    public WearMessageSender(Context context) {
        this.context = context;
    }

    @Override
    public void sendMessage(MessageRequest message) {
        Intent intent = new Intent(context, WearComIntentService.class);
        intent.putExtra(WearComIntentService.EXTRA_ACTION_TYPE, ActionType.SEND_MESSAGE);
        intent.putExtra(WearComIntentService.EXTRA_DATA, message.getBody());
        intent.putExtra(WearComIntentService.EXTRA_ACTION_TO_NOTIFY, message.getActionToBeNotified());
        context.startService(intent);
    }

}
