package com.humingbird.entity;

import javax.persistence.*;

import com.humingbird.constant.Size;
import com.humingbird.dto.TopNewItemDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name= "top_new_item")
@Getter
@Setter
@ToString
public class TopNewItem {
	
	@Id
	@Column(name = "top_new_item")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private int shoulder;
	
	@Column(nullable = false)
	private int bust;
	
	@Column(name = "top_length",nullable = false)
	private int topLength;
	
	@Enumerated(EnumType.STRING)
	private Size size;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "newItem_id")
	private NewItem newItem;
	
	//왜래키를 넣어준다
	public static TopNewItem createTopNewItem(TopNewItemDto topDto,NewItem newItem) {
		TopNewItem topNewItem = new TopNewItem();
		topNewItem.setBust(topDto.getBust());
		topNewItem.setShoulder(topDto.getShouder());
		topNewItem.setSize(topDto.getSize());
		topNewItem.setTopLength(topDto.getTopLength());
		
		//왜래키 값을 넣어줌
		
		topNewItem.setNewItem(newItem);
		
		return topNewItem;
	}
}
