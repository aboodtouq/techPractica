package com.spring.techpractica.model.entity;

import com.spring.techpractica.model.PlatformName;
import com.spring.techpractica.model.SocialAccountId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SOCIAL_ACCOUNTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@IdClass(SocialAccountId.class)
public class SocialAccount {


    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "platform_name")
    private PlatformName platformName;

    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name = "profile_url")
    private String profileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
