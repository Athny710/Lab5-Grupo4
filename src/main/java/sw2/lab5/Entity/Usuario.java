package sw2.lab5.Entity;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String intro;
    private String profile;
    @Column(nullable = false)
    private int active;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

}
