package com.globalgrupp.greenlight.model;

/**
 * Created by Lenovo on 26.01.2016.
 */
public class EventFilter {
    private Long eventId;

    public Long channelId;

    public EventFilter() {
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
}
