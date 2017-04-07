package com.rdx.newsSOA.face.impl;


import com.rdx.newsSOA.dao.YImageEntityMapper;
import com.rdx.newsSOA.entity.YImageEntity;
import com.rdx.newsSOA.face.ImageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service(value = "imageService")
public class ImageServiceImpl implements ImageService {
	@Autowired
	private YImageEntityMapper imageDao;
	@Value("#{sys['imageRootPath']}")
	private String rootPath;
	Logger logger = Logger.getLogger(this.getClass());

	public boolean insertImage(YImageEntity image) {
		// TODO Auto-generated method stub
		imageDao.insertSelective(image);
		return false;
	}

	public List<YImageEntity> getImagesPage(YImageEntity image) {
		// TODO Auto-generated method stub
		return imageDao.selectImagesPage(image);
	}

//	public boolean uploadImage(MultipartFile file) {
//		//获取年份 月 日
//		Calendar cal = Calendar.getInstance();
//		String year = String.valueOf(cal.get(Calendar.YEAR));
//		String month = String.valueOf(cal.get(Calendar.MONTH));
//		String day = String.valueOf(cal.get(Calendar.DATE));
//		// TODO Auto-generated method stub
//		InputStream is = null;
//		try {
//			is = file.getInputStream();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			logger.info(e.toString());
//			return false;
//		}
//		String fileName = file.getOriginalFilename();
//		String fileType = fileName.substring(fileName.indexOf(".") + 1);
//		String saveName = UUID.randomUUID().toString();
//		boolean ftpbool = ftpService.uploadByIS("/"+year+"/"+month+"/"+day+"/"+ saveName+"."+fileType,
//				is);
//		YImageEntity image = new YImageEntity();
//		image.setName(saveName);
//		image.setSize(file.getSize());
//		image.setType(fileType);
//		image.setUrl("/"+saveName + "/" + image.getType());
//		image.setFtpUrl("/"+year+"/"+month+"/"+day+"/"+ saveName + "." + image.getType());
//		if (ftpbool) {
//			return this.insertImage(image);
//		} else {
//			return false;
//		}
//	}

//	public void getImage(String imageName, HttpServletResponse response) {
//		// TODO Auto-generated method stub
//		try {
//			// response.setContentType("img/jpeg");
//			response.setCharacterEncoding("utf-8");
//			response.setContentType("image/jpg");
//			OutputStream outputStream = response.getOutputStream();
//			InputStream in = ftpService.getFileIS(rootPath, imageName);
//			if(in != null){
//			int len = 0;
//			byte[] buf = new byte[1024];
//			while ((len = in.read(buf, 0, 1024)) != -1) {
//				outputStream.write(buf, 0, len);
//			}
//			}else{
//				logger.info("图片"+imageName+"不存在!");
//			}
//
//			outputStream.close();
//		} catch (IOException e) { // TODO Auto-generated catch block
//									// e.printStackTrace(); } }
//		}

//	}
	public String getImageFtpUrlByName(String imageName) {
		// TODO Auto-generated method stub
		YImageEntity image = imageDao.selectFtpUtlByName(imageName);
		if(image!=null)return image.getFtpUrl();
		else return null;
	}
	
	public static void main(String[] args) {
		String ftpUserName = "admin";
		String ftpPassword = "admin";
		String ftpHost = "172.16.71.167";
		int ftpPort= 21;
	}



}
