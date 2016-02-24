package com.globalgrupp.greenlight.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey Belkin
 * Date: 02.12.2015
 * Time: 11:21
 */
@Entity
@Table(name="event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private Long id;

    @Column(name="message")
    private String message;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "event")
    @OrderBy("create_date DESC")
    private Set<Comment> comments=new HashSet<Comment>(0);


    @Column(name="longitude")
    private double longitude;

    @Column(name="latitude")
    private double latitude;

    @Column(name="altitude")
    private double altitude;

    @Column(name="audio_id")
    private Long audioId;

    @Column(name="photo_id")
    private Long photoId;

    @Column(name="video_id")
    private Long videoId;

    @ManyToOne()
    @JoinColumn(name="first_street_id")
    private Street firstStreet;

    @Transient
    private String streetName;

    public String getUniqueGUID() {
        return uniqueGUID;
    }

    public void setUniqueGUID(String uniqueGUID) {
        this.uniqueGUID = uniqueGUID;
    }

    @Column(name="unique_guid")
    private String uniqueGUID;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Transient
    private String senderAppId;

    public String getSenderAppId() {
        return senderAppId;
    }

    public void setSenderAppId(String senderAppId) {
        this.senderAppId = senderAppId;
    }

    @ManyToOne()
    @JoinColumn(name="second_street_id")
    private Street secondStreet;

    public Street getFirstStreet() {
        return firstStreet;
    }

    public void setFirstStreet(Street firstStreet) {
        this.firstStreet = firstStreet;
    }

    public Street getSecondStreet() {
        return secondStreet;
    }

    public void setSecondStreet(Street secondStreet) {
        this.secondStreet = secondStreet;
    }

    @Column(name="create_date")
    private Date createDate;


    @ElementCollection
    @CollectionTable(name="event_photo", joinColumns=@JoinColumn(name="event_id"))
    @Column(name="photo_id")
    private List<Long> photoIds;


    @Column(name="social_type")
    private Long socialType;

    @Column(name="user_name")
    private String userName;

    @Column(name="deleted")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getSocialType() {
        return socialType;
    }

    public void setSocialType(Long socialType) {
        this.socialType = socialType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Long> getPhotoIds() {
        return photoIds;
    }

    public void setPhotoIds(List<Long> photoIds) {
        this.photoIds = photoIds;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getPhotoId() {
        return photoId;

    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getAudioId() {
        return audioId;
    }

    public void setAudioId(Long audioId) {
        this.audioId = audioId;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
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


    public Event() {
    }
}
