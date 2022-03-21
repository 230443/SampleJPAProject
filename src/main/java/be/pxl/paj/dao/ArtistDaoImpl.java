package be.pxl.paj.dao;

import be.pxl.paj.domain.Album;
import be.pxl.paj.domain.Artist;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ArtistDaoImpl implements ArtistDao {

	private static final Logger LOGGER = LogManager.getLogger(ArtistDaoImpl.class);
	private final EntityManager entityManager;

	public ArtistDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@Override
	public Optional<Artist> get(int id) {
		return Optional.ofNullable(entityManager.find(Artist.class, id));
	}

	@Override
	public Optional<Artist> getByName(String name) {
		return Optional.ofNullable(
				entityManager
						.createNamedQuery("getByName", Artist.class)
						.setParameter("name", name)
						.getSingleResult()
		);
	}

	@Override
	public List<Artist> getAll() {
		return entityManager.createNamedQuery("getAll", Artist.class).getResultList();
	}
}
