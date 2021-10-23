package heli.copter.diplom;

import java.util.ArrayList;

public class MObj {
    public int status;
    public String massage;
    public ArrayList<Objs> Objs;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public ArrayList<MObj.Objs> getObjs() {
        return Objs;
    }

    public void setObjs(ArrayList<MObj.Objs> objs) {
        Objs = objs;
    }

    public class Objs{
        public long ID;
        public int TypeID;
        public String InvNumber;
        public byte[] EPC;

        public long getID() {
            return ID;
        }

        public void setID(long ID) {
            this.ID = ID;
        }

        public int getTypeID() {
            return TypeID;
        }

        public void setTypeID(int typeID) {
            TypeID = typeID;
        }

        public String getInvNumber() {
            return InvNumber;
        }

        public void setInvNumber(String invNumber) {
            InvNumber = invNumber;
        }

        public byte[] getEPC() {
            return EPC;
        }

        public void setEPC(byte[] EPC) {
            this.EPC = EPC;
        }
    }
}
