package com.foodyexpress.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String billId;
	private LocalDateTime billDate;
	private OrderDetails order;
	private Integer totalItem;
	private double totalCost;

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public LocalDateTime getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDateTime billDate) {
		this.billDate = billDate;
	}

	public OrderDetails getOrder() {
		return order;
	}

	public void setOrder(OrderDetails order) {
		this.order = order;
	}

	public Integer getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", billDate=" + billDate + ", order=" + order + ", totalItem=" + totalItem
				+ ", totalCost=" + totalCost + "]";
	}

	public Bill(String billId, LocalDateTime billDate, OrderDetails order, Integer totalItem, double totalCost) {
		super();
		this.billId = billId;
		this.billDate = billDate;
		this.order = order;
		this.totalItem = totalItem;
		this.totalCost = totalCost;
	}

	public Bill() {
		// TODO Auto-generated constructor stub
	}

}
