package library.core.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "readers")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Reader {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name" , nullable = false)
    private String first_name;

    @Column(name = "last_name",nullable = false)
    private String last_name;

    public Reader(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }
}
