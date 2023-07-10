package com.spring.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeDTO {
	private int notice_no;
    private String nickname;
    private String notice_title;
    private String notice_content;
    private Date notice_reg_date;
}
