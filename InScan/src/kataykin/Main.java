package kataykin;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			new Main().example();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void example() throws IOException {

		// Retrieve text from file
		InScan inScan = new InScan("input.txt", '-', ' ');

		// Retrieve text from console
		// InScan inScan = new InScan(System.in, '-', ' ');

		// Remove characters delimiters
		// inScan.addSeparator('_');
		// inScan.removeSeparator('-');

		// Iterate by cache list
		for (String s : inScan) {
			l("obj: " + s);
		}

		// Examples of using different methods
		l("_____");
		l("inScan: " + inScan);
		l("peek: " + inScan.peek());
		l("peekFirst: " + inScan.peekFirst());

		l("_____");
		l("inScan: " + inScan);
		l("poll: " + inScan.poll());
		l("pollFirst: " + inScan.pollFirst());

		l("_____");
		l("inScan: " + inScan);
		l("next: " + inScan.next());
		l("nextDouble: " + inScan.nextDouble());

		l("_____");
		l("inScan: " + inScan);
		l("next: " + inScan.next());
		l("nextDouble: " + inScan.nextDouble());
		l("nextInteger: " + inScan.nextInteger());
		l("nextLong: " + inScan.nextLong());
		l("poll: " + inScan.poll());
		l("pollFirst: " + inScan.pollFirst());
		l("peek: " + inScan.peek());
		l("peekFirst: " + inScan.peekFirst());
		l("peekFirst: " + inScan.peekFirst());
		l("hashCode: " + inScan.hashCode());
		l("isCacheEmpty: " + inScan.isCacheEmpty());
		l("isEmpty: " + inScan.isEmpty());
		l("isNextNumeric: " + inScan.isNextNumeric());

		l("_____");
		// Iterate over all text from a file or InputStream until the 'q' character is
		// found
		while (!inScan.isEmpty()) {
			if (inScan.isNextNumeric())
				l("num: " + inScan.nextDouble());
			else {
				String str = inScan.next();
				l("str: " + str);
				if (str.equalsIgnoreCase("q"))
					break;
			}
		}

		// Closes the stream
		inScan.close();
	}

	private void l(String log) {
		System.out.println(log);
	}
}