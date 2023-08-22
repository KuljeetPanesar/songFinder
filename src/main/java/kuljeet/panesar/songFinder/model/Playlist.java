package kuljeet.panesar.songFinder.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Playlist {
	
	//can add lengths of playlist time - add time/length of song to song class
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAYLIST_ID_GEN")
	@SequenceGenerator(name = "PLAYLIST_ID_GEN", sequenceName = "PLAYLIST_ID_SEQ", allocationSize =1)
	private long playlistId;
	private String playlistName;
	private String playlistDescription;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user; //many to one user
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
            name = "Playlist_Song",
            joinColumns = @JoinColumn(name = "playlistId"),
            inverseJoinColumns = @JoinColumn(name = "songId")
    )
	private List<Song> songs;

	
	
	public Playlist() {
		super();
	}



	public Playlist(String playlistName, String playlistDescription, User user, List<Song> songs) {
		super();
		this.playlistName = playlistName;
		this.playlistDescription = playlistDescription;
		this.user = user;
		this.songs = songs;
	}



	public long getPlaylistId() {
		return playlistId;
	}



	public void setPlaylistId(long playlistId) {
		this.playlistId = playlistId;
	}



	public String getPlaylistName() {
		return playlistName;
	}



	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}



	public String getPlaylistDescription() {
		return playlistDescription;
	}



	public void setPlaylistDescription(String playlistDescription) {
		this.playlistDescription = playlistDescription;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<Song> getSongs() {
		return songs;
	}



	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}
	
}