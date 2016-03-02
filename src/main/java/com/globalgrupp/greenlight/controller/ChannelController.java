package com.globalgrupp.greenlight.controller;

import com.globalgrupp.greenlight.model.Channel;
import com.globalgrupp.greenlight.model.Street;
import com.globalgrupp.greenlight.model.User;
import com.globalgrupp.greenlight.model.UserChannel;
import com.globalgrupp.greenlight.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by п on 24.12.2015.
 */
@RestController
@RequestMapping("/channel")
public class ChannelController {
    @RequestMapping(value="/editChannel",method= RequestMethod.POST)
    void editChannel(@RequestBody Channel channel){
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(channel);
        session.getTransaction().commit();
    }

    @RequestMapping(value="/getBaseChannels",method= RequestMethod.POST)
    List<Channel> getBaseChannels(@RequestBody User user){
        Session session= HibernateUtil.getSessionFactory().openSession();
        Query query=session.createQuery("from Channel ");
        List<Channel> result=query.list();
        return result;
    }

    @RequestMapping(value="/getUserChannels",method= RequestMethod.POST)
    List<UserChannel> getUserChannels(@RequestBody User user){
        Session session= HibernateUtil.getSessionFactory().openSession();
        Query query=session.createQuery("from UserChannel where user_id=:userId ");
        query.setParameter("userId",user.getId());
        List<UserChannel> result=query.list();
        return result;
    }

    @RequestMapping(value="/getStreets",method= RequestMethod.POST)
    List<Street> getStreets(){
        //todo paging
        Session session= HibernateUtil.getSessionFactory().openSession();
        Query query=session.createQuery("from Street order by street_name asc ");
        List<Street> result=query.list();
        return result;
    }

}
