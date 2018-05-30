package com.poligram.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import com.poligram.model.User;
import org.springframework.stereotype.Repository;


@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	public User findById(Long id) {
		User user = getByKey(id);

		return user;
	}

	public User findByName(String username) {
		System.out.println("username : "+username);
		try{
			User user = (User) getEntityManager()
					.createQuery("SELECT u FROM User u WHERE u.username LIKE :username")
					.setParameter("username", username)
					.getSingleResult();
			
			return user;
		}catch(NoResultException ex){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		List<User> users = getEntityManager()
				.createQuery("SELECT u FROM User u ORDER BY u.id ASC")
				.getResultList();
		return users;
	}

	@Override
	public void deleteAll() {
		getEntityManager().createQuery("Delete  FROM User");
	}

	public void save(User user) {
		persist(user);
	}

	public void update(User user) {
		merge(user);
	}

	public void deleteUserById(Long id) {
		User user = (User) getEntityManager()
				.createQuery("SELECT u FROM User u WHERE u.id LIKE :id")
				.setParameter("id", id)
				.getSingleResult();
		delete(user);
	}
	
	protected void initializeCollection(Collection<?> collection) {
	    if(collection == null) {
	        return;
	    }
	    collection.iterator().hasNext();
	}

}
