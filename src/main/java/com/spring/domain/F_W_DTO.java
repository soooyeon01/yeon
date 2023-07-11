package com.spring.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class F_W_DTO {
	private int favoritew_no;
    private String nickname;
    private Date favoritew_reg_date;
    private int with_pet_no;
    private String building;
    private String road;
    private String tel;
}
