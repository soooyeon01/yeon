package com.spring.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MembersDTO {
	private String nickname;
	private String email;
	private String pwd;
	private String name;
	private int phone;

}
