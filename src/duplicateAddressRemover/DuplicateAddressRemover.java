package duplicateAddressRemover;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.text.WordUtils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import objectTypes.Household;
import objectTypes.Patient;

public class DuplicateAddressRemover {
	
	
	public static void main(String[] args) throws IOException {
		
		
		/*
		 * This reads the Test Data.csv file, then converts it to a 
		 * list of patient objects based on the information from that file. 
		 */
		
		CSVReader reader = new CSVReader(new FileReader("DART Input.csv"));
		
		ColumnPositionMappingStrategy<Patient> beanStrategy = new ColumnPositionMappingStrategy<Patient>();
		beanStrategy.setType(Patient.class);
		beanStrategy.setColumnMapping(new String[] {"lastName","firstName","prefName","street", "street2",
													"city", "state", "zip"});
		
		CsvToBean<Patient> csvToBean = new CsvToBean<Patient>();
		
		List<Patient> patients = csvToBean.parse(beanStrategy, reader);
		capitalizePatientList(patients);
		
		/*
		 * This code runs through the list of Patient objects and converts it to a list of Household objects, 
		 * which doesn't contain duplicate addresses.
		 */
		
		
		List<Household> households = convertPatientsToHouseholds(patients);
		
	
		
		
		/*
		 * This last section of code takes the list of patient objects, then converts it to a .csv file, which is then stored at Test Write.csv
		 */
		FileWriter writer = new FileWriter("DART Output.csv");
		CSVWriter csvWriter = new CSVWriter(writer);
		
		List<String[]> data = toStringArray(households);

		csvWriter.writeAll(data);

		csvWriter.close();
		
		
		
		
	}
	
	
	private static List<String[]> toStringArray(List<Household> householdList) {
		
		List<String[]> records = new ArrayList<String[]>();

		// This would add a new header record, but this is unnecessary since the data we use contains the header.
		//records.add(new String[] {"lastName","firstName","prefName","street", "street2", "city", "state", "zip"});

		Iterator<Household> it = householdList.iterator();
		while (it.hasNext()) {
			Household household = it.next();
			
			if(household.isSingleResident()) {
				records.add(new String[] {household.getLastName(), household.getFirstName(), household.getPrefName(), 
					household.getStreet(), household.getStreet2(), household.getCity(), household.getState(), household.getZip()});
			}
			else {
				records.add(new String[] {"The " + household.getHouseholdName() + " Household", "", "", 
						household.getStreet(), household.getStreet2(), household.getCity(), household.getState(), household.getZip()});
			}
		}
		return records;
	}
	
	private static List<Household> convertPatientsToHouseholds(List<Patient> patientList){
		
		List<Household> households = new ArrayList<Household>();
		
		Iterator<Patient> it = patientList.iterator();	
		while(it.hasNext()) {
			Patient patient = it.next();
			
			//Always adds the first patient to the list of households
			if(households.isEmpty()) {
				households.add(patientToHousehold(patient));
			}
			//checks if the patient has the same address as the last
			else if(equalsIgnoreCasePunctuationAbbreviation(households.get(households.size() - 1).getStreet(),patient.getStreet())){
				
				households.get(households.size() - 1).setSingleResident(false);
				
				//check if the patients living at the same address have the same last name, then hyphenates the last names if they don't 
				if(!households.get(households.size() - 1).getLastName().equals(patient.getLastName())) {
					
					households.get(households.size() - 1).setHouseholdName(households.get(households.size() - 1)
							.getHouseholdName() + "-" +  patient.getLastName());
				}
			}
			//if the address doesn't already exist in the list of households, this adds it to the list
			else {
				households.add(patientToHousehold(patient));
			}
		}
		
		return households;
	}
	
	
	
	public static Household patientToHousehold(Patient patient) {
		
		return new Household(patient.getLastName(), patient.getLastName(),patient.getFirstName(),patient.getPrefName(),patient.getStreet(),
							 patient.getStreet2(), patient.getCity(), patient.getState(), patient.getZip(), true);
	}
	
	public static boolean equalsIgnoreCasePunctuation(String a, String b) {
		
		String modifiedA = a.toLowerCase().replaceAll("\\s+","").replace(",","").replace(".","").replace("#", "");
		
		String modifiedB = b.toLowerCase().replaceAll("\\s+","").replace(",","").replace(".","").replace("#", "");
		
		return modifiedA.equalsIgnoreCase(modifiedB);		
	}
	
	public static boolean equalsIgnoreCasePunctuationAbbreviation(String a, String b) {
		
		String modifiedA = a.toLowerCase().replaceAll("\\s+","").replace(",","").replace(".","").replace("#", "")
							.replaceAll("street", "st").replaceAll("drive", "dr").replaceAll("boulevard", "blvd").replaceAll("lane", "ln")
							.replaceAll("avenue", "av").replaceAll("ave", "av").replaceAll("avn", "av");
		String modifiedB = b.toLowerCase().replaceAll("\\s+","").replace(",","").replace(".","").replace("#", "")
							.replaceAll("street", "st").replaceAll("drive", "dr").replaceAll("boulevard", "blvd").replaceAll("lane", "ln")
							.replaceAll("avenue", "av").replaceAll("ave", "av").replaceAll("avn", "av");

		
		return modifiedA.equalsIgnoreCase(modifiedB);		
	}

	public static void capitalizePatientList(List<Patient> patientList) {
		
		for(int i = 0; i < patientList.size() - 1; i++) {
			patientList.get(i).capitalizeFully();
		}
		
	}
}
