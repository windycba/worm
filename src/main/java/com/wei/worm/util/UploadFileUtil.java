package com.wei.worm.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Component
public class UploadFileUtil {
    public String uploadFile(HttpServletRequest request){
        String path=null;
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        if(multipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            Iterator iter=multiRequest.getFileNames();
            while(iter.hasNext()){
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null){
                    path="D:/springUpload"+file.getOriginalFilename();
                    try {
                        file.transferTo(new File(path));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
        return path;
    }
}
