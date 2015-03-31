package model;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.FileManager;

public class Processor 
{

	public final static int AMERICAN_ENGLISH = 0;
	public final static int BRITISH_ENGLISH = 1;
	public final static int FRENCH = 2;
	public final static int GERMAN = 3;
	public final static int SPANISH = 4;

	private Hashtable<String, Integer> dictionary;
	private Hashtable<String, WrongWord> errors;
	private ArrayList<String> omitted;

	private int language;
	private ArrayList<String> languages;

	private String path;

	public Processor ()
	{
		language = SPANISH;

		languages = new ArrayList<String>();		
		languages.add("English (American)");
		languages.add("English (British)");
		languages.add("French");
		languages.add("German");
		languages.add("Spanish");

		dictionary = new Hashtable<String, Integer>();
		errors = new Hashtable<String, WrongWord>();
		omitted = new ArrayList<String>();

		path = "dic/spanish.txt";
		FileManager.loadDiccionary(path, dictionary);
	}

	public void setUpLanguage (int language)
	{
		switch (language)
		{			
		case AMERICAN_ENGLISH:
			this.language = AMERICAN_ENGLISH;
			path = "dic/american.txt";
			break;
		case BRITISH_ENGLISH:
			this.language = BRITISH_ENGLISH;
			path = "dic/british.txt";
			break;
		case FRENCH:
			this.language = FRENCH;
			path = "dic/french.txt";
			break;
		case GERMAN:
			this.language = GERMAN;
			path = "dic/german.txt";
			break;
		case SPANISH:
			this.language = SPANISH;
			path = "dic/spanish.txt";
			break;
		}
		dictionary.clear();
		FileManager.loadDiccionary (path, dictionary);
	}	


	public boolean verifyWord (String word)
	{
		if(dictionary.containsKey(word.toLowerCase().trim()))
			return true;
		return false;
		
	}
	
	public boolean isOmitted (String word)
	{
		return omitted.contains(word);
	}


	public ArrayList<String> combinations (String word)
	{
		ArrayList<String> result = new ArrayList<String>();  

		for(int i=0; i < word.length(); ++i) result.add(word.substring(0, i) + word.substring(i+1));  
		for(int i=0; i < word.length()-1; ++i) result.add(word.substring(0, i) + word.substring(i+1, i+2) + word.substring(i, i+1) + word.substring(i+2));  
		for(int i=0; i < word.length(); ++i) for(char c='a'; c <= 'z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i+1));  
		for(int i=0; i <= word.length(); ++i) for(char c='a'; c <= 'z'; ++c) result.add(word.substring(0, i) + String.valueOf(c) + word.substring(i)); 
		
		return result;
	}
	
	public ArrayList<String> getRealWords (ArrayList<String> combinations)
	{
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < combinations.size(); i++) 
		{
			String word = (String)combinations.get(i);
			if(verifyWord(word))
				list.add(word);
		}
		return list;
	}
	
	
	
	public int calculateDistance (String word1, String word2) 
	{
		char[] str1 = word1.toCharArray();
		char[] str2 = word2.toCharArray();
		
		int [][]distance = new int[str1.length+1][str2.length+1];

		for(int i=0;i<=str1.length;i++)
		{
			distance[i][0]=i;
		}
		for(int j=0;j<=str2.length;j++)
		{
			distance[0][j]=j;
		}
		for(int i=1;i<=str1.length;i++)
		{
			for(int j=1;j<=str2.length;j++)
			{ 
				distance[i][j]= minimum(distance[i-1][j]+1,
						distance[i][j-1]+1,
						distance[i-1][j-1]+
						((str1[i-1]==str2[j-1])?0:1));
			}
		}
		return distance[str1.length][str2.length];

	}
	
	
	public int minimum(int a, int b, int c) 
	{
		if(a<=b && a<=c)
			return a;
		if(b<=a && b<=c)
			return b;
		return c;
	}

	public ArrayList<String> getPossible (String word)
	{
		ArrayList<String> combinations = combinations(word);
		ArrayList<String> reals = getRealWords(combinations);		
		ArrayList<String> distances = new ArrayList<String>();
		
		for (int i = 0; i < reals.size(); i++) 
		{
			String option = reals.get(i);
			int n = calculateDistance(option, word);
			distances.add(option + "," + n);
		}		
		ArrayList<String> finals = new ArrayList<String>();
		
		for (int i = distances.size()-1; i >=0; i--) 
		{
			String option = distances.get(i);
			int c = option.indexOf(",");
			int n = Integer.parseInt(option.substring(c+1));
			option = option.substring(0, c);
			
			if(!finals.contains(option))
			{
				if(n > finals.size()-1)
					finals.add(option);
				else
					finals.add(n, option);
			}			
		}
		return finals;
	}
	
	
	public Hashtable<String, Integer> getDictionary ()
	{
		return dictionary;
	}

	public int getLanguage ()
	{
		return language;
	}

	public ArrayList<String> getLanguages ()
	{
		return languages;
	}
	
}
