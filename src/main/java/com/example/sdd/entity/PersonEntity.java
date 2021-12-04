package com.example.sdd.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NamedQuery(name = "PersonEntity.findAll", query = "Select c from PersonEntity c order by c.id asc")
@NamedQuery(name = "PersonEntity.findByName", query = "Select c from PersonEntity c where c.personName = :name")
@Table(name = "t_person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "person_name", unique = true)
    private String personName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity countryEntity;
}
