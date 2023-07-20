package com.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReplyDTO {
	private int r_no;
	private int c_no;
	private String nickname;
	private String rcontent;
	private String reg_date;
}
