package com.example.statemachine.domain.entity;


import com.example.statemachine.domain.enums.RecordStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<ID extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = -75829375235L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Nationalized
    @Column(length = 256)
    private String title;

    @Nationalized
    @Column(length = 1024)
    private String description;

    @Column(nullable = false)
    private Locale locale = Locale.getDefault();

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    private UUID createdBy;

    @LastModifiedBy
    private UUID lastModifiedBy;

    @Column(nullable = false)
    private RecordStatus recordStatus = RecordStatus.ACTIVE;


}
