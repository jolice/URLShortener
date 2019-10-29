package me.riguron.shortener.repository;

import me.riguron.shortener.domain.ShortenedUrl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface URLRepository extends CrudRepository<ShortenedUrl, String> {

    @Query("select s from ShortenedUrl s JOIN s.account a WHERE a.accountId = :name")
    Set<ShortenedUrl> getShorteningsFor(@Param("name") String name);
}
