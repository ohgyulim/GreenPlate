package com.team404x.greenplate.orders.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.team404x.greenplate.user.address.entity.Address;
import com.team404x.greenplate.user.model.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;

	private LocalDateTime orderDate;

	private Long totalPrice;

	private Integer totalQuantity;

	private String orderState;

	private Boolean refundYn;

	@ColumnDefault("false")
	private Boolean delYn;

	@CreatedDate
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime modifiedDate;

	@OneToMany(mappedBy = "orders")
	private List<OrderDetail> orderDetails = new ArrayList<>();

	public void orderState(OrderStatus orderStatus){
		orderState = orderStatus.toString();
	}

	public void refundOrder(){
		refundYn = true;
	}
}