package me.riguron.shortener.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor
public class ShortenedUrl {

    @Id
    @Column
    private String shortening;

    @Column
    private String url;

    @Column
    private int uses;

    @Column
    private int redirectType;

    public ShortenedUrl(String shortening, String url, int redirectType) {
        this.shortening = shortening;
        this.url = url;
        this.redirectType = redirectType;
    }

    public void use() {
        this.uses++;
    }


}
