package kuljeet.panesar.songFinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kuljeet.panesar.songFinder.exception.PlaylistAlreadyExistsException;
import kuljeet.panesar.songFinder.exception.ResourceNotFoundException;
import kuljeet.panesar.songFinder.model.Playlist;
import kuljeet.panesar.songFinder.model.Song;
import kuljeet.panesar.songFinder.repository.PlaylistRepository;

@Service
public class PlaylistService {

	@Autowired
	private PlaylistRepository playlistRepo;
	
	//get all playlists
	public List<Playlist> getAllPlaylists() {
		return playlistRepo.findAll();
	}
	
	//get playlist by id
	public Playlist getPlaylistById(long playlistId) {
		Optional<Playlist> playlistFound = playlistRepo.findById(playlistId);
		if(playlistFound.isPresent()) {
			return playlistFound.get();
		}
		throw new ResourceNotFoundException("Playlist with id of " + playlistId + " not found.");
	}
	
	//get playlist by name
	public Playlist getPlaylistByPlaylistName(String playlistName) {
		return playlistRepo.findPlaylistByPlaylistName(playlistName).orElseThrow(() -> new ResourceNotFoundException(playlistName + " playlist not found."));
	}
	
	//create a playlist
	public Playlist createPlaylist(Playlist playlist) {
		Optional<Playlist> existingPlaylist = playlistRepo.findPlaylistByPlaylistName(playlist.getPlaylistName());
		
		if(existingPlaylist.isPresent()) {
			throw new PlaylistAlreadyExistsException("Playlist with the name " + playlist.getPlaylistName() + " already exists");
		}
		
		return playlistRepo.save(playlist);
	}
	
	//edit playlist (names and description only)
	public Playlist editPlaylist(Playlist playlist, long playlistId) {
		if(!playlistRepo.existsById(playlistId)) {
			throw new ResourceNotFoundException("Playlist with id of " + playlist.getPlaylistId() + " not found.");
		}
		playlist.setPlaylistId(playlistId);
		return playlistRepo.save(playlist);
	} ////////////////////////////////////////////// ONLY EDIT NAME AND DESCRPITION OF PLAYLIST
	
	//delete playlist
	public void deletePlaylist(long playlistId) {
		if(!playlistRepo.existsById(playlistId)) {
			throw new ResourceNotFoundException("Playlist with id of " + playlistId + " not found.");
		}
	}
	
	
	//add song to playlist?
	public Playlist addSongToPlaylist(long playlistId, Song song) {
	    // Retrieve the playlist by its ID
	    Playlist playlist = getPlaylistById(playlistId);
	    
	    // Check if the song already exists in the playlist
	    List<Song> songs = playlist.getSongs();
	    if (songs.contains(song)) {
	        throw new IllegalArgumentException("Song already exists in the playlist.");
	    }
	    
	    // Add the song to the playlist's list of songs
	    songs.add(song);
	    playlist.setSongs(songs);
	    
	    // Save the updated playlist to the repository
	    return playlistRepo.save(playlist);
	}
	
	
	//remove song in playlist
	public Playlist removeSongFromPlaylist(long playlistId, Song song) {
	    // Retrieve the playlist by its ID
	    Playlist playlist = getPlaylistById(playlistId);

	    // Check if the song exists in the playlist
	    List<Song> songs = playlist.getSongs();
	    if (!songs.contains(song)) {
	        throw new IllegalArgumentException("Song does not exist in the playlist.");
	    }

	    // Remove the song from the playlist's list of songs
	    songs.remove(song);
	    playlist.setSongs(songs);

	    // Save the updated playlist to the repository
	    return playlistRepo.save(playlist);
	}
	
	
	// something for song length, date added to playlist 
}
