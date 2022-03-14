package be.pxl.paj.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "songs")
public class Song {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "_id")
	private short id;
	@Basic
	@Column(name = "track")
	private Byte track;
	@Basic
	@Column(name = "title")
	private String title;
	@ManyToOne
	@JoinColumn(name = "album", referencedColumnName = "_id")
	private Album album;

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public Byte getTrack() {
		return track;
	}

	public void setTrack(Byte track) {
		this.track = track;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Song song = (Song) o;

		if (id != song.id) return false;
		if (!Objects.equals(track, song.track)) return false;
		if (!Objects.equals(title, song.title)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (track != null ? track.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		return result;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
}
