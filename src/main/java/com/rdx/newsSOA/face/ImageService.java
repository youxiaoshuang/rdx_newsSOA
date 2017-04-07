package com.rdx.newsSOA.face;

import com.rdx.newsSOA.entity.YImageEntity;

import java.util.List;

public interface ImageService {
	boolean insertImage(YImageEntity image);
	List<YImageEntity> getImagesPage(YImageEntity image);
//	boolean uploadImage(MultipartFile file);
//	void getImage(String imageName, HttpServletResponse response);
	String getImageFtpUrlByName(String imageName);

}
