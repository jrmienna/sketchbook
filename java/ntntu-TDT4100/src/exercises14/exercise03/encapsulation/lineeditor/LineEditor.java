package exercises14.exercise03.encapsulation.lineeditor;

public class LineEditor {

	private String text = "";
	private int insertionIndex = 0;

	public void left() {
		if (this.insertionIndex > 0) {
			this.insertionIndex--;
		}
	}

	public void right() {
		if (this.insertionIndex < this.text.length()) {
			this.insertionIndex++;
		}
	}

	public void insertString(String s) {
		StringBuilder textBuilder = new StringBuilder(this.text);
		this.text = textBuilder.insert(this.insertionIndex, s).toString();
		this.insertionIndex += s.length();
	}

	public void deleteLeft() {
		if (this.insertionIndex > 0) {
			StringBuilder textBuilder = new StringBuilder(this.text);
			this.text = textBuilder.deleteCharAt(this.insertionIndex - 1)
					.toString();
			this.insertionIndex--;
		}
	}

	public void deleteRight() {
		if (this.insertionIndex <= this.text.length() - 1) {
			StringBuilder textBuilder = new StringBuilder(this.text);
			this.text = textBuilder.deleteCharAt(this.insertionIndex)
					.toString();
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		if (!(insertionIndex < text.length()+1)) {
			insertionIndex = text.length();
		}
	}

	public int getInsertionIndex() {
		return insertionIndex;
	}

	public void setInsertionIndex(int insertionIndex) {
		if (insertionIndex >= 0 && insertionIndex < text.length() + 1) {
			this.insertionIndex = insertionIndex;
		}
	}

	public String toString() {
		String textEnd = this.text.substring(this.insertionIndex);
		String textStart = this.text.substring(0, this.insertionIndex);
		return textStart + "|" + textEnd;
	}
	public static void main(String[] args) {
		LineEditor editor = new LineEditor();
		editor.setText("ABC");
		System.out.println(editor.getText());
		editor.setInsertionIndex(3);
		System.out.println(editor.getInsertionIndex());
		editor.setText("AB");
		System.out.println(editor.getText());
		System.out.println(editor.getInsertionIndex());
		}
}