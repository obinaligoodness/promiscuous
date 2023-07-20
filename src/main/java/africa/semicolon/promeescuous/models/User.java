package africa.semicolon.promeescuous.models;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private LocalDate dateOfBirth;
    @OneToOne
    private Address address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne
    private BasicData basicData;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
