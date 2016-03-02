package com.globalgrupp.greenlight.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey Belkin
 * Date: 02.12.2015
 * Time: 11:22
 */

@Entity
@Table(name="comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name="user_id")
    @JsonBackReference(value = "user")
    private User user;

    @ManyToOne()
    @JoinColumn(name="event_id")
    @JsonBackReference(value = "event")
    private Event event;

    @Column(name="message")
    private String message;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="audio_id")
    private Long audioId;

    public Long getAudioId() {
        return audioId;
    }

    public void setAudioId(Long audioId) {
        this.audioId = audioId;
    }

    public List<Long> getPhotoIds() {
        return photoIds;
    }

    public void setPhotoIds(List<Long> photoIds) {
        this.photoIds = photoIds;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    @ElementCollection
    @CollectionTable(name="comment_photo", joinColumns=@JoinColumn(name="comment_id"))
    @Column(name="photo_id")
    private List<Long> photoIds;

    @Column(name="video_id")
    private Long videoId;

    @Column(name="social_type")
    private Long socialType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getSocialType() {
        return socialType;
    }

    public void setSocialType(Long socialType) {
        this.socialType = socialType;
    }

    @Column(name="user_name")
    private String userName;




    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Comment() {
    }

    public Event getEvent() {

        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
