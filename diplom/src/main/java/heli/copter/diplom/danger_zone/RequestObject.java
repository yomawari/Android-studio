package heli.copter.diplom.danger_zone;

public class RequestObject{
    public RequestObject(){
        data = new data();
    }
    public String token;
    public data data;
    public class data{
        public int id;
        public int TypeID;
        public String InvNumber;
        public String epc;
    }
}