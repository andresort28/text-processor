import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.text.html.Option;

import junit.framework.TestCase;
import model.Processor;

public class ProcessorTest extends TestCase{

	private Processor processor;

	private Hashtable<String, Integer> dict;

	ArrayList<String> combinatios;

	/**
	 * 
	 */
	public void caseOne(){
		processor = new Processor();
		dict = processor.getDictionary();
		try {
			FileReader fr = new FileReader(new File("./testDoc/spanish-test.txt"));
			Scanner sc = new Scanner(fr);

			while(sc.hasNext()){
				dict.put(sc.next(), 1);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
	/**
	 * 
	 * @throws FileNotFoundException If dosen't found the tet file.
	 */
	public void caseTwo() throws FileNotFoundException{
		processor = new Processor();
		combinatios = new ArrayList<String>();
		FileReader reader = new FileReader("./testDoc/options.txt");
		Scanner sc = new Scanner(reader);

		while(sc.hasNext()){
			combinatios.add(sc.next());
		}
	}
	/**
	 * Tests the load of the dicctionasys method from the model package.
	 * Adds word to the the dictionary and then proves if it exists in the hashtable.
	 */
	public void testDict(){
		caseOne();
		assertTrue("The word doesn't exist", dict.containsKey("ababillarse"));
		assertTrue("The word doesn't exist", dict.containsKey("cabal"));
		assertFalse("The word mustn't exist", dict.containsKey("zapato"));		
	}
	/**
	 * Tests the verifyWord method from the model package.
	 */
	public void testVerifyWord(){
		caseOne();
		assertTrue("The word doesn't exist", processor.verifyWord("aarónica"));
		assertTrue("The word doesn't exist", processor.verifyWord("babatel"));
		assertFalse("The word mustn't exist", processor.verifyWord("corazón"));
		assertTrue("The word doesn't exist",processor.verifyWord("a") );
		assertFalse("The word mustn't exist", processor.verifyWord("parangarucutirimicuaro"));
	}
	/**
	 * Test the combinatios of a wrong word.
	 */
	public void  testCombinatios(){
		try {
			caseTwo();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> optcion = processor.combinations("amora");
		assertTrue("The word dosen't exist", optcion.contains(combinatios.get(1)));
		assertTrue("The word dosent't exist", optcion.contains(combinatios.get(6)));
	}

	/**
	 * Test the distance between words
	 */
	public void testCalculateDistance(){
		caseOne();		
		//the distance between amora and amor must be 1
		assertEquals(1,processor.calculateDistance("amora", "amor"));
		// the distance between roma and amor must be 4
		assertEquals(4, processor.calculateDistance("roma","amor"));
		//The distance between ababillarse and ababol muste be 6
		assertEquals(6,processor.calculateDistance("ababillarse", "ababol"));
	}
}
