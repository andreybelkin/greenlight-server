package com.globalgrupp.greenlight.controller;

import com.globalgrupp.greenlight.model.*;
import com.globalgrupp.greenlight.util.HibernateUtil;
import com.globalgrupp.greenlight.util.SomeUtils;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by п on 21.12.2015.
 */
@RestController
@RequestMapping("/event")
public class EventController {

    @RequestMapping(value="/createEvent",method= RequestMethod.POST)
    boolean createEvent(@RequestBody Event event){
        Session session= HibernateUtil.getSessionFactory().openSession();
        Query findEventQuery=session.createQuery("from Event where unique_guid=:guid");
        findEventQuery.setParameter("guid",event.getUniqueGUID());
        List<Event> eventsByGuid=findEventQuery.list();
        if (eventsByGuid.size()>0){
            return true; //такое сообщение уже есть
        }

        session.beginTransaction();
        if (event.getCreateDate()!=null){
            Date dt=new Date();
            event.setCreateDate(dt);
        }

        String streetName = event.getStreetName();
        if (!StringUtils.isEmpty(streetName)) {
            Query query = session.createQuery("from Street where street_name = :streetName");
            query.setParameter("streetName", streetName);
            List<Street> streets = query.list();
            if (streets.size() == 0) {
                Street newStreet = new Street(streetName);
                session.save(newStreet);
                event.setFirstStreet(newStreet);
            } else {
                event.setFirstStreet(streets.get(0));
            }
        }
        Query ownerUserQuery= session.createQuery("from User where push_app_id=:pushAppId");
        ownerUserQuery.setParameter("pushAppId",event.getSenderAppId());
        List<User> ownerUser=ownerUserQuery.list();
        if (ownerUser.size()>0){
            User owner=ownerUser.get(0);
            event.setUser(owner);
        } else{
            //wtf
            User owner=new User();
            owner.setPushAppId(event.getSenderAppId());
            session.save(owner);
            event.setUser(owner);;
        }

        session.save(event);
        session.getTransaction().commit();
        Query usersQuery= session.createQuery("from User");
        final List<User> users=usersQuery.list();
        Thread thread = new Thread(){
            public void run(){
                try{
                    Sender sender = new Sender("AIzaSyAQMtP-p4i2oV-OlVteDv5sW_l50uxFMiI");
                    Message message = new Message.Builder()
                            .addData("message",event.getMessage())
                            .addData("eventId",event.getId().toString())
                            .addData("senderId",event.getSenderAppId())
                            .build();
                    List<String> usersList=new ArrayList<String>();
                    for(int i=0;i<users.size();i++){
                        String pushAppid=users.get(i).getPushAppId();
                        //не добавляем в рассылку отправителя
                        if (pushAppid!=null && !pushAppid.isEmpty()){// && !pushAppid.equals(event.getSenderAppId())){
                            usersList.add(users.get(i).getPushAppId());
                        }
                    }
                    MulticastResult result = sender.send(message, usersList, 5);

                } catch (Exception e){
                    e.printStackTrace();

                }
            }
        };

        thread.start();
        return true;//
        //session.close();
    }





    @RequestMapping(value="/getNearestEvents",method= RequestMethod.POST)
    List<Event> getEventsByStreet(@RequestBody SimpleGeoCoords coords){
        //todo find events by coords;
        Session session= HibernateUtil.getSessionFactory().openSession();

        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.DAY_OF_YEAR, -1);
        Date dt=cal.getTime();
        Query query= session.createQuery("from Event where create_date>:dt and deleted<>1 order by create_date desc");
        query.setParameter("dt",dt);
//        where longitude-1<=:longitude and longitude+1>:longitude " +
//                " and latitude-1<=:latitude and latitude+1>=:latitude");
//        query.setParameter("longitude",coords.getLongitude());
//        query.setParameter("latitude",coords.getLatitude());
        //session.close();
        List<Event> result=query.list();
//        for (Event res : result) {
//            if (res.getFirstStreet()!=null){
//                res.setStreetName(res.getFirstStreet().getName());
//            }
//
//
//        }
        Iterator<Event> iter = result.iterator();
        while(iter.hasNext()){
            Event res=iter.next();
            if (coords.getRadius()*1000<= SomeUtils.distFrom(coords.getLatitude(),coords.getLongitude(),res.getLatitude(),res.getLongitude())){
                iter.remove();
            }else if (res.getFirstStreet()!=null){
                res.setStreetName(res.getFirstStreet().getName());
            }
        }
        return result;
    }

    @RequestMapping(value="/getEventsByChannel/{channelId}",method= RequestMethod.POST)
    List<Event> getEventsByChannels( @PathVariable("channelId")Long channelId){
        //todo find events by channel;
        Session session= HibernateUtil.getSessionFactory().openSession();
        Channel channel=(Channel)session.get(Channel.class,channelId);

        List<Street> streets = new ArrayList<Street>(channel.getStreets());
//        String str="";
        List<Long> qwerty=new ArrayList<>();
        List<Event> result=new ArrayList<Event>();
        //todo optimize
        Calendar cal = Calendar.getInstance(); // creates calendar
        cal.setTime(new Date()); // sets calendar time/date
        cal.add(Calendar.DAY_OF_YEAR, -1);
        Date dt=cal.getTime();
        for (int i=0;i<streets.size();i++) {
            qwerty.add(streets.get(i).getId());
            Query query= session.createQuery("from Event where create_date>:dt and first_street_id in (:streetList) and deleted<>1  ");
            query.setParameter("streetList",streets.get(i).getId());
            query.setParameter("dt",dt);
            result.addAll(query.list());
        }

        Iterator<Event> iter = result.iterator();
        while(iter.hasNext()){
            Event res=iter.next();
            if (res.getFirstStreet()!=null){
                res.setStreetName(res.getFirstStreet().getName());
            }
        }
        //str=str.substring(0,str.length()-1);

        return result;
    }

    @RequestMapping(value="/addComment",method= RequestMethod.POST)
    void addComment(@RequestBody Comment comment){
        comment.setCreateDate(new Date());
        Session session= HibernateUtil.getSessionFactory().openSession();
        comment.setUser((User)session.get(User.class,new Long(1)));//// TODO: 29.12.2015 нормальный юзер
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

    @RequestMapping(value="/getEvent",method= RequestMethod.POST)
    List<Event> getEvent(@RequestBody EventFilter filter){
        Session session= HibernateUtil.getSessionFactory().openSession();
        String queryStr="from Event where event_id=:event_id";
        if (filter.getChannelId()!=null && !filter.getChannelId().equals(new Long(0))){
            queryStr+=" and first_street_id in (:streetList)";
        }
        List<Event> result=new ArrayList<Event>();
        Query query= session.createQuery(queryStr);
        query.setParameter("event_id",filter.getEventId());
        if (filter.getChannelId()!=null && !filter.getChannelId().equals(new Long(0))){
            Channel channel=(Channel)session.get(Channel.class,filter.getChannelId());
            List<Street> streets = new ArrayList<Street>(channel.getStreets());
            //todo optimize
            for (int i=0;i<streets.size();i++) {
                Query query2= session.createQuery(queryStr);
                query2.setParameter("event_id",filter.getEventId());
                query2.setParameter("streetList",streets.get(i).getId());
                result.addAll(query2.list());
            }
        }else{
            result=query.list();
        }

        for (Event res :  result) {
            if (res.getFirstStreet()!=null){
                res.setStreetName(res.getFirstStreet().getName());
            }
        }

        return result;
    }

    @RequestMapping(value="/delete/{eventId}",method=RequestMethod.POST)
    public void deleteEvent(@PathVariable("eventId") Long eventId ){
        Session session= HibernateUtil.getSessionFactory().openSession();
        Event event=(Event) session.get(Event.class,eventId);
        session.beginTransaction();
        event.setDeleted(true);
        session.save(event);
        session.getTransaction().commit();

    }

}
