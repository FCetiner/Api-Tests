package pojos;

public class BookingResponsePojo {

    private int bookingid=11;
    private BookingPojo booking;

    public BookingResponsePojo() {
    }

    public BookingResponsePojo(int bookingid, BookingPojo bookingPojo) {
        this.bookingid = bookingid;
        this.booking = bookingPojo;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingid=" + bookingid +
                ", bookingPojo=" + booking +
                '}';
    }
}
