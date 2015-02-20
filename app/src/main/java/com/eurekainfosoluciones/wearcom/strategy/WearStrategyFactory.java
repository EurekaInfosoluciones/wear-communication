package com.eurekainfosoluciones.wearcom.strategy;

import com.eurekainfosoluciones.wearcom.service.ActionType;

public class WearStrategyFactory {

    public WearStrategy getWearStrategy(ActionType actionType) {
        switch (actionType) {
            case SEND_MESSAGE:
                return new MessageWearStrategy();
            case PUT_DATA_REQUEST:
                return new PutDataRequesterStrategy();
        }
        throw new IllegalArgumentException("ActionType not supported");
    }
}
