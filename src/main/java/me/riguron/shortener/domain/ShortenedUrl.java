package me.riguron.shortener.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
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

    public ShortenedUrl() {
    }

    public ShortenedUrl(String shortening, String url, int redirectType) {
        this.shortening = shortening;
        this.url = url;
        this.redirectType = redirectType;
    }

    public String getUrl() {
        return url;
    }

    public int getUses() {
        return uses;
    }

    public void use() {
        this.uses++;
    }

    public String getShortening() {
        return shortening;
    }

    public int getRedirectType() {
        return redirectType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShortenedUrl)) {
            return false;
        }
        ShortenedUrl that = (ShortenedUrl) o;
        return getUses() == that.getUses()
                &&
                getRedirectType() == that.getRedirectType()
                &&
                Objects.equals(getShortening(), that.getShortening())
                &&
                Objects.equals(getUrl(), that.getUrl());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getShortening(), getUrl(), getUses(), getRedirectType());
    }

    @Override
    public String toString() {
        return "ShortenedUrl{"
                +
                "shortening='" + shortening + '\''
                +
                ", url='" + url + '\''
                +
                ", uses=" + uses
                +
                ", redirectType=" + redirectType
                +
                '}';
    }
}
