package com.poligram.service;

import java.util.List;

import com.poligram.model.Album;
import com.poligram.model.Image;

public interface ImageService {

	Image findById(Long id);

	List<Image> findAllImages();

	List<Image> findAllByAlbumId(long id);
	
	void saveImage(Image image);
	
	void deleteById(Long id);
	
	void update(Image image);

	boolean isImageExist(Image image);

}
