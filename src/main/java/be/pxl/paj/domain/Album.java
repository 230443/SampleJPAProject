package be.pxl.paj.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "albums")
public class Album {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "_id")
	private short id;
	@Basic
	@Column(name = "name")
	private String name;
	@OneToMany(mappedBy = "album")
	private List<Song> songs;
	@ManyToOne
	@JoinColumn(name = "artist", referencedColumnName = "_id")
	private Artist artist;

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Album album = (Album) o;

		if (id != album.id) return false;
		if (!Objects.equals(name, album.name)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
}
