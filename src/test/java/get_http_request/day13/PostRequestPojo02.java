package get_http_request.day13;

import base_url.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import pojos.BookingsDatesPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostRequestPojo02 extends HerOkuAppBaseUrl {
    /*
 https://restful-booker.herokuapp.com/booking
 request body
 { "firstname": "Ali",
            "lastname": "Can",
            "totalprice": 500,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2022-03-01",
                "checkout": "2022-03-11"
             }
 }}
Status code is 200
 response body
 {
    "bookingid": 11,
       "booking": {
         "firstname": "Ali",
         "lastname": "Can",
         "totalprice": 500,
         "depositpaid": true,
         "bookingdates": {
            "checkin": "2022-03-01",
            "checkout": "2022-03-11"
                             }
                         }
                     }
  */
    @Test
    public void test(){
        //1 url olustur
        spec05.pathParam("1","booking");
        //2 expected data
        BookingsDatesPojo  bookingsDatesPojo=new BookingsDatesPojo("2022-03-01","2022-03-11");
        BookingPojo bookingPojo=new BookingPojo("Ali","Can",500,true,bookingsDatesPojo);
        System.out.println("bookingPojo = " + bookingPojo);

        //3 Request response
        Response response=given()
                .contentType(ContentType.JSON)
                .spec(spec05)
                .auth()
                .basic("admin","password123")
                .body(bookingPojo)
                .post("/{1}");
        response.prettyPeek();
        //4 Dogrulama
        BookingResponsePojo actualData=response.as(BookingResponsePojo.class);
       assertEquals(200,response.statusCode());
       assertEquals(bookingPojo.getFirstname(),actualData.getBooking().getFirstname());
       assertEquals(bookingPojo.getLastname(),actualData.getBooking().getLastname());
       assertEquals(bookingPojo.getTotalprice(),actualData.getBooking().getTotalprice());
       assertEquals(bookingPojo.isDepositpaid(),actualData.getBooking().isDepositpaid());
       assertEquals(bookingPojo.getBookingsDates().getCheckin(),actualData.getBooking().getBookingsDates().getCheckin());
       assertEquals(bookingPojo.getBookingsDates().getCheckout(),actualData.getBooking().getBookingsDates().getCheckout());

    }
}
