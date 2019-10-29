package me.riguron.shortener.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Account account;

    public ShortenedUrl(String shortening, String url, int redirectType, Account account) {
        this.shortening = shortening;
        this.url = url;
        this.redirectType = redirectType;
        this.account = account;
    }

    public void use() {
        this.uses++;
    }


}
