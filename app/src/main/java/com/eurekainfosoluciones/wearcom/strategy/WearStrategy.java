package com.eurekainfosoluciones.wearcom.strategy;

import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public interface WearStrategy {
    Status execute(GoogleApiClient googleApiClient, Intent intent);
}
