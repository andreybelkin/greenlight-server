package com.globalgrupp.greenlight.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey Belkin
 * Date: 02.12.2015
 * Time: 11:22
 */
@Entity
@Table(name="user_channel")
public class UserChannel {
    @Id
    @Column(name="channel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="channel_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="user_channel_street",joinColumns = {
            @JoinColumn(name = "channel_id")},
            inverseJoinColumns = {@JoinColumn(name="street_id")}
    )
    private Set<Street> streets = new HashSet<Street>(0);



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<Street> getStreets() {
        return streets;
    }

    public void setStreets(Set<Street> streets) {
        this.streets = streets;
    }



    public UserChannel() {
    }

    public UserChannel(String name) {
        this.name = name;
    }

    public UserChannel(Set<Street> streets, String name) {
        this.streets = streets;
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
