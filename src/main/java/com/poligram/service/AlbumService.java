package com.poligram.service;

import java.util.List;

import com.poligram.model.Album;


public interface AlbumService {

	Album findById(Long id);

	Album findByName(String name);

	void saveAlbum(Album user);

	void updateAlbum(Album user);

	void deleteAlbumById(Long id);

	void deleteAllAlbums();

	List<Album> findAllAlbums();

	boolean isAlbumExist(Album user);

}