package uk.ac.reading.tq011338.commandlines;

import android.widget.MultiAutoCompleteTextView.Tokenizer;

/**
 * 
 * @author Eldhose M Babu
 *         http://stackoverflow.com/questions/22013784/auto-complete
 *         -text-view-does-not-show-suggestions
 * 
 */
public class NewLineTokenizer implements Tokenizer {

	private final char delimiter = '\n';

	public int findTokenStart(CharSequence text, int cursor) {
		int i = cursor;

		while (i > 0 && text.charAt(i - 1) != delimiter) {
			i--;
		}
		while (i < cursor && text.charAt(i) == delimiter) {
			i++;
		}

		return i;
	}

	public int findTokenEnd(CharSequence text, int cursor) {
		int i = cursor;
		int len = text.length();

		while (i < len) {
			if (text.charAt(i) == delimiter) {
				return i;
			} else {
				i++;
			}
		}

		return len;
	}

	public CharSequence terminateToken(CharSequence text) {
		int i = text.length();
		while (i > 0 && text.charAt(i - 1) == delimiter) {
			i--;
		}

		return text;

	}

}
