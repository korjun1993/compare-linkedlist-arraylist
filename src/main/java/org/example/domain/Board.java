package org.example.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Board {
	private Long id;
	private String title;
	private String author;
	private String description;
}
