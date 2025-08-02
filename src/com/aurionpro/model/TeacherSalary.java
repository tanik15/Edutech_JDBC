package com.aurionpro.model;

import java.time.LocalDate;

public class TeacherSalary {
	private int salaryId;
    private int teacherId;
    private double salaryAmount;
    private LocalDate payDate;
    private double bonus;
    private double deductions;

    
    public TeacherSalary() {
    }

    
    public TeacherSalary(int salaryId, int teacherId, double salaryAmount, LocalDate payDate, double bonus, double deductions) {
        this.salaryId = salaryId;
        this.teacherId = teacherId;
        this.salaryAmount = salaryAmount;
        this.payDate = payDate;
        this.bonus = bonus;
        this.deductions = deductions;
    }

 
    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    @Override
    public String toString() {
        return "TeacherSalary{" +
                "salaryId=" + salaryId +
                ", teacherId=" + teacherId +
                ", salaryAmount=" + salaryAmount +
                ", payDate=" + payDate +
                ", bonus=" + bonus +
                ", deductions=" + deductions +
                '}';
    }
}

