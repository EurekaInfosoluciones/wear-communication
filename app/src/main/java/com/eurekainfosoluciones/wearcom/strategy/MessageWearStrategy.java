package com.eurekainfosoluciones.wearcom.strategy;

import android.content.Intent;
import com.eurekainfosoluciones.wearcom.service.WearComIntentService;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageWearStrategy implements WearStrategy {
    private static final Logger logger = LoggerFactory.getLogger(MessageWearStrategy.class.getSimpleName());


    @Override
    public Status execute(GoogleApiClient googleApiClient, Intent intent) {
        List<Node> nodes = Wearable.NodeApi.getConnectedNodes(googleApiClient).await().getNodes();
        if (nodes != null && !nodes.isEmpty()) {
            String node = nodes.get(0).getId();
            String messageBody = intent.getStringExtra(WearComIntentService.EXTRA_DATA);
            byte[] message = messageBody == null ? null : messageBody.getBytes();
            MessageApi.SendMessageResult sendMessageResult = Wearable.MessageApi.sendMessage(googleApiClient, node, intent.getAction(), message).await();
            return sendMessageResult.getStatus();
        } else {
            logger.error("Falied to deliver message - No nodes found");
            return new Status(CommonStatusCodes.ERROR);
        }
    }
}
