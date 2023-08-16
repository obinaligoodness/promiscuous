package africa.semicolon.promeescuous.models;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Table(name = "users")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateOfBirth;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Address address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String createdAt;
    private String firstName;
    private String lastName;
    @ElementCollection
    private Set<Interest> interests;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String password;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private boolean isActive;


    @PrePersist
    public void setCreatedAt(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        createdAt = currentTime.format(formatter);

    }
}
