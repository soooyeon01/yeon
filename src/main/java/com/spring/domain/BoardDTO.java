package com.spring.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class BoardDTO {
	private int b_no;
	private String title;
	private String content;
	private Date reg_date;
	private Date up_date;
	private int m_no;
	private String id;
}
