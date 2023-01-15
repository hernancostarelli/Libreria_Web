package com.example.library.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "editorial")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Editorial {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "editorial", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Book> bookList = new ArrayList<>();

    @OneToMany(mappedBy = "editorial", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Author> authorList = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false)
    private Date creationDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modification_date", nullable = false)
    private Date modificationDate = new Date();

    @Column(name = "deleted", nullable = false)
    private boolean deleted = Boolean.FALSE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Editorial editorial = (Editorial) o;
        return id != null && Objects.equals(id, editorial.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}