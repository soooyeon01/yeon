package com.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LikeDTO {
	private int l_no;
	private int c_no;
	private String nickname;
	private int like_cnt;
}
