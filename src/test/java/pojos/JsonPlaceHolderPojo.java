package pojos;

public class JsonPlaceHolderPojo {
    //1) Degiskenleri private olarak tanimlayacagiz
    private int userId;
    private int id;
    private String title;
    private boolean completed;
    //2) Degiskenlerin degerlerine ulasmak için getter ve setter olusturulur

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
//3) parametreli ve parametresiz constructor olustur

    public JsonPlaceHolderPojo() {
    }

    public JsonPlaceHolderPojo(int userId, int id, String title, boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
//4) toString methodu olustur


    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
