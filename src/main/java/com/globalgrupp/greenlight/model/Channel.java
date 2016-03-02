package com.globalgrupp.greenlight.model;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey Belkin
 * Date: 02.12.2015
 * Time: 11:22
 */

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="channel")
public class Channel {
    @Id
    @Column(name="channel_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="channel_name")
    private String name;

    @ManyToMany(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="street_channel",joinColumns = {
            @JoinColumn(name = "channel_id",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="street_id",nullable = false,updatable = false)}
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

    public Channel() {
    }

    public Channel(String name) {
        this.name = name;
    }

    public Channel(String name,Set<Street> streets) {
        this.streets = streets;
        this.name = name;
    }
}
