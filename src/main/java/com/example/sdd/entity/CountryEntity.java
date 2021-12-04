package com.example.sdd.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NamedQuery(name = "CountryEntity.findAll", query = "Select c from CountryEntity c order by c.id asc")
@NamedQuery(name = "CountryEntity.findByName", query = "Select c from CountryEntity c where c.countryName = :name")
@Table(name = "t_country")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    @ToString.Include
    @Column(name = "country_name", unique = true)
    private String countryName;

    @Valid
    @NotEmpty
    @OneToMany(mappedBy = "countryEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PersonEntity> persons;

}