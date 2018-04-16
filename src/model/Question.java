package model;

public class Question {
	private int questionid;
	private String question;
	private String dapan;
	private String dapantv;
	private String topic;

	public Question() {
		super();
	}

	public Question(int questionid, String question, String dapan, String dapantv, String topic) {
		super();
		this.questionid = questionid;
		this.question = question;
		this.dapan = dapan;
		this.dapantv = dapantv;
		this.topic = topic;
	}

	public int getQuestionid() {
		return questionid;
	}

	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getDapan() {
		return dapan;
	}

	public void setDapan(String dapan) {
		this.dapan = dapan;
	}

	public String getDapantv() {
		return dapantv;
	}

	public void setDapantv(String dapantv) {
		this.dapantv = dapantv;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}
