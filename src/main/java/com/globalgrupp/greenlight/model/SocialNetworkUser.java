package com.globalgrupp.greenlight.model;



import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Lenovo on 25.02.2016.
 */
@Entity
@Table(name="social_network_user")
public class SocialNetworkUser {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="social_network_user_id")
    private Long socialNetworkuserId;

    @Column(name = "social_network_id")
    private Long socialNetworkId;

    @Column(name="user_name")
    private String userName;

    @ManyToMany(mappedBy = "socialNetworkUserSet")
    @JsonBackReference(value="groups")
//    @Transient
    private Set<Group> groups=new HashSet<Group>(0);



    public SocialNetworkUser() {
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSocialNetworkId() {
        return socialNetworkId;
    }

    public void setSocialNetworkId(Long socialNetworkId) {
        this.socialNetworkId = socialNetworkId;
    }

    public Long getSocialNetworkuserId() {
        return socialNetworkuserId;
    }

    public void setSocialNetworkuserId(Long socialNetworkuserId) {
        this.socialNetworkuserId = socialNetworkuserId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
