//converte to abstract, no need for instance of class passenger
import java.time.LocalDate;
import java.util.Comparator;

public class Passenger implements IPassenger{
    private String name;
    private String surname;
    private String passportNumber;
    private String creditCardNumber;
    private LocalDate passportValid;
    private LocalDate validationCardDate;
    private String citizenship;


    //Name generator for simpler use
    //Private functions, part of hiding data
    private String generateRandomName(){
        String name = "";
        for(int i=0; i<5; i++){
            name = name + (char) (Math.round(Math.random() * ((80 - 65) + 1)) + 65);
        }
        return name;
    }
    private String generateName(){
        String[] names = {"Emma", "Olivia","Ava","Isabela","Sophia","Mia","Amelia","James","Liam","Lucas","Logan","Jacob","Daniel"};
        var a = Math.round( Math.random()*12);
        return names[(int)a];

    }
    private String generateSurname(){
        String[] surnames = {"Murphy","Bond","Walsh","Smith","Ryan","O'Connor","O'Neill", "Gallagher","Lynch","Moore"};
        var a = Math.round( Math.random()*9);
        return surnames[(int)a];
    }

public int compare(Passenger s1,Passenger s2){
        return s1.getName().compareTo(s2.getName());
        }


//-------Constructors-------
    public Passenger(){
        this.setName(generateName());
        this.setSurname(generateSurname());
        this.setCitizenship("Bosnia");
        this.setValidationCardDate(LocalDate.of(2020, 12,10));
        this.setPassportValid(LocalDate.of(2030, 1,1));
    }
    public Passenger(String name, String surname, LocalDate passportValid , LocalDate validationCardDate, String citizenship){
        this.setName(name);
        this.setSurname(surname);
        this.setCitizenship(citizenship);
        this.setValidationCardDate(validationCardDate);
        this.setPassportValid(passportValid);
    }


    @Override
    public String toString() {
        return "Passenger:" +
                "\r\n Name: '" + name + '\'' +
                "\r\n Surname: '" + surname + '\''+
                "\r\n Citizenship: "+ citizenship;
    }
    public boolean checkPassportDate(int days){
        var lowerBound = this.getPassportValid().minusDays(days);
        //System.out.println("The passenger cannot fly in this flight, passport is not valid");
        return !lowerBound.isBefore(LocalDate.now());
    }
    public boolean possibleDiscount(int ticketNumber){
        //System.out.println(this.getName()+this.getSurname()+" is the 5th customer and got 30% discount ");
        return ticketNumber % 5 == 0;
    }
    public boolean checkValidationCardDate(){
        var lowerBound = this.getValidationCardDate().minusDays(90);
        //System.out.println("The passenger cannot fly in this flight, credit card is not valid");
        return !lowerBound.isBefore(LocalDate.now());
    }
    public boolean finalCheck(){
        return this.getName() != null && this.getName() != null && this.checkValidationCardDate() && this.checkPassportDate(90);
        //System.out.println(this.getName()+this.getSurname() + " Info is not valid");
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public boolean setName(String name) {
        if (name.length()>0) {
            this.name = name;
            return true;
        }
        else{
            //System.out.println("Set a name again");
            return false;
        }
    }

    public String getSurname() {
        return surname;
    }

    public boolean setSurname(String surname) {
        if (surname.length()>0) {
            this.surname = surname;
            return true;
        }
        else {
//            System.out.println("Set a surname again");
            return false;
        }
    }
    public LocalDate getPassportValid(){
        return this.passportValid;
    }

    public LocalDate getValidationCardDate() {
        return validationCardDate;
    }

    public void setValidationCardDate(LocalDate validationCardDate) {
        this.validationCardDate = validationCardDate;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public void setPassportValid(LocalDate passportValid) {
        this.passportValid = passportValid;
    }

    public String getPassportNumber() {

        return passportNumber;
    }
    //check validity of a passport number
    public boolean setPassportNumber(String passportNumber) {
        String[] arr = passportNumber.split("[~#@*+%{}<>\\[\\]|\"\\_^]", 2);
        if( arr.length == 1){
            this.passportNumber = passportNumber;
            return true;
        }
        return false;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    //check if credit card number is valid
    public boolean setCreditCardNumber(String creditCardNumber) {
        String[] arr = creditCardNumber.split("[~#@*+%{}<>\\[\\]|\"\\_^]", 2);
        if( arr.length == 1){
            this.creditCardNumber = creditCardNumber;
            return true;
        }
        return false;
    }
}
