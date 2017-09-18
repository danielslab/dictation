package dictation.model;

public class QuestionModel {
	private String audioPath;
	private String answerText;

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
		return "QuestionModel [audioPath=" + audioPath + ", answerText=" + answerText + "]";
	}
}
