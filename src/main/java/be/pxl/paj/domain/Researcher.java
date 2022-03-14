package be.pxl.paj.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Researcher {

	private static final Logger LOGGER = LogManager.getLogger(Researcher.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 40, nullable = false)
	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	private ContactInformation contactInformation;
	@ManyToMany
	private List<Project> projects = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}


	public List<Project> getProjects() {
		return projects;
	}

	public void addProject(Project project) {
		if(projects.contains(project)) {
			LOGGER.warn("researcher already assigned to this project");
		} else {
			projects.add(project);
			project.addResearcher(this);
		}
	}

	public void removeProject(Project project) {
		if(projects.contains(project)) {
			projects.remove(project);
			project.removeResearcher(this);
		} else {
			LOGGER.warn("researcher not have this project");
		}
	}

	@Override
	public String toString() {
		return "Researcher{" +
				"id=" + id +
				", name='" + name + '\'' +
				", contactInformation=" + contactInformation +
				", project=" + projects +
				'}';
	}
}
