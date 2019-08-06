package me.riguron.shortener.repository;

import me.riguron.shortener.domain.ShortenedUrl;
import org.springframework.data.repository.CrudRepository;
public interface URLRepository extends CrudRepository<ShortenedUrl, String> {

}
