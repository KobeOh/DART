package duplicateAddressRemover;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import objectTypes.Patient;

public class OpenCsvBeanToCsvTest {
	public static void main(String[] args) throws IOException {
		
		
		/*
		 * This first section of code is identical to OpenCsvParseToBeanTest. This reads the Test Data.csv file, then converts it to a 
		 * list of patient objects based on the information from that file. 
		 */
		
		CSVReader reader = new CSVReader(new FileReader("Test Data.csv"));
		
		ColumnPositionMappingStrategy<Patient> beanStrategy = new ColumnPositionMappingStrategy<Patient>();
		beanStrategy.setType(Patient.class);
		beanStrategy.setColumnMapping(new String[] {"lastName","firstName","prefName","street", "street2",
													"city", "state", "zip"});
		
		CsvToBean<Patient> csvToBean = new CsvToBean<Patient>();
		
		List<Patient> patients = csvToBean.parse(beanStrategy, reader);
		
		/*
		 * This will print the resulting CSV file
		 */
		/*
		StringWriter writer = new StringWriter();
		CSVWriter csvWriter = new CSVWriter(writer);
		
		List<String[]> data = toStringArray(patients);

		csvWriter.writeAll(data);

		csvWriter.close();
		
		System.out.println(writer);
		*/
		
		
		/*
		 * This second section of code takes the list of patient objects, then converts it to a .csv file, which is then stored at Test Write.csv
		 */
		FileWriter writer = new FileWriter("Test Write.csv");
		CSVWriter csvWriter = new CSVWriter(writer);
		
		List<String[]> data = toStringArray(patients);

		csvWriter.writeAll(data);

		csvWriter.close();
		
		
		
		
	}
	
	
	private static List<String[]> toStringArray(List<Patient> patientList) {
		List<String[]> records = new ArrayList<String[]>();

		// This would add a new header record, but this is unnecessary since the data we use contains the header.
		//records.add(new String[] {"lastName","firstName","prefName","street", "street2", "city", "state", "zip"});

		Iterator<Patient> it = patientList.iterator();
		while (it.hasNext()) {
			Patient patient = it.next();
			records.add(new String[] { patient.getLastName(), patient.getFirstName(), patient.getPrefName(), 
					patient.getStreet(), patient.getStreet2(), patient.getCity(), patient.getState(), patient.getZip()});
		}
		return records;
	}
}
