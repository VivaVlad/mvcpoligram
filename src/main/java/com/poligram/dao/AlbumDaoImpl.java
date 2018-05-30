package com.poligram.dao;

import java.util.Collection;
import java.util.List;
import com.poligram.model.Album;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;


@Repository("albumDao")
public class AlbumDaoImpl extends AbstractDao<Long, Album> implements AlbumDao {

	public Album findById(Long id) {
		Album album = getByKey(id);

		return album;
	}

	public Album findByName(String albumname) {
		System.out.println("albumname : "+albumname);
		try{
			Album album = (Album) getEntityManager()
					.createQuery("SELECT u FROM Album u WHERE u.title LIKE :albumname")
					.setParameter("albumname", albumname)
					.getSingleResult();

			return album;
		}catch(NoResultException ex){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Album> findAll() {
		List<Album> albums = getEntityManager()
				.createQuery("SELECT u FROM Album u ORDER BY u.id ASC")
				.getResultList();
		return albums;
	}

	@Override
	public void deleteAll() {
		getEntityManager().createQuery("Delete  FROM Album");
	}

	public void save(Album album) {
		persist(album);
	}

	public void update(Album album) {
		merge(album);
	}

	public void deleteAlbumById(Long id) {
		Album album = (Album) getEntityManager()
				.createQuery("SELECT u FROM Album u WHERE u.id LIKE :id")
				.setParameter("id", id)
				.getSingleResult();
		delete(album);
	}

	//An alternative to Hibernate.initialize()
	protected void initializeCollection(Collection<?> collection) {
		if(collection == null) {
			return;
		}
		collection.iterator().hasNext();
	}

}
