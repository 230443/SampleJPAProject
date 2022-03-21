package be.pxl.paj.dao;

import be.pxl.paj.domain.Artist;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public interface ArtistDao {
	Optional<Artist> get(int id);
	Optional<Artist> getByName(String name);
	List<Artist> getAll();
	static ArtistDao getInstance(EntityManager entityManager) {
		return new ArtistDaoImpl(entityManager);
	}
}
