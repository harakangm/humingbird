package com.humingbird.entity;

import javax.persistence.*;


import com.humingbird.constant.Size;
import com.humingbird.dto.BottomNewItemDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name ="bottom_new_item")
public class BottomNewItem {
	
	@Id
	@Column(name = "top_new_item")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long TopNewItemId;
	
	@Column(nullable = false)
	private int waistline;
	
	@Column(name="upper_thight", nullable = false)
	private int upperThight;
	
	@Column(name = "top_length", nullable = false)
	private int legLength;
	
	@Enumerated(EnumType.STRING)
	private Size size;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "new_Item_id")
	private NewItem newItem;
	
	//왜래키를 넣어준다
	public static BottomNewItem createBottomNewItem(BottomNewItemDto BottomDto,NewItem newItem) {
		BottomNewItem bottomNewItem = new BottomNewItem();
		bottomNewItem.setLegLength(BottomDto.getLegLength());
		bottomNewItem.setUpperThight(BottomDto.getUpperThight());
		bottomNewItem.setSize(BottomDto.getSize());
		bottomNewItem.setWaistline(BottomDto.getWaistline());
		
		bottomNewItem.setNewItem(newItem);
		
		return bottomNewItem;
		
	}
}
