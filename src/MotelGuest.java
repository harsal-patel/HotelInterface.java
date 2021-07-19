import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MotelGuest {
    private String name;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String cityStateZip;
    private int numGuests;
    private int roomNumber;
    private int numNights;
    private int remainingNights;
    private LocalDate checkOut;
    private LocalDate checkIn;

    public MotelGuest() {
        name = "Null Guest";
    }
    public String getName() { return name; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getStreetAddress() { return streetAddress; }
    public String getCityStateZip() { return cityStateZip; }
    public int getNumGuests() { return numGuests; }
    public int getRoomNumber() { return roomNumber; }
    public int getNumNights() { return numNights; }
    public int getRemainingNights() { return remainingNights; }
    public LocalDate getCheckOut() { return checkOut; }
    public LocalDate getCheckIn() { return checkIn; }
    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        name = firstName + " " + lastName;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public void setCityStateZip(String cityStateZip) {
        this.cityStateZip = cityStateZip;
    }
    public void setNumGuests(int numGuests) {
        this.numGuests = numGuests;
    }
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setNumNights(int numNights, LocalDate currentDate, int counter) {
        this.numNights = numNights;
        remainingNights = numNights;
        checkIn = currentDate.plusDays(counter);
        checkOut =  currentDate.plusDays(numNights + counter);
    }
    public void removeNight() { remainingNights -= 1; }
    public void printGuestInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        System.out.println(getName());
        System.out.println(getStreetAddress());
        System.out.println(getCityStateZip());
        System.out.println("Room: " + getRoomNumber());
        System.out.println("Number of Guests: " + getNumGuests());
        System.out.println("Length of stay: " + getNumNights() + " nights");
        if (remainingNights > 0) {
            System.out.print("Check out on ");
            System.out.println(formatter.format(checkOut));
        }
        else { System.out.println("Check out today"); }
    }
}
