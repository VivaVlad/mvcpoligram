package com.poligram.dao;

import java.util.List;

import com.poligram.model.Album;
import com.poligram.model.Image;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("albumDocumentDao")
public class ImageDaoImpl extends AbstractDao<Long, Image> implements ImageDao {

	@SuppressWarnings("unchecked")
	public List<Image> findAll() {
		List<Image> images = getEntityManager()
				.createQuery("SELECT u FROM Image u ORDER BY u.id ASC")
				.getResultList();
		return images;
	}

	public void save(Image document) {
		persist(document);
	}

	
	public Image findById(Long id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	public List<Image> findAllByAlbumId(long id){
		List<Image> images = getEntityManager()
				.createQuery("SELECT u FROM Image u WHERE u.album.id LIKE :albumId")
				.setParameter("albumId", id)
				.getResultList();
		return images;
	}

	public void update(Image image) {
		merge(image);
	}
	public void deleteById(Long id) {
		Image document =  getByKey(id);
		delete(document);
	}

}
