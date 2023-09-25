package com.tft.storeservice.menuoption.db.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.tft.storeservice.menu.db.entity.Menu;
import com.tft.storeservice.store.db.entity.Store;

@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class MenuOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menuOptionId;

	@ManyToOne
	private Menu menu;

	@NotNull
	private String option;

	@NotNull
	private String content;

	@NotNull
	private int price;

	@CreatedDate
	@Column(updatable = false, nullable = false)
	private LocalDateTime createdDate;

	private String status;
}
