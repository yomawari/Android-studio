package heli.copter.diplom.danger_zone;

public class RequestType{
    public RequestType(){
        data = new data();
    }
    public String token;
    public data data;
    class data{
        public int id;
        public String name;
        public String onesNumber;
        public int categoryID;
        public String desk_text;

    }
}