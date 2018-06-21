package com.example.springbootdemo.pojo.paopao;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String teacheremail;

	private String role;

	private Date createtime;

	private Date updatetime;
}
