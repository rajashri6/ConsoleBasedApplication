package com.miniproject.question;


public class Result {
    private int resultId;
    private int studentId;
    private int score;
	public int getResultId() {
		return resultId;
	}
	public void setResultId(int resultId) {
		this.resultId = resultId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Result(int resultId, int studentId, int score) {
		super();
		this.resultId = resultId;
		this.studentId = studentId;
		this.score = score;
	}
	public Result() {
		// TODO Auto-generated constructor stub
	}

    
}

