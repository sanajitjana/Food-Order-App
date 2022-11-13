package com.foodyexpress.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemCreateDTO {
	private String itemName;
	private String catergoryName;
	private Double cost;
}
