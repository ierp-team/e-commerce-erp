package com.ierp.common.beans;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class UpPic {
    //private String path="./Admin/Image/";
    private static String image="defaut.jpg";
    
    public static String UpAndgetPath(MultipartFile file,HttpServletRequest request) throws IllegalStateException, IOException
    {  
    	 if (file!=null) {// 判断上传的文件是否为空
    		String path=null;
    		String type=null;// 文件类型
    		String fileName=file.getOriginalFilename();// 文件原名称
    		System.out.println("上传的文件原名称:"+fileName);
    		// 判断文件类型
    		type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
    	    if(type!=null)
    	    {
    	    	if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
    	    		 // 项目在容器中实际发布运行的根路径
    	    		String realPath=request.getSession().getServletContext().getRealPath("/")+"Admin\\resources\\images\\pictures\\";
    	    		 // 自定义的文件名称
    	    		
    	    		File md=new File(realPath);
    	    		if(!md.exists())
    	    		{
    	    			md.mkdir();
    	    		}
    	    		String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;
    	    		 // 设置存放图片文件的路径
    	    		path=realPath+trueFileName;
    	    		System.out.println("存放图片文件的路径:"+path);
    	    		 // 转存文件到指定的路径
    	    		 file.transferTo(new File(path));
    	    		 System.out.println("文件成功上传到指定目录下");
    	    		 return trueFileName;
    	    	 }else {
    	    		System.out.println("不是我们想要的文件类型,请按要求重新上传");
    	    		       return null;
    	    		     }
    	    }else
    	    return image;
    	    
    	 }else 
    	return image;
    }  
}
