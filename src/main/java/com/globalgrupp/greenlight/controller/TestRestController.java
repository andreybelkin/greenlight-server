package com.globalgrupp.greenlight.controller;

import com.globalgrupp.greenlight.model.Channel;
import com.globalgrupp.greenlight.model.Street;
import com.globalgrupp.greenlight.model.User;
import com.globalgrupp.greenlight.util.HibernateUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by poplygin on 15.12.15.
 */
@RestController
@RequestMapping("/test")
public class TestRestController {

    @RequestMapping(value="/testMethod",method= RequestMethod.POST)
    Collection<String> getSmth(){
        Collection<String> res=new ArrayList<>(2);
        res.add("adsfadf");
        res.add("adsfadfasdfadf");
        return res;
    }
    @RequestMapping(value="/getChannel",method= RequestMethod.GET)
    Channel getChannel(){
        Channel res=(Channel) HibernateUtil.getSessionFactory().openSession().get(Channel.class,new Long(2));

        return res;
    }
    @RequestMapping(value="/getStreet",method= RequestMethod.GET)
    Street getStreet(){
        Street res=(Street) HibernateUtil.getSessionFactory().openSession().get(Street.class,new Long(2));

        return res;
    }
    @RequestMapping(value="/getUser",method= RequestMethod.GET)
    User getUser(){
        User res=(User) HibernateUtil.getSessionFactory().openSession().get(User.class,new Long(8));

        return res;
    }
}
