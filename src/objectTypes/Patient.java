package objectTypes;

import java.io.Serializable;

import org.apache.commons.text.WordUtils;

public class Patient implements Serializable {
	
	//Information stored for each patient
	public String  lastName, firstName, prefName, street, street2, city, state, zip;
	
	/*
	 * This constructor creates a patient object that has defined values for each variable.
	 */
	
	public Patient(String lastName ,String firstName, String prefName,
				   String street, String street2, String city, String state, String zip) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.prefName = prefName;
		this.street = street;
		this.street2 = street2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	

	/*
	 * This blank constructor creates a "empty" patient object with an empty string for each variable.
	 * Use this if you want to create a named patient, but you don't have that patient's data yet. 
	 */
	public Patient(){
		
	}
		
	public void capitalizeFully() {
		lastName = WordUtils.capitalizeFully(lastName);
		firstName = WordUtils.capitalizeFully(firstName);
		prefName = WordUtils.capitalizeFully(prefName);
		street = WordUtils.capitalizeFully(street);
		street2 = WordUtils.capitalizeFully(street2);
		city = WordUtils.capitalizeFully(city);
		state = WordUtils.capitalizeFully(state);

	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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



	@Override
	public String toString() {
		return "Patient [lastName=" + lastName + ", firstName=" + firstName + ", prefName=" + prefName + ", street="
				+ street + ", street2=" + street2 + ", city=" + city + ", state=" + state + ", zip=" + zip + "]";
	}
	

	
}

