package com.foodyexpress.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemDTO {

	private Integer itemId;
	private Integer catergoryId;
	private String itemName;
	private Integer quantity;
	private Double cost;

}
