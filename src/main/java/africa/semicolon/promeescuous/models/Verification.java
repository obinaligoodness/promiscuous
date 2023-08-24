package africa.semicolon.promeescuous.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity @Table(name = "VerificationTable")
public class Verification {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String cloudinaryVideoUrl;
    private String verificationStatus;

//    private Long id;

}
