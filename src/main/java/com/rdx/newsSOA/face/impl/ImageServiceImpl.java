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
