package com.tcf.entity;

import org.tcf.annotation.Column;
import org.tcf.annotation.Entity;
import org.tcf.annotation.Id;
import org.tcf.annotation.PrimaryKeyType;

@Entity(catelog="myschool")
public class Grade {
	@Id(type=PrimaryKeyType.autoIncrement)
	@Column
	private Integer gradeId;
	@Column
	private String gradeName;
	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Grade(Integer gradeId, String gradeName) {
		super();
		this.gradeId = gradeId;
		this.gradeName = gradeName;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", gradeName=" + gradeName + "]";
	}
}
