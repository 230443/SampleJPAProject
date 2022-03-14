package be.pxl.paj;

import be.pxl.paj.dao.ArtistDao;
import be.pxl.paj.dao.ArtistDaoImpl;
import be.pxl.paj.dao.ProjectDao;
import be.pxl.paj.dao.ResearcherDao;
import be.pxl.paj.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class ArtistDetailsApp {

	private static final Logger LOGGER = LogManager.getLogger(ArtistDetailsApp.class);

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		Scanner scanner = new Scanner(System.in);
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = entityManagerFactory.createEntityManager();
			ArtistDao artistDao = ArtistDao.getInstance(entityManager);

			System.out.println("Artist name: ");
			String artistName = scanner.nextLine();
			Optional<Artist> optionalArtist =  artistDao.getByName(artistName);
			Artist artist = optionalArtist.orElseThrow();
			System.out.println("*** " + artist.getName());
			artist.getAlbums().forEach(ArtistDetailsApp::printAlbum);

		} catch (NoSuchElementException e) {
			LOGGER.warn("Artist not found", e);
		}
		finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (entityManagerFactory != null) {
				entityManagerFactory.close();
			}
		}
	}

	private static void printAlbum(Album album) {
		System.out.println("- " + album.getName());
		album.getSongs().stream()
				.sorted(Comparator.comparing(Song::getTrack))
				.forEach(
						r -> System.out.println(
								"\t -> " +
								r.getTrack() +
								"\t" +
								r.getTitle()
						));
	}
}
