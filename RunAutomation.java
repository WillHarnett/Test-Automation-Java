package test;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class RunAutomation {
	public static ArrayList<ArrayList<String>> stepsList;
	public static ArrayList<String> eResutlsList;

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileReader.readTestSuite();
	}
}
