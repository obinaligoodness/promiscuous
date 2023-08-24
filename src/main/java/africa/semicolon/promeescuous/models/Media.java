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

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private List<Reaction> reactions;
    @Column(unique = false, columnDefinition = "MEDIUMTEXT", length = 1000)
    private String url;

    @ManyToOne
    private User user;

    private boolean isLike;

    public Media(){
        reactions = new ArrayList<>();
    }
}
