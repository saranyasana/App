package me.woemler.springblog.repositories;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



import me.woemler.springblog.models.User;

/**
 * @author woemler
 */
public class UserRepositoryImpl implements UserDetailsService {

	@Autowired 
	private MongoTemplate mongoTemplate;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return mongoTemplate.findOne(new Query(Criteria.where("username").is(username)), User.class);
	}
	

	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}


	public void saveUser(User user) {
		
		user.setName(user.getName());
		user.setEmail(user.getEmail());
		user.setUsername(user.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRegistrationDate(new Date());
		user.setRoles(new HashSet<>(Arrays.asList(User.ROLE_USER)));
		userRepository.insert(user);
		System.out.println(user.getRoles());
		userRepository.save(user);
	}
}
