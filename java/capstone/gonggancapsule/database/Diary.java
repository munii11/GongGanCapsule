package capstone.gonggancapsule.database;


public class Diary {

    public static final String TABLE_NAME = "diary";

    public static final String COLUMN_INDEX = "index";
    public static final String COLUMN_LATITUDE = "latitude";
    public static final String COLUMN_LONGITUDE = "longitude";
    public static final String COLUMN_CREATEDATE = "create_date";
    public static final String COLUMN_CONTENT = "content";
    public static final String COLUMN_PICTURE = "picture";

    private int index;
    private double latitude;
    private double longitude;
    private String create_date;
    private String content;
    private String picture;

    //create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_INDEX + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_LATITUDE + " DOUBLE,"
                    + COLUMN_LONGITUDE + " DOUBLE,"
                    + COLUMN_CREATEDATE + " DATETIME DEFAULT CURRENT_TIMESTAMP,"
                    + COLUMN_CONTENT + " TEXT,"
                    + COLUMN_PICTURE + " STRING"
                    + ")";

    public Diary(){
    }

    public Diary(int index, double latitude, double longitude,
                 String create_date, String content, String picture) {
        this.index = index;
        this.latitude = latitude;
        this.longitude = longitude;
        this.create_date = create_date;
        this.content = content;
        this.picture = picture;
    }


    //get()
    public int getIndex(){
        return index;
    }
    public double getLatitude(){
        return latitude
    }
    public double getLongitude(){
        return longitude;
    }
    public String getTimestamp(){
        return create_date;
    }
    public String setContent(){
        return content;
    }
    public String getPicture(){
        return picture;
    }



    //set()
    public void setIndex(int index) {
        this.index = index;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public void setTimeStamp(String create_date) {
        this.create_date = create_date;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

}
