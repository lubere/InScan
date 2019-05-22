package kataykin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class InScan implements Iterable<String> {

	private BufferedReader br;
	private LinkedList<String> queue;
	private Set<Integer> separators;

	/**
	 * Creates a list of elements from split <code>text</code> by
	 * <code>separator</code> from file by <code>fileName</code>.
	 * 
	 * @param fileName  the name of the file to read from
	 * @param separator An array of characters used as separators (any of them) to
	 *                  split received text
	 * @exception FileNotFoundException if the named file does not exist, is a
	 *                                  directory rather than a regular file, or for
	 *                                  some other reason cannot be opened for
	 *                                  reading.
	 */
	public InScan(String fileName, char... separator) throws FileNotFoundException {
		this(separator);
		br = new BufferedReader(new FileReader(fileName));
	}

	/**
	 * Constructs an empty list and creates an InputStreamReader that uses the
	 * default charset.
	 * 
	 * @param in        An InputStream
	 * @param separator An array of characters used as separators (any of them) to
	 *                  split received text
	 */
	public InScan(InputStream in, char... separator) {
		this(separator);
		br = new BufferedReader(new InputStreamReader(in));
	}

	/**
	 * Creates a list of elements from split <code>text</code> by
	 * <code>separator</code> from <code>file</code>.
	 * 
	 * @param file      the <tt>File</tt> to read from
	 * @param separator An array of characters used as separators (any of them) to
	 *                  split received text
	 * 
	 * @exception FileNotFoundException if the file does not exist, is a
	 *                                  directoryеепн rather than a regular file, or
	 *                                  for some other reason cannot be opened for
	 *                                  reading.
	 */
	public InScan(File f, char... separator) throws FileNotFoundException {
		this(separator);
		br = new BufferedReader(new FileReader(f));
	}

	/**
	 * Default constructor
	 * 
	 * @param separator An array of characters used as separators (any of them) to
	 *                  split received text
	 */
	private InScan(char... arrSep) {
		queue = new LinkedList<>();
		separators = new HashSet<>();
		if (arrSep != null)
			for (char sep : arrSep)
				separators.add(Integer.valueOf(sep));
	}

	/**
	 * Add a new delimiter to the <code>separators</code> set. The characters in the
	 * <code>separators</code> set are unique.
	 */
	public void addSeparator(char separator) {
		separators.add(Integer.valueOf(separator));
	}

	/**
	 * remove a <code>separator</code> char from the <code>separators</code> set.
	 */
	public void removeSeparator(char separator) {
		separators.remove(Integer.valueOf(separator));
	}

	/**
	 * clear <code>separators</code> set.
	 */
	public void removeAllSeparators() {
		separators.clear();
	}

	/**
	 * Retrieves, but does not remove, the first element of the cache list, or
	 * returns {@code null} if this list is empty (only when reading from a file,
	 * otherwise it will wait data from the InputStream).
	 *
	 * @return the first element of the cache list, or {@code null} if this list is
	 *         empty (only when reading from a file)
	 */
	public String peek() {
		return isEmpty() ? null : queue.peekFirst();
	}

	/**
	 * Retrieves, but does not remove, the last element of the cache list, or
	 * returns {@code null} if the cache list is empty (only when reading from a
	 * file, otherwise it will wait data from the InputStream).
	 *
	 * @return the last element of the cache list, or {@code null} if this list is
	 *         empty (if reading from a file)
	 */
	private String peekLast() {
		return isEmpty() ? null : queue.peekLast();
	}

	/**
	 * Retrieves, but does not remove, the first element of the cache list, or
	 * returns {@code null} if this list is empty (only when reading from a file,
	 * otherwise it will wait data from the InputStream).
	 *
	 * @return the first element of the cache list, or {@code null} if this list is
	 *         empty (if reading from a file)
	 */
	public String peekFirst() {
		return isEmpty() ? null : queue.peekFirst();
	}

	/**
	 * Retrieves, but does not remove, the first element of the cache list, or
	 * returns {@code null} if this list is empty (only when reading from a file,
	 * otherwise it will wait data from the InputStream). Retrieves the
	 * {@code Integer} object represented by the string of the first element of the
	 * cache list.
	 *
	 * @return the first element of the cache list parsed to Integer, or
	 *         {@code null} if this list is empty (if reading from a file)
	 * @exception NumberFormatException if the string does not contain a parsable
	 *                                  integer.
	 */
	public Integer peekInteger() {
		String s = peek();
		return s != null ? Integer.parseInt(s.trim()) : null;
	}

	/**
	 * Retrieves, but does not remove, the first element of the cache list, or
	 * returns {@code null} if this list is empty (only when reading from a file,
	 * otherwise it will wait data from the InputStream). Retrieves the {@code Long}
	 * object represented by the string of the first element of the cache list.
	 *
	 * @return the first element of the cache list parsed to Long, or {@code null}
	 *         if this list is empty (if reading from a file)
	 * @throws NumberFormatException if the string does not contain a parsable
	 *                               {@code long}.
	 */
	public Long peekLong() {
		String s = peek();
		return s != null ? Long.parseLong(s.trim()) : null;
	}

	/**
	 * Retrieves, but does not remove, the first element of the cache list, or
	 * returns {@code null} if this list is empty (only when reading from a file,
	 * otherwise it will wait data from the InputStream). Retrieves the
	 * {@code Double} object represented by the string of the first element of the
	 * cache list.
	 *
	 * @return the first element of the cache list parsed to Double, or {@code null}
	 *         if this list is empty (if reading from a file)
	 * @throws NumberFormatException if the first element of this list does not
	 *                               contain a parsable {@code double}.
	 */
	public Double peekDouble() {
		String s = peek();
		return s != null ? Double.parseDouble(s.trim()) : null;
	}

	/**
	 * @return <tt>true</tt> if the cache collection does not contain elements and
	 *         FileReader (when reading from a file) has already received all the
	 *         text from the file.
	 * 
	 *         When using the InputStream, it will wait for data from it.
	 */
	public boolean isEmpty() {
		fillQueue();
		return isCacheEmpty();
	}

	/**
	 * @return <tt>true</tt> if the cache collection contains no elements
	 */
	public boolean isCacheEmpty() {
		return queue.isEmpty();
	}

	/**
	 * If the cache list is empty -> Get the next string line from InputStreamReader
	 * -> Split the text by Splitter elements -> add the received items to the cache
	 * list in the same order of adding items
	 */
	private void fillQueue() {
		try {
			if (isCacheEmpty()) {
				put(br.readLine());
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Split the text by Splitter elements -> add the received items to the cache
	 * list in the same order of adding items
	 */
	private void put(String text) {
		if (text == null || text.length() == 0)
			return;
		char[] arr = text.toCharArray();
		int posStart = 0;
		for (int i = 0; i < arr.length; i++) {
			if (separators.contains((int) arr[i])) {
				if (posStart < i)
					queue.add(text.substring(posStart, i));
				posStart = i + 1;
			}
		}
		if (posStart < text.length()) {
			queue.add(text.substring(posStart, text.length()));
		}
	}

	/**
	 * Retrieves and removes the first element of the cache list, or returns
	 * {@code null} if this list is empty (if reading from a file, otherwise it will
	 * wait data from the InputStream).
	 *
	 * @return the first element of the cache list, or {@code null} if this list is
	 *         empty (if reading from a file)
	 */
	public String next() {
		return isEmpty() ? null : queue.pollFirst();
	}

	/**
	 * Retrieves and removes the first element of the cache list, or returns
	 * {@code null} if this list is empty (if reading from a file, otherwise it will
	 * wait data from the InputStream). Retrieves the {@code Integer} object
	 * represented by the string of the first element of the cache list.
	 *
	 * @return the first element of the cache list parsed to Integer, or
	 *         {@code null} if this list is empty (if reading from a file)
	 * @exception NumberFormatException if the string does not contain a parsable
	 *                                  integer.
	 */
	public Integer nextInteger() {
		String s = next();
		return s != null ? Integer.parseInt(s.trim()) : null;
	}

	/**
	 * Retrieves and removes the first element of the cache list, or returns
	 * {@code null} if this list is empty (only when reading from a file, otherwise
	 * it will wait data from the InputStream). Retrieves the {@code Long} object
	 * represented by the string of the first element of the cache list.
	 *
	 * @return the first element of the cache list parsed to Long, or {@code null}
	 *         if this list is empty (if reading from a file)
	 * @throws NumberFormatException if the string does not contain a parsable
	 *                               {@code long}.
	 */
	public Long nextLong() throws NumberFormatException {
		String s = next();
		return s != null ? Long.parseLong(s.trim()) : null;
	}

	/**
	 * Retrieves and removes the first element of the cache list, or returns
	 * {@code null} if this list is empty (if reading from a file, otherwise it will
	 * wait data from the InputStream). Retrieves the {@code Double} object
	 * represented by the string of the first element of the cache list.
	 *
	 * @return the first element of the cache list parsed to Double, or {@code null}
	 *         if this list is empty (if reading from a file)
	 * @throws NumberFormatException if the first element of the cache list does not
	 *                               contain a parsable {@code double}.
	 */
	public Double nextDouble() throws NumberFormatException {
		String s = next();
		return s != null ? Double.parseDouble(s.trim()) : null;
	}

	/**
	 * @return {@code true} if next (first element in the head) is numeric. Does not
	 *         remove next item in the cache list. If the cache list is empty -> get
	 *         next line from InputStreamReader.
	 */
	public boolean isNextNumeric() {
		String s = isEmpty() ? null : peek();
		return s != null && s.trim().matches("-?\\d+(\\.\\d+)?");
	}

	/**
	 * Retrieves and removes the first element of the cache list, or returns
	 * {@code null} if this list is empty (if reading from a file, otherwise it will
	 * wait data from the InputStream).
	 *
	 * @return the first element of the cache list, or {@code null} if this list is
	 *         empty (if reading from a file)
	 */
	public String poll() {
		return next();
	}

	/**
	 * Retrieves and removes the first element of the cache list, or returns
	 * {@code null} if this list is empty (if reading from a file, otherwise it will
	 * wait data from the InputStream).
	 *
	 * @return the first element of the cache list, or {@code null} if this list is
	 *         empty (only when reading from a file)
	 */
	public String pollFirst() {
		return next();
	}

	/**
	 * Retrieves and removes the last element of the cache list, or returns
	 * {@code null} if this list is empty (only when reading from a file, otherwise
	 * it will wait data from the InputStream).
	 *
	 * @return the last element of the cache list, or {@code null} if this list is
	 *         empty (only when reading from a file)
	 */
	private String pollLast() {
		return isEmpty() ? null : queue.pollLast();
	}

	/**
	 * Returns an iterator over the elements in cache list (in proper sequence) by
	 * the current cache list. If it's empty -> retrieve next line of text (if
	 * reading from a file or wait next line from the InputStream), split it to
	 * elements by <code>separators</code> and add them to cache list and then
	 * return an iterator.
	 * <p>
	 *
	 * This implementation merely returns a list iterator over the cache list.
	 *
	 * @return an iterator over the elements in this list (in proper sequence)
	 */
	@Override
	public Iterator<String> iterator() {
		fillQueue();
		return queue.iterator();
	}

	/**
	 * Returns a string representation of the cache collection. The string
	 * representation consists of a list of the cache collection's elements in the
	 * order they are returned by its iterator, enclosed in square brackets
	 * (<tt>"[]"</tt>). Adjacent elements are separated by the characters
	 * <tt>", "</tt> (comma and space). If it's empty -> retrieve next line of text
	 * (if reading from a file or wait next line from the InputStream), split it to
	 * elements by <code>separators</code> and add them to cache list and then
	 * return <code>toString</code> method from the cache list.
	 *
	 * @return a string representation of the cache collection
	 */
	@Override
	public String toString() {
		fillQueue();
		return queue.toString();
	}

	/**
	 * Closes the stream and releases any system resources associated with it. Once
	 * the stream has been closed, further next(), poll(), peek(), isNextNumeric() invocations will
	 * throw an IOException.
	 *
	 * @exception IOException If an I/O error occurs
	 */
	public void close() throws IOException {
		br.close();
		queue.clear();
		separators.clear();
	}
}
