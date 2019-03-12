package com.baizhi.controller;

import com.baizhi.entity.Papers;
import com.baizhi.service.PapersService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value ="papers",method = {RequestMethod.GET,RequestMethod.POST})
public class PapersController {
    @Autowired
    private PapersService papersService;

    //文件上传
    @RequestMapping("upLoad")
    @ResponseBody
    public String upLoad(MultipartFile multipartFile, HttpSession session) throws IOException {
        //获取上传文件的原始文件名
        String originalFilename = multipartFile.getOriginalFilename();
        String id = UUID.randomUUID().toString();
        //生成要保存的文件名
        originalFilename=id+"UUID"+originalFilename;
        //文件要上传的位置
        String filePath=session.getServletContext().getRealPath("/upFile");
        //把文件上传到服务器上
       /* BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream=null;
        try {
            bufferedInputStream=new BufferedInputStream((InputStream) multipartFile);
            byte[] bytes=new byte[1024];
            int read = bufferedInputStream.read(bytes);
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath));
            System.out.println("bufferedInputStream"+bufferedInputStream);
            System.out.println("bufferedOutputStream"+bufferedOutputStream);
            while (read!=-1){
                bufferedOutputStream.write(bytes,0,read);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedOutputStream.close();
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        multipartFile.transferTo(new File(filePath+"/"+originalFilename));
        Papers papers=new Papers();
        papers.setName(originalFilename);
        papers.setPath("/upFile/"+originalFilename);
        int i = papersService.addPapers(papers);
        String s=null;
        if(i==1){
            s="上传成功";
        }else{
            s="上传失败";
        }
        return s;
    }

    //文件下载
    @RequestMapping("downLoad")
    public void downLoad(String path, HttpServletResponse response,HttpSession session) throws IOException {
        //获取要下载的文件资源的路径
        String realPath = session.getServletContext().getRealPath(path);
        System.out.println(path);
        System.out.println("realPath"+realPath);
        InputStream in=new FileInputStream(realPath);
        ServletOutputStream outputStream = response.getOutputStream();
        String extension = FilenameUtils.getExtension(path);
        //获取下载文件的默认文件名
        String[] uuIds = path.split("UUID");
        for (String uuId : uuIds) {
            System.out.println(uuId);
        }
        path=uuIds[1];
        //设置下载文件的类型
        response.setHeader("content-type",extension);
        //设置下载文件的方式
        response.setHeader("content-disposition","attachment;filename="+path);
        IOUtils.copy(in,outputStream);
    }

    //查询所有能被下载的文件
    @RequestMapping("selectAll")
    @ResponseBody
    public List<Papers> selectAll(){
        List<Papers> papers = papersService.queryAll();
        return papers;
    }
}
