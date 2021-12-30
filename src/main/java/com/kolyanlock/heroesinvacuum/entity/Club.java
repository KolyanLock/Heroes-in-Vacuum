package com.kolyanlock.heroesinvacuum.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "clubs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OrderColumn(name = "title")
    private String title;

    @OrderColumn(name = "description")
    private String description;

//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @JoinTable(name = "hero_club",
//            joinColumns = @JoinColumn(name = "club_id"),
//            inverseJoinColumns = @JoinColumn(name = "hero_id"))
//    private List<Hero> heroes;
}
