package com.payroll.model;

public class Employee {
	private int empId;
	private String empName;
	private String designation;
	private double basicSalary;
	private double hra;
	private double da;
	private double deductions;
	private double netSalary;
	//Default Constructor
	public Employee() {
		
	}
	
	public Employee(int empId,String empName,String designation,
			double basicSalary,double hra,double da,double deductions,
			double netSalary) {
		this.empId=empId;
		this.empName=empName;
		this.designation=designation;
		this.basicSalary=basicSalary;
		this.hra=hra;
		this.da=da;
		this.deductions=deductions;
		this.netSalary=netSalary;
		
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public double getHra() {
		return hra;
	}

	public void setHra(double hra) {
		this.hra = hra;
	}

	public double getDa() {
		return da;
	}

	public void setDa(double da) {
		this.da = da;
	}

	public double getDeductions() {
		return deductions;
	}

	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	public double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(double netSalary) {
		this.netSalary = netSalary;
	}
	
	

}
