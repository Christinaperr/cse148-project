package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Scanner;

public class TextbookBag implements Serializable {
	private Textbook[] textbookArray;
	private int nElems;
	Scanner in = new Scanner(System.in);

	public TextbookBag(int maxSize) {
		this.textbookArray = new Textbook[maxSize];
		this.nElems = 0;
	}

	public void add(Textbook textbook) {
		this.textbookArray[nElems++] = textbook;
	}

	public Textbook findByIbsn(String isbn) {
		for (int i = 0; i < nElems; i++) {
			if (isbn.equals(textbookArray[i].getIsbn())) {
				return textbookArray[i];
			}
		}
		return null;
	}

	
	public int removeByIsbn(String isbn) {
		int isbnIndex = -1;
		for (int i = 0; i < nElems; i++) {
			if (isbn.equals(textbookArray[i].getIsbn())) {
				isbnIndex = i;
			}
		}
		if (isbnIndex == -1) {
			return -1;
		} else {
			for (int j = isbnIndex; j < nElems-1; j++) {
				textbookArray[j] = textbookArray[j + 1];
				isbnIndex = j;
			}
		}
		nElems--;
		return isbnIndex;
	}

	public void importData() {
		File file = new File("textbook.txt");
		double price = 0.0;
		try {
			in = new Scanner(file);
			while (in.hasNextLine()) {
				String title = in.nextLine();
				Name authorName = null;
				String[] nameTokens = in.nextLine().split(" ");
				if(nameTokens.length == 2){
					authorName = new Name(nameTokens[0], nameTokens[1]);
				} else {
					authorName = new Name(nameTokens[0], nameTokens[1].charAt(0), nameTokens[2]);
				}
				String publisher = in.nextLine();
				String textbookISBN = in.nextLine();
				String priced = in.nextLine();
				price = Double.parseDouble(priced);
				Textbook textbook = new Textbook(title, authorName, publisher, textbookISBN);
				textbook.setPrice(price);
				add(textbook);
			}
			in.close();
		} catch (FileNotFoundException e) {
		}
	}

	public void exportData(){
		try {
			PrintWriter pw = new PrintWriter("textbook.txt");
			for (int i = 0; i < nElems; i++) {
				Textbook tb = textbookArray[i];
				pw.println(tb.getTitle());
				pw.println(tb.getAuthor());
				pw.println(tb.getPublisher());
				pw.println(tb.getIsbn());
				pw.println(tb.getPrice());
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("Cant write file");
		}
	}

	public void save() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("TextbookBag.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(textbookArray);
			oos.writeObject(nElems);
			oos.close();
		} catch (IOException e) {
			System.out.println("Failure to save textbookBag file");
		}

	}

	public void load() {
		FileInputStream fis;
		try {
			fis = new FileInputStream("TextbookBag.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			textbookArray = (Textbook[]) (ois.readObject());
			nElems = (int) (ois.readObject());
			ois.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
			System.out.println("Failure loading textbookBag file");
		} catch (ClassNotFoundException e) {
		}
	}

	public Textbook[] getTextbookArray() {
		return Arrays.copyOf(textbookArray, nElems);
	}

	public void display() {
		for (int i = 0; i < nElems; i++) {
			System.out.println(textbookArray[i]);
		}
		System.out.println();
	}
}
