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

import kuljeet.panesar.songFinder.model.Playlist;
import kuljeet.panesar.songFinder.model.Song;
import kuljeet.panesar.songFinder.service.PlaylistService;

@RestController
@RequestMapping("/api/v1/playlists")
@CrossOrigin(origins = "hhtp://localhost:3000")
public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
	@GetMapping
	public List<Playlist> getAllPlaylists() {
		return playlistService.getAllPlaylists();
	}

	@GetMapping("/{playlistId}")
	public ResponseEntity<Playlist> getPlaylistById(@PathVariable long playlistId) {
			Playlist playlist = playlistService.getPlaylistById(playlistId);
			
			if(playlist != null) {
				return ResponseEntity
						.status(HttpStatus.OK)
						.body(playlist);
			}
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.build();
	}

	
	@GetMapping("/playlist/{playlistName}")
	public ResponseEntity<Playlist> getPlaylistByPlaylistName(@PathVariable("playlistName") String playlistName) {
		Playlist playlist = playlistService.getPlaylistByPlaylistName(playlistName);
		
		if(playlist != null) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(playlist);
		}
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.build();
	}
	
	
	@PostMapping
	public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        Playlist createdPlaylist = playlistService.createPlaylist(playlist);

        if (createdPlaylist != null) {
            URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{playlistId}")
                .buildAndExpand(createdPlaylist.getPlaylistId())
                .toUri();
            return ResponseEntity
                .created(location)
                .build();
        }
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .build();
    } //////// playlist for other users not allowed if name is the same
	
	@PutMapping("/{playlistId}")
    public ResponseEntity<Playlist> editPlaylist(@PathVariable long playlistId, @RequestBody Playlist playlist) {
            return ResponseEntity.ok(playlistService.editPlaylist(playlist, playlistId));
    }

    @DeleteMapping("/{playlistId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable long playlistId) {
    	playlistService.deletePlaylist(playlistId);
            return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    } //// not deleting
	
    @PostMapping("/addSong/{playlistId}")
    public ResponseEntity<Playlist> addSongToPlaylist(@PathVariable long playlistId, @RequestBody Song song) {
        Playlist updatedPlaylist = playlistService.addSongToPlaylist(playlistId, song);
        return ResponseEntity.ok(updatedPlaylist);
    }
    
    @DeleteMapping("/removeSong/{playlistId}")
    public ResponseEntity<Playlist> removeSongFromPlaylist(@PathVariable long playlistId, @RequestBody Song song) {
        Playlist updatedPlaylist = playlistService.removeSongFromPlaylist(playlistId, song);
        return ResponseEntity.ok(updatedPlaylist);
    }

}

///// playlist by user??
