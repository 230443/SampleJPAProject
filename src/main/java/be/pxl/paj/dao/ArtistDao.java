package be.pxl.paj.dao;

import be.pxl.paj.domain.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistDao {
	Artist get(int id);
	Optional<Artist> getByName(String name);
	List<Artist> getAll();
}
