package kuljeet.panesar.songFinder.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import kuljeet.panesar.songFinder.model.User;
import kuljeet.panesar.songFinder.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable long userId) {
        User user = userService.getUserById(userId);

        if(user != null) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
        }
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .build();
    }
	
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUserame(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);

        if(user != null) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
        }

        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .build();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);

        if (createdUser != null) {
            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userId}")
                .buildAndExpand(createdUser.getUserId())
                .toUri();
            return ResponseEntity
                .created(location)
                .build();
        }
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .build();
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody User user) {
            return ResponseEntity.ok(userService.updateUser(user, userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> removeUser(@PathVariable long userId) {
    	userService.removeUser(userId);
            return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}

//logging and exception handling
