package com.eurekainfosoluciones.wearcom.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.eurekainfosoluciones.wearcom.constants.Constants;
import com.eurekainfosoluciones.wearcom.strategy.WearStrategy;
import com.eurekainfosoluciones.wearcom.strategy.WearStrategyFactory;
import com.eurekainfosoluciones.wearcom.util.StringUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.Wearable;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.eurekainfosoluciones.wearcom.constants.Constants.CONNECTION_TIME_OUT_MS;

public class WearComIntentService extends IntentService {

    public static final String EXTRA_DATA = "extraData";
    public static final String EXTRA_ACTION_TO_NOTIFY = "extraActionToNotify";
    public static final String EXTRA_ACTION_TYPE = "extraWearAction";

    private static final Logger logger = LoggerFactory.getLogger(WearComIntentService.class);

    private final WearStrategyFactory wearStrategyFactory = new WearStrategyFactory();

    private GoogleApiClient googleApiClient;
    private LocalBroadcastManager localBroadcastManager;


    public WearComIntentService() {
        super(WearComIntentService.class.getSimpleName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        googleApiClient = new GoogleApiClient.Builder(this).addApi(Wearable.API).build();
        localBroadcastManager = LocalBroadcastManager.getInstance(WearComIntentService.this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        googleApiClient.blockingConnect(CONNECTION_TIME_OUT_MS, TimeUnit.MILLISECONDS);
        if (googleApiClient.isConnected()) {
            ActionType actionType = (ActionType) intent.getSerializableExtra(EXTRA_ACTION_TYPE);
            WearStrategy wearStrategy = wearStrategyFactory.getWearStrategy(actionType);
            Status status = wearStrategy.execute(googleApiClient, intent);
            logger.debug("{} completed successfully {}", actionType, status.isSuccess());
            sendBroadcastIfNeeded(intent.getStringExtra(EXTRA_ACTION_TO_NOTIFY), status);
            googleApiClient.disconnect();
        } else {
            logger.error("Failed to deliver Message - Client disconnected from Google Play Services");
        }
    }

    private void sendBroadcastIfNeeded(String actionToNotify, Status status) {
        if (StringUtil.isNotEmptyOrNull(actionToNotify)) {
            Intent intentToNotify = new Intent(actionToNotify);
            intentToNotify.putExtra(Constants.EXTRA_IS_SUCCESS, status.isSuccess());
            localBroadcastManager.sendBroadcast(intentToNotify);
        }
    }
}
