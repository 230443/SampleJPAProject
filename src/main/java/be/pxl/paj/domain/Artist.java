package be.pxl.paj.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "artists")
@NamedQueries({
		@NamedQuery(name = "getByName", query = "SELECT a FROM Artist a WHERE a.name = :name"),
		@NamedQuery(name = "getAll", query = "SELECT a FROM Artist a"),
})
public class Artist {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "_id")
	private short id;
	@Basic
	@Column(name = "name")
	private String name;
	@OneToMany(mappedBy = "artist")
	private List<Album> albums;

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

		Artist artist = (Artist) o;

		if (id != artist.id) return false;
		if (!Objects.equals(name, artist.name)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
}
