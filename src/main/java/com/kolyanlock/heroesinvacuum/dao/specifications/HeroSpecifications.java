package com.kolyanlock.heroesinvacuum.dao.specifications;

import com.kolyanlock.heroesinvacuum.entity.Club_;
import com.kolyanlock.heroesinvacuum.entity.Hero;
import com.kolyanlock.heroesinvacuum.entity.Hero_;
import org.springframework.data.jpa.domain.Specification;


import java.util.List;


public class HeroSpecifications {

    public static Specification<Hero> isAlive() {
        return (root, query, cb) -> cb.equal(root.get(Hero_.ALIVE), true);
    }

    public static Specification<Hero> likeName(String name) {
        if (name == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get(Hero_.NAME)), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Hero> likeTitle(String title) {
        if (title == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get(Hero_.TITLE)), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Hero> likeHeroClassDescription(String heroClassDescription) {
        if (heroClassDescription == null) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get(Hero_.HERO_CLASS).get("description")), "%" + heroClassDescription.toLowerCase() + "%");
    }

//    public static Specification<Hero> inAnyClub() {
////        if (!in) {
////            return null;
////        }
//
//        return (root, query, cb) -> cb.isNotEmpty(root.get(Hero_.CLUBS));
//    }

    public static Specification<Hero> inClubs(List<Integer> clubIdList) {
        if (clubIdList == null) {
            return null;
        }

        if (clubIdList.isEmpty()) {
            return (root, query, cb) -> cb.isEmpty(root.get(Hero_.CLUBS));
        }

        return (root, query, cb) -> root.join(Hero_.CLUBS).get(Club_.ID).in(clubIdList);
    }

    public static Specification<Hero> inClub(String club) {
        if (club == null) {
            return null;
        }

        return (root, query, cb) -> cb.equal(root.join(Hero_.CLUBS).get(Club_.TITLE), club);
    }

}
