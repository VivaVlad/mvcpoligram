package com.poligram.dao;

import java.util.List;
import com.poligram.model.UserProfile;
import org.springframework.stereotype.Repository;


@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Integer, UserProfile>implements UserProfileDao{

	public UserProfile findById(int id) {
		return getByKey(id);
	}

	public UserProfile findByType(String type) {
		UserProfile userProfile = (UserProfile) getEntityManager()
				.createQuery("SELECT u FROM UserProfile u WHERE u.type LIKE :type")
				.setParameter("type", type)
				.getSingleResult();
		return userProfile;
	}
	
	@SuppressWarnings("unchecked")
	public List<UserProfile> findAll(){
		List<UserProfile> userProfiles = getEntityManager()
				.createQuery("SELECT u FROM UserProfile u ORDER BY u.id ASC")
				.getResultList();
		return userProfiles;
	}
	
}
