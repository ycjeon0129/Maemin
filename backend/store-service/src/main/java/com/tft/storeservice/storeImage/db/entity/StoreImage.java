package com.tft.storeservice.storeImage.db.entity;

import com.tft.storeservice.store.db.entity.Store;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class StoreImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeImageId;

    @ManyToOne
    private Store store;

    @NotNull
    private String storePicureUrl;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdDate;

    public StoreImage addStore(Store store) {
        this.store = store;
        return this;
    }
}
