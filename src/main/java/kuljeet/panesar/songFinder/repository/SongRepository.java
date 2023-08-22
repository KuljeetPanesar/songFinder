package kuljeet.panesar.songFinder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kuljeet.panesar.songFinder.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {

	Optional<Song> findBySongName(String songName);


}
