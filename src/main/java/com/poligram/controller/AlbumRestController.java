package com.poligram.controller;

import com.poligram.model.Album;
import com.poligram.service.AlbumService;
import com.poligram.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;


@RestController
public class AlbumRestController {

	public static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	AlbumService albumService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Albums---------------------------------------------

	@RequestMapping(value = "/album/", method = RequestMethod.GET)
	public ResponseEntity<List<Album>> listAllAlbums() {
		List<Album> albums = albumService.findAllAlbums();
		if (albums.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Album>>(albums, HttpStatus.OK);
	}

	// -------------------Retrieve Single Album------------------------------------------

	@RequestMapping(value = "/album/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAlbum(@PathVariable("id") long id) {
		logger.info("Fetching Album with id {}", id);
		Album album = albumService.findById(id);
		if (album == null) {
			logger.error("Album with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Album with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Album>(album, HttpStatus.OK);
	}

	// -------------------Create a Album-------------------------------------------

	@RequestMapping(value = "/album/", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<?> createAlbum(@RequestBody Album album, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Album : {}", album);

		if (albumService.isAlbumExist(album)) {
			logger.error("Unable to create. A Album with name {} already exist", album.getTitle());
			return new ResponseEntity(new CustomErrorType("Unable to create. A Album with name " +
					album.getTitle() + " already exist."), HttpStatus.CONFLICT);
		}
		albumService.saveAlbum(album);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/album/{id}").buildAndExpand(album.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Album ------------------------------------------------

	@RequestMapping(value = "/album/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAlbum(@PathVariable("id") long id, @RequestBody Album album) {
		logger.info("Updating Album with id {}", id);

		Album currentAlbum = albumService.findById(id);

		if (currentAlbum == null) {
			logger.error("Unable to update. Album with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Album with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentAlbum.setTitle(album.getTitle());
		currentAlbum.setImages(album.getImages());
		
		albumService.updateAlbum(currentAlbum);
		return new ResponseEntity<Album>(currentAlbum, HttpStatus.OK);
	}

	// ------------------- Delete a Album-----------------------------------------

	@RequestMapping(value = "/album/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAlbum(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Album with id {}", id);

		Album album = albumService.findById(id);
		if (album == null) {
			logger.error("Unable to delete. Album with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Album with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		albumService.deleteAlbumById(id);
		return new ResponseEntity<Album>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Albums-----------------------------

	@RequestMapping(value = "/album/", method = RequestMethod.DELETE)
	public ResponseEntity<Album> deleteAllAlbums() {
		logger.info("Deleting All Albums");

		albumService.deleteAllAlbums();
		return new ResponseEntity<Album>(HttpStatus.NO_CONTENT);
	}

}