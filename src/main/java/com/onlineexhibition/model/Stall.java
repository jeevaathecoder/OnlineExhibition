package com.onlineexhibition.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;


@Data
@Builder
@Entity
@Table(name = "stall")
@AllArgsConstructor
@NoArgsConstructor
public class Stall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stall_name",nullable = false)
    private String stallName;

    @Column(name = "stall_description")
    private String stallDescription;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "brochure_url")
    private String brochureUrl;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotBlank
    private User user;

}

