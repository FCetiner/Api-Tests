package pojosYeni;

import java.util.HashMap;
import java.util.Map;

public class Bookingdates {

    private String checkin;
    private String checkout;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}