package pojos;

public class BookingPojo {
    private String firstname="Ali";
    private String lastname="Can";
    private int totalprice=500;
    private boolean depositpaid=true;
    private BookingsDatesPojo bookingdates;

    public BookingPojo() {
    }

    public BookingPojo(String firstname, String lastname, int totalprice, boolean depositpaid, BookingsDatesPojo bookingsDatesPojo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingsDatesPojo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingsDatesPojo getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingsDatesPojo bookingdates) {
        this.bookingdates = bookingdates;
    }

    @Override
    public String toString() {
        return "BookingPojo{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingsDatesPojo=" + bookingdates +
                '}';
    }
}
