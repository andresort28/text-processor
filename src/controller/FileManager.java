package controller;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Hashtable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import view.ContentPanel;
import view.InterfaceEditor;

public class FileManager 
{


	/**
	 * Loads an dictionary to the editor.
	 * <b>Pre<\b> The dictionary must be inicilizaded.
	 * <b>Post<\> A dictionary has been charged.
	 * @param path The path of the file that contains the dictionary.
	 * @param dictionary The hash table with the dictionary.
	 */
	public static void loadDiccionary (String path, Hashtable<String, Integer> dictionary)
	{
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = "";
			while ( (line = br.readLine()) != null) 
			{
				dictionary.put(line, 1);
			}
		}		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	/**
	 * Opens a file to be read in
	 *
	 * @param path
	 * @param name
	 */
	public static String openFile (Component component) 
	{
		String text = "";
		try 
		{
			JFileChooser fc = new JFileChooser(System.getProperty(System.getProperty("user.home") + "/Desktop"));
			fc.setDialogTitle("Select a file");
			int r = fc.showOpenDialog(component);			

			if(r == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				if (file != null)
				{
					String path = file.getAbsolutePath();
					int pos = path.indexOf(".");
					String type = path.substring(pos);
					if(type.equalsIgnoreCase(".txt"))
					{
						BufferedReader in = new BufferedReader(new FileReader(file));
						String line = "";
						while( (line = in.readLine()) != null )
						{
							text += line + "\n";
						}
						in.close();
						return text;
					}
					else
						JOptionPane.showMessageDialog(component, "The file must be a .txt, not ." + type, "Error to open", JOptionPane.ERROR_MESSAGE);
				}
			}
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}		
	}


	/**
	 * Saves a file in the given position
	 */
	public static void saveFile(String text, String path) 
	{
		try
		{
			File file = new File(path);
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(text);
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static String saveAsFile (Component component, String text)
	{
		try 
		{
			JFileChooser fc = new JFileChooser(System.getProperty(System.getProperty("user.home") + "/Desktop"));
			fc.setDialogTitle("Save As");
			int r = fc.showOpenDialog(component);			

			if(r == JFileChooser.APPROVE_OPTION)
			{
				File file = fc.getSelectedFile();
				if (file != null)
				{
					String path = file.getAbsolutePath() + ".txt";
					File file2 = new File(path);
					BufferedWriter out = new BufferedWriter(new FileWriter(file2));
					out.write(text);
					out.close();
					return path;
				}
			}
			return null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}




	public static boolean savePDF (String text)
	{
		
		return true;
	}


	public static void print (final ContentPanel content)
	{
		new Thread()
		{
			 public void run() 
	         {
	     		try
	    		{
	    			PrinterJob printer = PrinterJob.getPrinterJob();
	    			printer.setPrintable(content, printer.defaultPage());
	    			
	    			if(printer.printDialog())
	    				printer.print();
	    		}
	    		catch (Exception e)
	    		{
	    			JOptionPane.showMessageDialog(null, "Error to print", "Error", JOptionPane.ERROR_MESSAGE);
	    		}
	         }
		}.start();
		System.out.println("Entro a imprimir");
		
		
//		PrintJob pjob = getToolkit().getPrintJob(this,"Imprimir Hoja",null);
//		Graphics pg=  pjob.getGraphics();
//		pg.setFont(new Font("SansSerif",Font.PLAIN,10));
//		pg.drawString("Imprimido:",100,100);
//		int inicio=0;
//		int numlineas=1;
//		for (int i=0; i<todo.length();i++)
//		{
//			if((int) todo.charAt(i)==10)
//			{
//				pg.drawString(todo.substring(inicio,i-1),100,100 + (15 * numlineas));
//				inicio=i+1;
//				numlineas ++;
//			}
//		}
//		pg.drawString (todo.substring(inicio,todo.length()),100,100 + (15 * numlineas));
//		pg.dispose (); //Finalizar pagina
//		pjob.end(); //Termina trabajo y tira la pagina
	}


	//	public void crear_PDF()
	//	{
	//		Colocar_Destino();
	//		String path = "";
	//		if( path != null)
	//		{
	//			try 
	//			{
	//				String content = "";
	//				File file = new File (path + ".pdf");
	//				Document document = new Document();
	//				PdfWriter.getInstance(document, new FileOutputStream(file));
	//				document.open();// se abre el documento
	//				document.addTitle(""); // se añade el titulo
	//				document.addAuthor(""); // se añade el autor del documento
	//				document.addSubject(""); //se añade el asunto del documento
	//				document.addKeywords(""); //Se agregan palabras claves  
	//				document.addC(content); // se añade el contendio del PDF
	//				document.close(); //se cierra el PDF&
	//				JOptionPane.showMessageDialog(null,"Documento PDF creado");
	//			}
	//			catch (Exception e) 
	//			{
	//			     e.printStackTrace();
	//			}
	//		}  
	//		
	//		
	//		
	//		 try {
	//		      Document document = new Document();
	//		      PdfWriter.getInstance(document, new FileOutputStream(FILE));
	//		      document.open();
	//		      addMetaData(document);
	//		      addTitlePage(document);
	//		      addContent(document);
	//		      document.close();
	//		    } catch (Exception e) {
	//		      e.printStackTrace();
	//	}
	//	/* abre la ventana de dialogo GUARDAR*/
	//	public void Colocar_Destino(){
	//		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF","pdf","PDF");
	//		JFileChooser fileChooser = new JFileChooser();       
	//		fileChooser.setFileFilter(filter);
	//		int result = fileChooser.showSaveDialog(null);
	//		if ( result == JFileChooser.APPROVE_OPTION ){   
	//			this.ruta_destino = fileChooser.getSelectedFile().getAbsoluteFile();
	//		}
	//	}
}
