package kuljeet.panesar.songFinder.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SONG_ID_GEN")
	@SequenceGenerator(name = "SONG_ID_GEN", sequenceName = "SONG_ID_SEQ", allocationSize = 1) 
	private long songId;
	private String songName;
	private String artist;
	private String album;
	private String genre;
	private int releaseYear;
	@Column(length = 10000)
	private String lyrics;
	
	@ManyToMany(mappedBy="songs")
	private List<Playlist> playlists;
	
	
	public Song() {
		super();
	}


	public Song(String songName, String artist, String album, String genre, int releaseYear, String lyrics) {
		super();
		this.songName = songName;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.lyrics = lyrics;
	}


	public long getSongId() {
		return songId;
	}


	public void setSongId(long songId) {
		this.songId = songId;
	}


	public String getSongName() {
		return songName;
	}


	public void setSongName(String songName) {
		this.songName = songName;
	}


	public String getArtist() {
		return artist;
	}


	public void setArtist(String artist) {
		this.artist = artist;
	}


	public String getAlbum() {
		return album;
	}


	public void setAlbum(String album) {
		this.album = album;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}


	public int getReleaseYear() {
		return releaseYear;
	}


	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}


	public String getLyrics() {
		return lyrics;
	}


	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	
	

	@Override
	public int hashCode() {
		return Objects.hash(album, artist, genre, lyrics, songId, songName, releaseYear);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		return Objects.equals(album, other.album) && Objects.equals(artist, other.artist)
				&& Objects.equals(genre, other.genre) && Objects.equals(lyrics, other.lyrics) && songId == other.songId
				&& Objects.equals(songName, other.songName) && Objects.equals(releaseYear, other.releaseYear);
	}


	@Override
	public String toString() {
		return "Song [songId=" + songId + ", songName=" + songName + ", artist=" + artist + ", album=" + album
				+ ", genre=" + genre + ", releaseYear=" + releaseYear + ", lyrics=" + lyrics + "]";
	}

	
}
