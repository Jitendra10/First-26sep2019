package com.example.demo.test;

public class Parameter {

	private long parameter1;
	private double parameter2;
	private int parameter3;
	private long parameter4;
	private double parameter5;
	private int parameter6;
	private int parameter7;

	public Parameter() {
		super();
	}

	public Parameter(long parameter1, double parameter2, int parameter3, long parameter4, double parameter5,
			int parameter6, int parameter7) {
		super();
		this.parameter1 = parameter1;
		this.parameter2 = parameter2;
		this.parameter3 = parameter3;
		this.parameter4 = parameter4;
		this.parameter5 = parameter5;
		this.parameter6 = parameter6;
		this.parameter7 = parameter7;
	}

	public long getParameter1() {
		return parameter1;
	}

	public void setParameter1(long parameter1) {
		this.parameter1 = parameter1;
	}

	public double getParameter2() {
		return parameter2;
	}

	public void setParameter2(double parameter2) {
		this.parameter2 = parameter2;
	}

	public int getParameter3() {
		return parameter3;
	}

	public void setParameter3(int parameter3) {
		this.parameter3 = parameter3;
	}

	public long getParameter4() {
		return parameter4;
	}

	public void setParameter4(long parameter4) {
		this.parameter4 = parameter4;
	}

	public double getParameter5() {
		return parameter5;
	}

	public void setParameter5(double parameter5) {
		this.parameter5 = parameter5;
	}

	public int getParameter6() {
		return parameter6;
	}

	public void setParameter6(int parameter6) {
		this.parameter6 = parameter6;
	}

	public int getParameter7() {
		return parameter7;
	}

	public void setParameter7(int parameter7) {
		this.parameter7 = parameter7;
	}

	@Override
	public String toString() {
		return "Parameter [parameter1=" + parameter1 + ", parameter2=" + parameter2 + ", parameter3=" + parameter3
				+ ", parameter4=" + parameter4 + ", parameter5=" + parameter5 + ", parameter6=" + parameter6
				+ ", parameter7=" + parameter7 + "]";
	}

	
}
