package com.humingbird.entity;

import javax.persistence.*;

import com.humingbird.constant.Category;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name ="newItem")
@Getter
@Setter
@ToString
public class NewItem extends BaseEntity{
	
	@Id
	@Column(name = "newItem_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "brand_name" , nullable = false, length = 20)
	private String brandName;
	
	@Column(name = "clothe_name" , nullable = false, length = 30)
	private String clotheName;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@Column(nullable = false)
	private int price;
	
	@Column(nullable =false)
	private String detail;
}
	