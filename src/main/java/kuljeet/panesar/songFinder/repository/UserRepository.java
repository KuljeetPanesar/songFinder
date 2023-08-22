package kuljeet.panesar.songFinder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kuljeet.panesar.songFinder.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	
}
