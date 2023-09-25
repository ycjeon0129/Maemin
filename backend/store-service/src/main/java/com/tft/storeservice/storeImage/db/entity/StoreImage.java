package com.tft.storeservice.storeImage.db.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.tft.storeservice.dibs.db.entity.Dibs;
import com.tft.storeservice.store.db.entity.Store;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class StoreImage{
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

	@LastModifiedDate
	private LocalDateTime updatedAt;


}
