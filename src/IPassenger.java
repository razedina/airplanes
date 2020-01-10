public interface IPassenger {
    boolean checkPassportDate(int days);
    boolean checkValidationCardDate();
    boolean possibleDiscount(int ticketNumber);
    boolean finalCheck();
}
