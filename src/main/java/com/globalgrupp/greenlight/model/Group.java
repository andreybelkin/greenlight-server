package com.globalgrupp.greenlight.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Lenovo on 25.02.2016.
 */

@Entity
@Table(name="groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="group_type")
    private Long groupType;


    @ManyToMany(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name="group_social_network_user",joinColumns = {
            @JoinColumn(name = "group_id",nullable = false,updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="social_network_user_id",nullable = false,updatable = false)}
    )
//    @Transient
    private List<SocialNetworkUser> socialNetworkUserSet;



    public Group() {
    }

    public List<SocialNetworkUser> getSocialNetworkUserSet() {
        return socialNetworkUserSet;
    }

    public void setSocialNetworkUserSet(List<SocialNetworkUser> socialNetworkUserSet) {
        this.socialNetworkUserSet = socialNetworkUserSet;
    }

    public Long getGroupType() {
        return groupType;
    }

    public void setGroupType(Long groupType) {
        this.groupType = groupType;
    }

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
}
