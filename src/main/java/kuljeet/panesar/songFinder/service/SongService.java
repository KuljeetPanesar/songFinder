package kuljeet.panesar.songFinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kuljeet.panesar.songFinder.exception.ResourceNotFoundException;
import kuljeet.panesar.songFinder.exception.SongAlreadyExistsException;
import kuljeet.panesar.songFinder.model.Song;
import kuljeet.panesar.songFinder.repository.SongRepository;

@Service
public class SongService {
	
	@Autowired
	private SongRepository songRepo;
	
	//get all songs
	public List<Song> getAllSongs() {
		return songRepo.findAll();
	}
	
	//get song by id
	public Song getSongById(long songId) {
		Optional<Song> songFound = songRepo.findById(songId);
		if(songFound.isPresent()) {
			return songFound.get();
		}
		throw new ResourceNotFoundException("Song with id of " + songId + " not found.");
	}
	
	
	//get by song name
	public Song getSongBySongName(String songName) {
		return songRepo.findBySongName(songName).orElseThrow(() -> new ResourceNotFoundException(songName + " not found."));
	}
	
	
	//create song
	public Song addSong(Song song) {
		Optional<Song> existingSong = songRepo.findBySongName(song.getSongName());
		
		if(existingSong.isPresent()) {
			throw new SongAlreadyExistsException(song.getSongName() + " is already in the database.");
		}								//////////// check this gives the name///////////
		return songRepo.save(song);
	}
	

	//update song details
	public Song updateSong(Song song, long songId) {
		if(!songRepo.existsById(songId)) {
			throw new ResourceNotFoundException("Song with id of " + songId + " not found.");
		}
		song.setSongId(songId);
		return songRepo.save(song);
	}
	
	//delete song
	public void removeSong(long songId) {
		if(!songRepo.existsById(songId)) {
			throw new ResourceNotFoundException("Song with id of " + songId + " not found.");
		}
		songRepo.deleteById(songId);
	}
	
}
