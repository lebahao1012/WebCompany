package com.company.web.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "post_date")
    private LocalDate postDate;

    @Column(name = "author")
    private String author;

    @Lob
    @Column(name = "detail", columnDefinition = "TEXT")
    private String detail;

    @Column(name = "short_detail")
    private String short_detail;

    @Column(name = "datestamp")
    private String datestamp;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @PrePersist
    public void prePersist() {
        // Set the current date as the datestamp before persisting the entity
        this.datestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }

}
