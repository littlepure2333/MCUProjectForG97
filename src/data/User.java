package data;

public class User implements java.io.Serializable{
    @SuppressWarnings("WeakerAccess")
    public int qmNumber;
    @SuppressWarnings("WeakerAccess")
    public String fullName;
    @SuppressWarnings("WeakerAccess")
    public String emailAddress;
    @SuppressWarnings("WeakerAccess")
    public boolean needToPay;

    int getQmNumber() {
        return qmNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    void setQmNumber(int qmNumber) {
        this.qmNumber = qmNumber;
    }

    void setFullName(String fullName) {
        this.fullName = fullName;
    }

    void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /*
    private boolean qmCheck() {
        return true;
    }
    private boolean emailCheck() {
        System.out.println("Mailing a check to " + fullName
                + " " + emailAddress);
        return true;
    }
    */

   public User(int qmNumber, String fullName, String emailAddress) {
        this.qmNumber = qmNumber;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
    }

   public User() {

   }
   /*
    try
        {
            FileInputStream ifs = new FileInputStream(xmlFile);
            @SuppressWarnings("unchecked")
            List<SerialableObject> sos4In = (List<SerialableObject>)deserializeSingleObject(ifs);
            for(SerialableObject jo4In : sos4In)
            {
                System.out.println("id: " + jo4In.getId());
                System.out.println("name: " + jo4In.getName());
                System.out.println("value: " + jo4In.getValue());
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    public Object deserializeSingleObject(InputStream is)       // 反序列化单个Java对象
    {
        XMLDecoder xd = new XMLDecoder(is);
        Object obj = xd.readObject();       // 从XML序列中解码为Java对象
        xd.close();
        return obj;
    }
*/
}
