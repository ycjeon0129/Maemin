package com.tft.storeservice.dibs.db.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

import com.tft.storeservice.store.db.entity.Store;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Dibs{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dibsId;

	@ManyToOne
	private Store store;

	@NotNull
	private Long userId;

	@NotNull
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdDate;

	public Dibs addStore(Store store){
		this.store = store;
		return this;
	}
}
