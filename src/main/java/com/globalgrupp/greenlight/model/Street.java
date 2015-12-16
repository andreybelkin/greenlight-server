package com.globalgrupp.greenlight.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
@Table(name="street")
public class Street {
    @Id
    @Column(name="street_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="street_name")
    private String name;

    @ManyToMany(mappedBy = "streets")
    @JsonBackReference
    private Set<Channel> channels=new HashSet<Channel>(0);


    @ManyToMany(mappedBy = "streets")
    @JsonBackReference
    private Set<UserChannel> userChannels=new HashSet<UserChannel>(0);

    public Set<Channel> getChannels() {
        return channels;
    }

    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Street() {
    }

    public Street(String name) {
        this.name = name;
    }

    public Street(String name, Set<Channel> channels) {
        this.name = name;
        this.channels = channels;
    }
}
