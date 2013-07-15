package first_pr.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.ioc.annotations.Inject;

@Entity
@Table(name = "users")
public class Users implements Serializable {

	@Inject
    public Users() {}
	
	private static final long serialVersionUID=100L;
    private Long id;
    private String login;
    public static String name;
    public static String password;

    public Users(Long id, String name, String login, String password) {
        super();
        this.id = id;
        Users.name = name;
        this.login = login;
        Users.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uid", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        Users.name = name;
    }

    @Column(name = "login", nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Users.password = password;
    }
}