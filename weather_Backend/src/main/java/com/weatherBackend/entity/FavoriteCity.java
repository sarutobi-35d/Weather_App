package com.weatherBackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "favoriteCity", uniqueConstraints = {
        @UniqueConstraint(name = "uk_user_city", columnNames = {
                "user_id", "city_id"
        })
})
@Getter
@Setter
public class FavoriteCity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city_id", nullable = false)
    private Long cityId;

    @Column(name = "city_name", nullable = false)
    private String cityName;

    @Column(nullable = false)
    private String country;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
