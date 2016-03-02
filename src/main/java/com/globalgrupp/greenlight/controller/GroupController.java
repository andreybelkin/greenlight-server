package com.globalgrupp.greenlight.controller;

import com.globalgrupp.greenlight.model.Group;
import com.globalgrupp.greenlight.model.SocialNetworkUser;
import com.globalgrupp.greenlight.model.UsersFilter;
import com.globalgrupp.greenlight.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Lenovo on 25.02.2016.
 */

@RestController
@RequestMapping("/group")
public class GroupController {

    @RequestMapping(value="/getAllGroups",method= RequestMethod.GET)
    List<Group> getAllGroups(){
        Session session= HibernateUtil.getSessionFactory().openSession();
        Query groupsQuery=session.createQuery("from Group");
        List<Group> result=groupsQuery.list();
        return result;
    }

    @RequestMapping(value = "/editGroup",method=RequestMethod.POST)
    void editGroup(@RequestBody Group group){
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(group);
        session.getTransaction().commit();
    }

    @RequestMapping(value = "/saveUser",method=RequestMethod.POST)
    void saveUser(@RequestBody SocialNetworkUser socialNetworkUser){
        Session session=HibernateUtil.getSessionFactory().openSession();
        Query userQuery=session.createQuery("from SocialNetworkUser where social_network_id=:socialNetworkId and social_network_user_id=:socialNetworkuserId");
        userQuery.setParameter("socialNetworkId",socialNetworkUser.getSocialNetworkId());
        userQuery.setParameter("socialNetworkuserId",socialNetworkUser.getSocialNetworkuserId());
        List<SocialNetworkUser> users=userQuery.list();
        session.beginTransaction();
        if (users.size()==1){//пользователь у нас уже есть, обновим ему имя
            SocialNetworkUser us=users.get(0);
            us.setUserName(socialNetworkUser.getUserName());
            session.save(us);
        }else{//пользователя нет, сохраняем нового
            session.save(socialNetworkUser);
        }
        session.getTransaction().commit();
    }

    @RequestMapping(value = "/getUsers",method = RequestMethod.POST)
    List<SocialNetworkUser> getUsers(@RequestBody UsersFilter filter){
        String queryString="from SocialNetworkUser where user_name like '%:userName%' ";
        String cs="(";
        if (filter.isFbUser()){
            cs+=" social_network_id=2 ";
        }
        if (filter.isVkUser()){
            if (cs.length()>1) cs+=" or ";
            cs+=" social_network_id=1 ";
        }
        if (filter.isTwUser()){
            if (cs.length()>1) cs+=" or ";
            cs+=" social_network_id=3 ";
        }
        cs+=")";
        if (filter.isFbUser()||filter.isTwUser()||filter.isVkUser()){
            queryString+=" and "+cs;
        }

        Session session=HibernateUtil.getSessionFactory().openSession();
        Query usersQuery=session.createQuery(queryString);
        List<SocialNetworkUser> users=usersQuery.list();
        return users;
    }
}
