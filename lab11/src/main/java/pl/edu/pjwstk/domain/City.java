package pl.edu.pjwstk.domain;

import java.util.List;

import javax.persistence.*;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "city.all", query = "Select c from City c"),
	@NamedQuery(name = "city.update", query = "UPDATE City c SET c.name = :name, c.zipcode = :zipcode WHERE c.id = :id"),
	@NamedQuery(name = "city.byZipcode", query = "Select c from City c where c.zipcode = :zipcode")
})
public class City {

	private Long id;
	private String name;
	private String zipcode;
	private List<Unicorn> unicorns;

	public City() {}
	
	public City(String name, String zipcode) {
		this.name = name;
		this.zipcode = zipcode;
	}
	
	public City(Long id, String name, String zipcode) {
		this(name, zipcode);
		this.id = id;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(unique = true, nullable = false)
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@OneToMany(orphanRemoval=true)
    //@JoinColumn(name="OWNER")
	public List<Unicorn> getUnicorns() {
		return unicorns;
	}
	public void setUnicorns(List<Unicorn> unicorns) {
		this.unicorns = unicorns;
	}
}
