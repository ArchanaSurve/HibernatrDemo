package com.scp.hibernateassignment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="StudInfo")
public class Student {
	
	@Id
	@Column(name="studId")
	private int sId;
	
	@Column(name="studName")
	private String sName;
	
	@Column(name="studAge")
	private int sAge;
	
	@Column(name="studAddr")
	private String sAddr;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int sId, String sName, int sAge, String sAddr) {
		super();
		this.sId = sId;
		this.sName = sName;
		this.sAge = sAge;
		this.sAddr = sAddr;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public int getsAge() {
		return sAge;
	}

	public void setsAge(int sAge) {
		this.sAge = sAge;
	}

	public String getsAddr() {
		return sAddr;
	}

	@Override
	public String toString() {
		return "Student [sId=" + sId + ", sName=" + sName + ", sAge=" + sAge + ", sAddr=" + sAddr + "]";
	}

	public void setsAddr(String sAddr) {
		this.sAddr = sAddr;
	}
	}
