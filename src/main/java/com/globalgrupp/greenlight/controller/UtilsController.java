package com.globalgrupp.greenlight.controller;

import com.globalgrupp.greenlight.model.LoadedFile;
import com.globalgrupp.greenlight.model.User;
import com.globalgrupp.greenlight.util.HibernateUtil;
import org.apache.commons.io.IOUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by п on 31.12.2015.
 */
@RestController
@RequestMapping("/utils")
public class UtilsController {

//    @RequestMapping(value="/uploadFile", method= RequestMethod.POST)
//    public @ResponseBody  String handleFileUpload( @RequestParam("file") MultipartFile file){
@RequestMapping(value="/uploadFile", method=RequestMethod.PUT)
@ResponseBody
public Long convert(InputStream file){
            try {
                byte[] bytes = IOUtils.toByteArray(file);//new byte[1024];
                LoadedFile lf =new LoadedFile();
                lf.setData(bytes);
                Session session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(lf);
                session.getTransaction().commit();
                return lf.getId();
            } catch (Exception e) {
                return null;
            }
    }

    @RequestMapping(value = "/getFile/{file_name}", method = RequestMethod.GET)
    public void getFile(
            @PathVariable("file_name") Long id,
            HttpServletResponse response) {
        try {
            // get your file as InputStream
            Session session= HibernateUtil.getSessionFactory().openSession();
            LoadedFile lf=(LoadedFile)session.get(LoadedFile.class,id);
            InputStream is = new ByteArrayInputStream(lf.getData());
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            //log.info("Error writing file to output stream. Filename was '{}'", fileName, ex);
            throw new RuntimeException("IOError writing file to output stream");
        }

    }


    @RequestMapping(value="/savePushAppId/{appId}", method=RequestMethod.POST)
    public void savePushAppId(@PathVariable("appId") String pushAppId){
        Session session= HibernateUtil.getSessionFactory().openSession();
        Query query=session.createQuery("from User where push_app_id=:pushAppId");
        query.setParameter("pushAppId",pushAppId);
        List<User> users=query.list();
        User fUser;
        if (users.size()==1){
            fUser=users.get(0);
            //такой ключ уже есть
        } else {
            fUser=new User();
            fUser.setPushAppId(pushAppId);
            session.beginTransaction();
            session.save(fUser);
            session.getTransaction().commit();
        }

    }
}
