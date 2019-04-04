package dgsw.hs.kr.data;

public class UserBean {
    private int sequenceNumber;
    private String name;

    public UserBean(String name){
        this.name = name;
    }

    public UserBean(){

    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
