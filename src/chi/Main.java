package chi;

import java.io.BufferedReader;
import java.io.File;
//import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

public class Main {

	public static void main(String[] args) {

		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

		File f1;
		File f2;
		File foldForRes;
		int result;
		//int readByte = 0;
		//byte buff[] = new byte[1024*1024];

		fc.setDialogTitle("select 1st file");
		result = fc.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			f1 = fc.getSelectedFile();

			if (!f1.getName().toLowerCase().endsWith(".txt")) {
				return;
			}
		} else {
			return;
		}

		fc.setDialogTitle("select 2st file");
		result = fc.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			f2 = fc.getSelectedFile();
			if (!f2.getName().toLowerCase().endsWith(".txt")) {
				return;
			}
		} else {
			return;
		}

		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setDialogTitle("select folder for result");

		result = fc.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			foldForRes = fc.getSelectedFile();
		} else {
			return;
		}
		
		File fileTo = new File(foldForRes.getAbsolutePath() + "/" + "result.txt");
		
		//try (FileInputStream fis1 = new FileInputStream(f1); FileInputStream fis2 = new FileInputStream(f2); FileOutputStream fos = new FileOutputStream(fileTo)) {
		try (BufferedReader bf1 = new BufferedReader(new FileReader(f1)); BufferedReader bf2 = new BufferedReader(new FileReader(f2)); FileOutputStream fos = new FileOutputStream(fileTo)) {
			
			String resultLine = "";
			String tempLine;
			String[] words1;
			String[] words2;
			
			while((tempLine = bf1.readLine()) != null) {
				resultLine += tempLine; 
			}
			
			words1 = resultLine.split("\\s+");
			
			resultLine = "";
			
			while((tempLine = bf2.readLine()) != null) {
				resultLine += tempLine; 
			}
			
			words2 = resultLine.split("\\s+");
			
			resultLine = "";
			
			for (String i: words1) {
				for (String j: words2) {
					if (i.equals(j)) {
						resultLine += " ";
						resultLine += i;
					}
				}
			}
			
			fos.write(resultLine.getBytes());
			
			/*fos.write("1st file".getBytes());
			fos.write(System.lineSeparator().getBytes());
			
			for (;(readByte = fis1.read(buff)) > 0;) {
				fos.write(buff,0,readByte);
			}
			
			fos.write(System.lineSeparator().getBytes());	
			fos.write("2nd file".getBytes());
			fos.write(System.lineSeparator().getBytes());  
			
			for (;(readByte = fis2.read(buff)) > 0;) {
				fos.write(buff,0,readByte);
			}*/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
