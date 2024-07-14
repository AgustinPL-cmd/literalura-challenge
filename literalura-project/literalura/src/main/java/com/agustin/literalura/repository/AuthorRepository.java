package com.agustin.literalura.repository;

import com.agustin.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);

    @Query("SELECT a FROM Author a WHERE (a.birthYear IS NULL OR a.birthYear <= :endYear) AND (a.deathYear IS NULL OR a.deathYear >= :startYear)")
    List<Author> findByYearsLived(int startYear, int endYear);

}
