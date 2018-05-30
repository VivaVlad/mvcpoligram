package com.poligram.controller;

import com.poligram.model.Album;
import com.poligram.model.FileBucket;
import com.poligram.model.Image;
import com.poligram.service.AlbumService;
import com.poligram.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class AppController {

	@Autowired
	AlbumService albumService;

	@Autowired
	ImageService imageService;
	@RequestMapping("/")
	String home(ModelMap model) {
		model.addAttribute("title","Polygram");
		model.addAttribute("loggedinuser", getPrincipal());
		return "index";
	}

	@RequestMapping("/partials/{page}")
	String partialHandler(@PathVariable("page") final String page) {
		return page;
	}

	@RequestMapping(value = { "add-image-{albumId}" }, method = RequestMethod.GET)
	public String addImages(@PathVariable long albumId, ModelMap model) {
		Album album = albumService.findById(albumId);
		model.addAttribute("album", album);

		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);

		List<Image> images = imageService.findAllByAlbumId(albumId);
		model.addAttribute("images", images);

		return "manageimages";
	}


	@RequestMapping(value = { "download-image-{imageId}" }, method = RequestMethod.GET)
	public String downloadImage(@PathVariable long imageId, HttpServletResponse response) throws IOException {
		Image image = imageService.findById(imageId);
		response.setContentType(image.getType());
		response.setContentLength(image.getContent().length);
		response.setHeader("Content-Disposition","attachment; filename=\"" + image.getName() +"\"");

		FileCopyUtils.copy(image.getContent(), response.getOutputStream());

		return "redirect:/";
	}

	@RequestMapping(value = { "delete-image-{imageId}" }, method = RequestMethod.GET)
	public String delete(@PathVariable long imageId) {
		imageService.deleteById(imageId);
		return "redirect:/";
	}

	@RequestMapping(value = { "add-image-{albumId}" }, method = RequestMethod.POST)
	public  String uploadImage(@Valid FileBucket fileBucket, BindingResult result, ModelMap model, @PathVariable long albumId) throws IOException{

		if (result.hasErrors()) {
			System.out.println("validation errors");
			Album album = albumService.findById(albumId);
			model.addAttribute("album", album);

			List<Image> images = imageService.findAllByAlbumId(albumId);
			model.addAttribute("images", images);

			return "manageimages";
		} else {

			System.out.println("Fetching file");

			Album album = albumService.findById(albumId);
			model.addAttribute("album", album);

			saveImage(fileBucket, album);

			return "redirect:/add-image-"+albumId;
		}
	}

	private void saveImage(FileBucket fileBucket, Album album) throws IOException {

		Image image = new Image();

		MultipartFile multipartFile = fileBucket.getFile();

		image.setName(multipartFile.getOriginalFilename());
		image.setDescription(fileBucket.getDescription());
		image.setType(multipartFile.getContentType());
		image.setContent(multipartFile.getBytes());
		image.setAlbum(album);
		imageService.saveImage(image);
	}

	@RequestMapping(value = "album-{album_id}", method = RequestMethod.GET)
	public String showImages(@PathVariable("album_id") long album_id, ModelMap model) {

		List<Image> images = imageService.findAllByAlbumId(album_id);
		Album album = albumService.findById(album_id);
		model.addAttribute("album",album);
		model.addAttribute("images", images);
		return "images";
	}
	@RequestMapping(value = "image-{albumId}-{imageId}", method = RequestMethod.GET)
	public String showImage(@PathVariable long albumId, @PathVariable long imageId,ModelMap model) {
		Album album = albumService.findById(albumId);
		Image image = imageService.findById(imageId);
		model.addAttribute("image", image);
		model.addAttribute("album", album);

		return "image";
	}
	@RequestMapping(value = "/image", method = RequestMethod.GET)
	public void showImage(@RequestParam("id") long id, HttpServletResponse response)
			throws ServletException, IOException {
		Image image = imageService.findById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(image.getContent());
		response.getOutputStream().close();
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}
	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
}
