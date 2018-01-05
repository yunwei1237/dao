package com.tcf.entity;

import java.sql.Date;

import org.tcf.annotation.Column;
import org.tcf.annotation.Entity;
import org.tcf.annotation.Id;
import org.tcf.annotation.PrimaryKeyType;

/**
 * +-------------+--------------+------+-----+---------+-------+
| Field       | Type         | Null | Key | Default | Extra |
+-------------+--------------+------+-----+---------+-------+
| StudentNo   | varchar(50)  | NO   | PRI | NULL    |       |
| LoginPwd    | varchar(20)  | NO   |     | NULL    |       |
| StudentName | varchar(50)  | NO   | MUL | NULL    |       |
| Sex         | char(2)      | NO   |     | NULL    |       |
| GradeId     | int(11)      | NO   |     | NULL    |       |
| Phone       | varchar(255) | NO   |     | NULL    |       |
| Address     | varchar(255) | YES  |     | NULL    |       |
| BornDate    | datetime     | YES  |     | NULL    |       |
| Email       | varchar(50)  | YES  |     | NULL    |       |
+-------------+--------------+------+-----+---------+-------+
 * @author Archer Tan
 *
 */
@Entity
public class Student {
	@Id(type=PrimaryKeyType.assigned)
	@Column
	private String studentNo;
	@Column
	private String loginPwd;
	@Column
	private String studentName;
	@Column
	private String sex;
	@Column
	private Integer gradeId;
	@Column
	private String phone;
	@Column
	private String address;
	@Column
	private Date bornDate;
	@Column
	private String email;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(String studentNo, String loginPwd, String studentName,
			String sex, Integer gradeId, String phone, String address,
			Date bornDate, String email) {
		super();
		this.studentNo = studentNo;
		this.loginPwd = loginPwd;
		this.studentName = studentName;
		this.sex = sex;
		this.gradeId = gradeId;
		this.phone = phone;
		this.address = address;
		this.bornDate = bornDate;
		this.email = email;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBornDate() {
		return bornDate;
	}
	public void setBornDate(Date bornDate) {
		this.bornDate = bornDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Student [studentNo=" + studentNo + ", loginPwd=" + loginPwd
				+ ", studentName=" + studentName + ", sex=" + sex
				+ ", gradeId=" + gradeId + ", phone=" + phone + ", address="
				+ address + ", bornDate=" + bornDate + ", email=" + email + "]";
	}
}
