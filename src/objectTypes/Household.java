package objectTypes;

import java.io.Serializable;

import org.apache.commons.text.WordUtils;

public class Household implements Serializable{

	public boolean singleResident;
	//Information stored for each household
	public String  householdName, lastName, firstName, prefName, street, street2, city, state, zip;
		
		
	/*
	 * This constructor creates a household object that has defined values for each variable.
	 */
	
	public Household(String householdName, String lastName ,String firstName, String prefName,
				   String street, String street2, String city, String state, String zip, boolean singleResident) {
		this.householdName = householdName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.prefName = prefName;
		this.street = street;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.singleResident = singleResident;
	}
	
	

	/*
	 * This blank constructor creates a "empty" household object with an empty string for each variable.
	 * Use this if you want to create a household patient, but you don't have that household's data yet. 
	 */
	public Household(){
		householdName = "";
		lastName = "";
		firstName = "";
		prefName = "";
		street = "";
		street2 = "";
		city = "";
		state = "";
		zip = "";
		singleResident = true;
	}
	
	@Override
	public String toString() {
		return "Household [singleResident=" + singleResident + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", prefName=" + prefName + ", street=" + street + ", street2=" + street2 + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + "]";
	}


	public void capitalizeFully() {
		householdName = WordUtils.capitalizeFully(householdName);
		lastName = WordUtils.capitalizeFully(lastName);
		firstName = WordUtils.capitalizeFully(firstName);
		prefName = WordUtils.capitalizeFully(prefName);
		street = WordUtils.capitalizeFully(street);
		street2 = WordUtils.capitalizeFully(street2);
		city = WordUtils.capitalizeFully(city);
		state = WordUtils.capitalizeFully(state);

	}
	


	public String getHouseholdName() {
		return householdName;
	}



	public void setHouseholdName(String householdName) {
		this.householdName = householdName;
	}



	public boolean isSingleResident() {
		return singleResident;
	}



	public void setSingleResident(boolean singleResident) {
		this.singleResident = singleResident;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getPrefName() {
		return prefName;
	}



	public void setPrefName(String prefName) {
		this.prefName = prefName;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getStreet2() {
		return street2;
	}



	public void setStreet2(String street2) {
		this.street2 = street2;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getZip() {
		return zip;
	}



	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
	
}
