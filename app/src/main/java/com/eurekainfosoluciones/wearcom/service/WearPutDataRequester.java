package com.eurekainfosoluciones.wearcom.service;

import android.content.Context;
import android.content.Intent;
import com.eurekainfosoluciones.wearcom.PutDataRequester;
import com.eurekainfosoluciones.wearcom.model.DataRequest;

public class WearPutDataRequester implements PutDataRequester {

    private final Context context;

    public WearPutDataRequester(Context context) {
        this.context = context;
    }

    @Override
    public void putData(DataRequest request) {
        Intent intent = new Intent(context, WearComIntentService.class);
        intent.putExtra(WearComIntentService.EXTRA_ACTION_TYPE, ActionType.PUT_DATA_REQUEST);
        intent.putExtra(WearComIntentService.EXTRA_DATA, request.getData());
        intent.putExtra(WearComIntentService.EXTRA_ACTION_TO_NOTIFY, request.getActionToBeNotified());
        context.startService(intent);
    }

}
