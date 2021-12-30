package com.kolyanlock.heroesinvacuum.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "heroes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OrderColumn(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hero_class_id")
    @Getter
    private HeroClass heroClass;

    @ManyToMany
    @JoinTable(name = "hero_club",
            joinColumns = @JoinColumn(name = "hero_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id"))
    private List<Club> clubs;

    @Column(name = "create_date", updatable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "last_change ")
    @LastModifiedDate
    private LocalDateTime lastChange;

    @Column(name = "is_alive", columnDefinition = "boolean default true")
    private boolean alive = true;
}
