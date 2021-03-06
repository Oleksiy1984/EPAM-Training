package ua.nure.emelianov.Practice4.part4;

import java.io.File;
import java.util.Iterator;
import java.util.regex.Matcher;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	Parser parser;

	@Before
	public void testConstructor() {
		parser = new Parser(new File("part4.txt"));
	}

	@Test
	public void testInvalidConstructor() {
		parser = new Parser(new File("INVALID_FILENAME"));
	}

	@Test
	public void iteratorTest() {
		Iterator<String> it = parser.iterator();
		Matcher m = parser.getMatcher();
		while (it.hasNext()) {
			m.find();
			Assert.assertEquals(parser.getMatcher().group(), it.next());
		}
	}

	@Test(expected = UnsupportedOperationException.class)
	public void iteratorExceptionTest() {
		Iterator<String> it = parser.iterator();
		it.remove();
	}

}
