package br.com.sga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.sga.model.Role;
import br.com.sga.model.User;
import br.com.sga.repository.RoleRepository;
import br.com.sga.repository.UserRepository;
import br.com.sga.service.exception.ServiceException;

/**
 * @author sga
 *
 */
@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * @param user
     */
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    /**
     * @param username
     * @return
     */
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    /**
     * @return
     */
    public List<User> findAll() {
		return userRepository.findAll();
	}
    
    /**
     * @return
     */
    public List<Role> listRoles() {
		return roleRepository.findAll();
	}
    
    /**
     * @param id
     */
    public void delete(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceException("The User could not be found.");
		}
	}
}