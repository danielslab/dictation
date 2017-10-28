package dictation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="records")
public class Record {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Question question;
	@Column
	private Long elapsedTime;
	@Column(columnDefinition="TEXT")
	private String answeredText;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public Long getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public String getAnsweredText() {
		return answeredText;
	}
	public void setAnsweredText(String answeredText) {
		this.answeredText = answeredText;
	}

	@Override
	public String toString() {
		return "Record [id=" + id + ", user=" + user + ", question=" + question + ", elapsedTime=" + elapsedTime
				+ ", answeredText=" + answeredText + "]";
	}
}
