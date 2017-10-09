package dictation.model;

public class QuestionModel {
	private Integer id;
	private String name;
	private String audioPath;
	private String answerText;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAudioPath() {
		return audioPath;
	}
	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}

	@Override
	public String toString() {
		return "QuestionModel [name=" + name + ", audioPath=" + audioPath + ", answerText=" + answerText + "]";
	}
}
