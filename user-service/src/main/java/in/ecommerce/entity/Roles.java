package in.ecommerce.entity;


import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name =  "roles")
@AllArgsConstructor
@NoArgsConstructor

public class Roles {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Long role_id; 
	
	@Column(nullable =  false, unique =  true )
	private String name; 
	
	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	

}
