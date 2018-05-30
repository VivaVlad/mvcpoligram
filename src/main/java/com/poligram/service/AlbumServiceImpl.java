package com.poligram.service;

import java.util.List;

import com.poligram.dao.AlbumDao;
import com.poligram.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {

	@Autowired
	private AlbumDao albumRepository;

	public Album findById(Long id) {
		return albumRepository.findById(id);
	}

	public Album findByName(String name) {
		return albumRepository.findByName(name);
	}

	public void saveAlbum(Album album) {
		albumRepository.save(album);
	}

	public void updateAlbum(Album album) {
		albumRepository.update(album);
	}

	public void deleteAlbumById(Long id){
		albumRepository.deleteAlbumById(id);
	}

	public void deleteAllAlbums(){
		albumRepository.deleteAll();
	}

	public List<Album> findAllAlbums(){
		return albumRepository.findAll();
	}

	public boolean isAlbumExist(Album album) {
		return findByName(album.getTitle()) != null;
	}

}
