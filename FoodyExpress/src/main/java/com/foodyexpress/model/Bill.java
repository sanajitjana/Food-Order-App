package com.foodyexpress.model;



import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer billId;
	private LocalDateTime billDate;
	private Double totalCost;
	private Integer totalItem;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private OrderDetails order;
	
	
	public Bill() {
		// TODO Auto-generated constructor stub
	}


	public Bill(Integer billId, LocalDateTime billDate, Double totalCost, Integer totalItem, OrderDetails order) {
		super();
		this.billId = billId;
		this.billDate = billDate;
		this.totalCost = totalCost;
		this.totalItem = totalItem;
		this.order = order;
	}


	public Integer getBillId() {
		return billId;
	}


	public void setBillId(Integer billId) {
		this.billId = billId;
	}


	public LocalDateTime getBillDate() {
		return billDate;
	}


	public void setBillDate(LocalDateTime billDate) {
		this.billDate = billDate;
	}


	public Double getTotalCost() {
		return totalCost;
	}


	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}


	public Integer getTotalItem() {
		return totalItem;
	}


	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}


	public OrderDetails getOrder() {
		return order;
	}


	public void setOrder(OrderDetails order) {
		this.order = order;
	}


	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", billDate=" + billDate + ", totalCost=" + totalCost + ", totalItem="
				+ totalItem + ", order=" + order + "]";
	}
	
	
	
}