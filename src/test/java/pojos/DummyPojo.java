package pojos;

public class DummyPojo {
    private String status="success";
    private DataPojo data;
    private String message= "Successfully! Record has been fetched.";
    //"status": "success",
    // "data": {
    //   "id": 1,
    //   "employee_name": "Tiger Nixon",
    //   "employee_salary": 320800,
    //   "employee_age": 61,
    //   "profile_image": ""
    //   },
    // "message": "Successfully! Record has been fetched."


    public DummyPojo() {
    }

    public DummyPojo(String status, DataPojo data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataPojo getData() {
        return data;
    }

    public void setData(DataPojo data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DummyPojo{" +
                "status='" + status + '\'' +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }
}
