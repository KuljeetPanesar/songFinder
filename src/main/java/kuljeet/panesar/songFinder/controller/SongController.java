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

import kuljeet.panesar.songFinder.model.Song;
import kuljeet.panesar.songFinder.service.SongService;

@RestController
@RequestMapping("/api/v1/songs")
@CrossOrigin(origins = "http://localhost:3000")
public class SongController {
	
	@Autowired
	private SongService songService;
	
	@GetMapping
	public List<Song> getAllSongs() {
		return songService.getAllSongs();
	}
	
	@GetMapping("/{songId}")
	public ResponseEntity<Song> getSongById(@PathVariable long songId) {
			Song song = songService.getSongById(songId);
			
			if(song != null) {
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(song);
			}
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.build();
	}

	
	@GetMapping("/song/{songName}")
	public ResponseEntity<Song> getSongBySongName(@PathVariable("songName") String songName) {
		Song song = songService.getSongBySongName(songName);
		
		if(song != null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(song);
		}
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.build();
	}
	
	
	@PostMapping
	public ResponseEntity<Song> addSong(@RequestBody Song song) {
        Song createdSong = songService.addSong(song);

        if (createdSong != null) {
            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{songId}")
                .buildAndExpand(createdSong.getSongId())
                .toUri();
            return ResponseEntity
                .created(location)
                .build();
        }
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .build();
    }
	
	@PutMapping("/{songId}")
    public ResponseEntity<Song> updateSong(@PathVariable long songId, @RequestBody Song song) {
            return ResponseEntity.ok(songService.updateSong(song, songId));
    }

    @DeleteMapping("/{songId}")
    public ResponseEntity<Void> removeSong(@PathVariable long songId) {
    	songService.removeSong(songId);
            return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
	
}
