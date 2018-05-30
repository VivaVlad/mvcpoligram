package com.poligram.dao;

import java.util.List;

import com.poligram.model.Album;


public interface AlbumDao {

	Album findById(Long id);

	Album findByName(String name);

	void save(Album album);

	void update(Album album);

	void deleteAlbumById(Long id);

	List<Album> findAll();

	void deleteAll();

}

