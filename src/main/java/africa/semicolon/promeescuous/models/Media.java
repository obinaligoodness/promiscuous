package africa.semicolon.promeescuous.models;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.List;

@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private List<Reaction> reactions;
    @Column(unique = true, columnDefinition = "MEDIUMTEXT", length = 500)
    private String url;
    @ManyToOne
    private User user;
}
