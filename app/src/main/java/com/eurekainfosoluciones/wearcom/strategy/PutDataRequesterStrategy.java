package com.eurekainfosoluciones.wearcom.strategy;

import android.content.Intent;
import com.eurekainfosoluciones.wearcom.service.WearComIntentService;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;

public class PutDataRequesterStrategy implements WearStrategy {
    @Override
    public Status execute(GoogleApiClient googleApiClient, Intent intent) {
        PutDataRequest dataRequest = intent.getParcelableExtra(WearComIntentService.EXTRA_DATA);
        DataApi.DataItemResult dataItem = Wearable.DataApi.putDataItem(googleApiClient, dataRequest).await();
        return dataItem.getStatus();
    }
}
