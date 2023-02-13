package com.humingbird.entity;

import javax.persistence.*;


import com.humingbird.constant.Size;
import com.humingbird.dto.OuterNewItemDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="outer_new_item")
@Getter
@Setter
@ToString
public class OuterNewItem {

	@Id
	@Column(name = "outer_new_item")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long TopNewItemId;
	
	@Column(nullable = false)
	private int shoulder;
	
	@Column(nullable = false)
	private int bust;
	
	@Column(name = "top_length", nullable = false)
	private int topLength;
	
	@Enumerated(EnumType.STRING)
	private Size size;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "newItem_id")
	private NewItem newItem;
	
	//왜래키를 넣어줌
	public static OuterNewItem createouterNewItem(OuterNewItemDto outerDto, NewItem newItem) {
		OuterNewItem outerNewItem = new OuterNewItem();
		outerNewItem.setBust(outerDto.getBust());
		outerNewItem.setShoulder(outerDto.getShouder());
		outerNewItem.setSize(outerDto.getSize());
		outerNewItem.setTopLength(outerDto.getTopLength());
		
		//외래키 값을 넣어줌
		
		outerNewItem.setNewItem(newItem);
		
		return outerNewItem;
		
	}
	
}
