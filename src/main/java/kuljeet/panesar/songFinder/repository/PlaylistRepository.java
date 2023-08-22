package kuljeet.panesar.songFinder.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kuljeet.panesar.songFinder.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long>{

	Optional<Playlist> findPlaylistByPlaylistName(String playlistName);

}
