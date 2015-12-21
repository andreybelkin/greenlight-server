package com.globalgrupp.greenlight.controller;

import com.globalgrupp.greenlight.model.Channel;
import com.globalgrupp.greenlight.model.Event;
import com.globalgrupp.greenlight.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by п on 21.12.2015.
 */
@RestController
@RequestMapping("/event")
public class EventController {
    @RequestMapping(value="/createEvent",method= RequestMethod.POST)
    void createEvent(Event event){
        Session session=HibernateUtil.getSessionFactory().getCurrentSession();
        session.save(event);
        session.close();
    }
}
