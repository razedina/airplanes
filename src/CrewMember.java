import java.time.LocalDate;

public class CrewMember extends Passenger implements Comparable<CrewMember>{
    private String school;
    private int yearsInCompany;
    private int level;
    private double salary;
    private int specialLevel = 3;
    private int validDays = 90;

    ///------------Constructors----------------
    public CrewMember(){
        this("TUDublin", (int) Math.round( Math.random()*15), (int) Math.round( Math.random()*5), 1000);
    }

    public CrewMember(String school, int yearsInCompany, int level, int salary){
        /* super(); */
        this.setSchool(school);
        this.setYearsInCompany(yearsInCompany);
        this.setLevel(level);
        this.setSalary(salary + salary*level);//depends of a level
    }
    public CrewMember(String name, String surname, LocalDate passportValid , LocalDate validationCardDate, String citizenship,String school, int yearsInCompany, int level, int salary){
        super(name, surname , passportValid, validationCardDate, citizenship );
        this.setSchool(school);
        this.setYearsInCompany(yearsInCompany);
        this.setLevel(level);
        this.setSalary(salary + salary*level);
    }


    public int compareTo(CrewMember st){
        return Integer.valueOf(this.level).compareTo(Integer.valueOf(st.level));
    }
    //only level 3 can have validation pw less than specified days
    //the limit for level 3 is 10 days
    //other level have the same rule as customers
    @Override
    public boolean checkPassportDate(int days) {
        if(this.level == specialLevel && super.checkPassportDate(validDays /9)) {
            //System.out.println(this.getName() + ' '+ this.getSurname() + " passport is about to expire in a less than 10 days");
            return true;
        }
        else return super.checkPassportDate(days);

    }
    //the default check with 90 and 10 days
    public boolean checkPassportDate() {
        if(this.level == this.specialLevel && super.checkPassportDate(validDays /9)) {
            return true;
        }
        else return super.checkPassportDate(validDays);
    }

    @Override //this method is not applicable for crew member and we need to exclude it
    public boolean possibleDiscount(int ticketNumber) {
        //System.out.println("Discount is not valid for crew members");
        return false;
    }
    //rule of the company:
    //every 2 years the salary is increased by 20%
    public boolean bonusSalary(){
        if (this.getYearsInCompany()>5){
            //System.out.println(this.getName() + " "+this.getSurname() + " has a bonus salary");
            this.salary = this.getSalary()*(1 + (this.getYearsInCompany()-5)/10);
            return true;
        }
        return false;
    }
    //crew member can fly but he got notice to replace a card
    @Override
    public boolean checkValidationCardDate(){
        var lowerBound = this.getValidationCardDate().minusDays(90);
        if(lowerBound.isBefore(LocalDate.now())){
            //System.out.println("Credit card is not valid but crew member can fly. Change your card as soon as possible");
            return true;
        }
        return true;

    }
    @Override
    public String toString() {
        return super.toString() +
                "\r\n Additional Info for CrewMember:" +
                "School: '" + school + '\'' +
                ", yearsInCompany: " + yearsInCompany +
                ", level: " + level +
                ", salary: " + salary;
    }
    public boolean finalCheck(){
        if(super.finalCheck() && level >-1){
            return true;
        }
        System.out.println(super.finalCheck());
        return false;
    }

    //getters and setters
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        if(school.length()>0)
            this.school = school;
    }

    public int getYearsInCompany() {
        return this.yearsInCompany;
    }

    public void setYearsInCompany(int yearsInCompany) {
        if (yearsInCompany > 0)
            this.yearsInCompany = yearsInCompany;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(level >0 && level < 4)
            this.level = level;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        //years must be set first for bonus salary
        this.salary = salary;
        if (this.getYearsInCompany() > -1){
            this.bonusSalary();
        }
    }

}
