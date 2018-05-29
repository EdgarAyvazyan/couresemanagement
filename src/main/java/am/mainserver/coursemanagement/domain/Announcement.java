package am.mainserver.coursemanagement.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "announcement")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Announcement {
    @Id
    @SequenceGenerator(name = "announcement_generator" , sequenceName = "announcement_sequence")
    @GeneratedValue(generator = "announcement_generator",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "title")
    private String title;
}


