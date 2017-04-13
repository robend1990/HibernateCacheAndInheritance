package HibernatePGS1to1.HibernatePGS1to1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name="agent")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Agent {

	@Id
    @Column(name="id")
	@GeneratedValue
    Long id;
 
    @Column(name="firstname")
    String name;

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
    
    
}
