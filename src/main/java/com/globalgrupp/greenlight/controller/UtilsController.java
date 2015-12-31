package com.globalgrupp.greenlight.controller;

import com.globalgrupp.greenlight.model.LoadedFile;
import com.globalgrupp.greenlight.util.HibernateUtil;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
    String name = "test11";
       // if (!file.isEmpty()) {
            try {
                byte[] bytes = IOUtils.toByteArray(file);//new byte[1024];
                LoadedFile lf =new LoadedFile();
                lf.setData(bytes);
                Session session= HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                session.save(lf);
                session.getTransaction().commit();
                return lf.getId();//
//                BufferedOutputStream stream =
//                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
//                stream.write(bytes);
//                stream.close();

            } catch (Exception e) {
                return null;
            }
//        } else {
//            return "You failed to upload " + name + " because the file was empty.";
//        }
    }

    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public ResponseEntity<InputStream> downloadFile(Long id)
            throws IOException {

        Session session= HibernateUtil.getSessionFactory().openSession();
        LoadedFile lf=(LoadedFile)session.get(LoadedFile.class,id);

        return ResponseEntity
                .ok()
                .contentLength(lf.getData().length)
                .contentType(
                        MediaType.parseMediaType("application/octet-stream"))
                .body(new ByteArrayInputStream(lf.getData()));
    }
}
