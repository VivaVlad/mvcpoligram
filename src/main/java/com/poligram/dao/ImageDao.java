package com.poligram.dao;

import java.util.List;

import com.poligram.model.Album;
import com.poligram.model.Image;

public interface ImageDao {

	List<Image> findAll();
	
	Image findById(Long id);
	
	void save(Image image);
	
	void deleteById(Long id);


	void update(Image image);

    List<Image> findAllByAlbumId(long id);
}
