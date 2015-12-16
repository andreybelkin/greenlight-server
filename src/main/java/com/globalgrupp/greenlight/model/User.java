package com.globalgrupp.greenlight.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey Belkin
 * Date: 02.12.2015
 * Time: 11:21
 */
@Entity
@Table(name="user")
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "user")
    private Set<UserChannel> userChannels=new HashSet<UserChannel>(0);

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "user")
    private Set<Event> events=new HashSet<Event>(0);

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "user")
    private Set<Comment> comments=new HashSet<Comment>(0);

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public Set<UserChannel> getUserChannels() {
        return userChannels;
    }

    public void setUserChannels(Set<UserChannel> userChannels) {
        this.userChannels = userChannels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User() {
    }
}
