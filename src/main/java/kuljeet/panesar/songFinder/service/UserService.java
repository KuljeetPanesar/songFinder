package kuljeet.panesar.songFinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kuljeet.panesar.songFinder.exception.ResourceNotFoundException;
import kuljeet.panesar.songFinder.exception.UserAlreadyExistsException;
import kuljeet.panesar.songFinder.model.User;
import kuljeet.panesar.songFinder.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	//get all users
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	//get by id
	public User getUserById(long userId) {
        Optional<User> userFound = userRepo.findById(userId);
        if(userFound.isPresent()) {
            return userFound.get();
        }
        throw new ResourceNotFoundException("User with id of " + userId + " not found.");
    }
	
	//get by username
	public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User with username " + username + " not found."));
    }
		
	//create user
	public User createUser(User user) {
        Optional<User> existingUser = userRepo.findByUsername(user.getUsername());

        if(existingUser.isPresent()) {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists");
        }
        return userRepo.save(user);
    }
	
	//update/edit user
	public User updateUser(User user, long userId) {
        if(!userRepo.existsById(userId)) {
            throw new ResourceNotFoundException("User with id of " + userId + " not found.");
        }
        user.setUserId(userId);
        return userRepo.save(user);
    } //////////////maybe need constraints so it cannot be edited to an existing user?//////////////////////
	
	//remove user
	public void removeUser(long userId) {
        if(!userRepo.existsById(userId)) {
        	throw new ResourceNotFoundException("User with id of " + userId + " not found.");
        }
        userRepo.deleteById(userId);
    }
	
	//login method with email/username and password
	
	//logout
	
	//forgot password?
	
	// spring security for authentication etc?
	
	
}
