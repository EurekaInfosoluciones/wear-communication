package com.eurekainfosoluciones.wearcom.model;

public class MessageRequest {

    private final String path;
    private final String body;
    private final String actionToBeNotified;

    public String getPath() {
        return path;
    }

    public String getBody() {
        return body;
    }

    public String getActionToBeNotified() {
        return actionToBeNotified;
    }

    private MessageRequest(Builder builder) {
        this.path = builder.path;
        this.body = builder.body;
        this.actionToBeNotified = builder.actionToBeNotified;
    }

    public static class Builder {
        private String path;
        private String body;
        private String actionToBeNotified;

        public static Builder messageRequest() {
            return new Builder();
        }

        public Builder withPath(String path) {
            this.path = path;
            return this;
        }

        public Builder withBody(String body) {
            this.body = body;
            return this;
        }

        public Builder withActionToBeNotified(String actionToBeNotified) {
            this.actionToBeNotified = actionToBeNotified;
            return this;
        }

        public MessageRequest build() {
            return new MessageRequest(this);
        }
    }
}
