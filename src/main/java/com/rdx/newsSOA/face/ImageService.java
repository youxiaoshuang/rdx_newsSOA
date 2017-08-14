package com.rdx.newsSOA.face;

import com.rdx.newsSOA.entity.YImageEntity;

import java.util.List;

public interface ImageService {
	boolean insertImage(YImageEntity image);
	List<YImageEntity> getImagesPage(YImageEntity image);
	String getImageFtpUrlByName(String imageName);

}
