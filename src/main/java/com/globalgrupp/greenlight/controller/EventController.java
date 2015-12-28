package com.globalgrupp.greenlight.controller;

import com.globalgrupp.greenlight.model.*;
import com.globalgrupp.greenlight.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by п on 21.12.2015.
 */
@RestController
@RequestMapping("/event")
public class EventController {

    @RequestMapping(value="/createEvent",method= RequestMethod.POST)
    boolean createEvent(@RequestBody Event event){
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(event);
        session.getTransaction().commit();
        return true;//
        //session.close();
    }

    @RequestMapping(value="/getNearestEvents",method= RequestMethod.GET)
    List<Event> getEventsByStreet(@RequestBody SimpleGeoCoords coords){
        //todo find events by coords;
        Session session= HibernateUtil.getSessionFactory().openSession();
        Query query= session.createQuery("from Event where ");
        //session.close();
        List<Event> result=query.list();
        return result;
    }

    @RequestMapping(value="/getEventsByChannel",method= RequestMethod.GET)
    List<Event> getEventsByChannels(@RequestBody Channel channel){
        //todo find events by channel;
        Session session= HibernateUtil.getSessionFactory().openSession();
        Query query= session.createQuery("from Event where ");
        //session.close();
        List<Event> result=new ArrayList<>();
        return result;
    }

    @RequestMapping(value="/addComment",method= RequestMethod.POST)
    void addComment(@RequestBody Comment comment){
        comment.setCreateDate(new Date());
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(comment);
        session.getTransaction().commit();
    }

    @RequestMapping(value="/getComments",method= RequestMethod.GET)
    List<Comment> getComments(@RequestBody Event event){
        Session session= HibernateUtil.getSessionFactory().openSession();
        Query query= session.createQuery("from Comment where event_id=:event_id order by create_date asc");
        query.setParameter("event_id",event.getId());
        //session.close();
        List<Comment> result=query.list();
        return result;
    }



}