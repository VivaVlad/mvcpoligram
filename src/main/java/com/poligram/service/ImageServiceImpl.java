package com.poligram.service;

import java.util.List;

import com.poligram.model.Album;
import com.poligram.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poligram.dao.ImageDao;

@Service("imageService")
@Transactional
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageDao dao;

	public Image findById(Long id) {
		return dao.findById(id);
	}

	public List<Image> findAllImages() {
		return dao.findAll();
	}

	public List<Image> findAllByAlbumId(long id) {
		return dao.findAllByAlbumId(id);
	}

	public void saveImage(Image image){
		dao.save(image);
	}

	public void deleteById(Long id){
		dao.deleteById(id);
	}

	@Override
	public void update(Image image) {
		dao.update(image);
	}

	public boolean isImageExist(Image image) {
		return findById(image.getId()) != null;
	}


}
