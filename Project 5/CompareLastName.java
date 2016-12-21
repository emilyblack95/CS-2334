import java.util.Comparator;

/**
 * Project 5
 * CS 2334 - Section 010
 * 4/29/15
 */
public class CompareLastName implements Comparator<Person> 
{

	/**
	 * This method compares one person to another based on their last name.
	 * <P>
	 * @param 	person			the first person
	 * @param	anotherPerson	the person the first person is being compared to
	 * @return					a positive integer if person's last name comes after anotherPerson's or
	 * 							if their last names are equal but person's first name comes after,
	 * 							a negative integer if person's last name comes before anotherPerson's or
	 * 							if their last names are equal but person's first name comes before
	 */
	public int compare(Person person, Person anotherPerson)    //Compares last name
	 {
	    String lastName1 = person.getFullName().getLastName().toUpperCase();
	    String firstName1 = person.getFullName().getFirstName().toUpperCase();
	    String lastName2 = anotherPerson.getFullName().getLastName().toUpperCase();
	    String firstName2 = anotherPerson.getFullName().getFirstName().toUpperCase();

	    if (!(lastName1.equals(lastName2)))
	    {
	      return lastName1.compareTo(lastName2);
	    	
	    }
	    else
	    {
	      return firstName1.compareTo(firstName2);
	  
	    }
	 }
}