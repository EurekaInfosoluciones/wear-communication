package com.eurekainfosoluciones.wearcom.model;

import com.google.android.gms.wearable.PutDataRequest;

public class DataRequest {

    private final PutDataRequest data;
    private final String actionToBeNotified;

    public PutDataRequest getData() {
        return data;
    }

    public String getActionToBeNotified() {
        return actionToBeNotified;
    }

    private DataRequest(Builder builder) {
        this.data = builder.putDataRequest;
        this.actionToBeNotified = builder.actionToBeNotified;
    }

    public static class Builder {
        private PutDataRequest putDataRequest;
        private String actionToBeNotified;

        public Builder withPutDataRequest(PutDataRequest putDataRequest) {
            this.putDataRequest = putDataRequest;
            return this;
        }

        public Builder withActionToBeNotified(String actionToBeNotified) {
            this.actionToBeNotified = actionToBeNotified;
            return this;
        }

        public DataRequest build() {
            return new DataRequest(this);
        }
    }
}
