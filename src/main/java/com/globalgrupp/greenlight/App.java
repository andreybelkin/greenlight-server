package com.globalgrupp.greenlight;

/**
 * Created with IntelliJ IDEA.
 * User: Andrey Belkin
 * Date: 02.12.2015
 * Time: 10:47
 */


import com.globalgrupp.greenlight.model.Channel;
import com.globalgrupp.greenlight.model.Street;
import com.globalgrupp.greenlight.model.User;
import com.globalgrupp.greenlight.model.UserChannel;
import com.globalgrupp.greenlight.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;


@Configuration
@ComponentScan
@EnableAutoConfiguration
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);



//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        session.beginTransaction();
//        UserChannel channel = new UserChannel("aaaaaa");
//        Street category2= new Street("qqqqqqqq");
//
//        Set<Street> categories1 = new HashSet<Street>();
//        categories1.add(category2);
//        channel.setStreets(categories1);
//
//        User user=new User();
//        Set<UserChannel> sus= new HashSet<UserChannel>();
//
//        sus.add(channel);
//        //channel.setUser(user);
//        //user.setUserChannels(sus);
//
//        session.save(user);
//        channel.setUser(user);
//        session.save(channel);
//        session.getTransaction().commit();
//        System.out.println("Done");
    }

}
