package CourseWork;

public class smartPlugs
{
    private int smartPlugID;
    private String plugAttachedTo;
    private boolean status;
    private int plugRoomID;

    public smartPlugs(int smartPlugID, String plugAttachedTo, boolean status, int plugRoomID)
    {
        this.smartPlugID = smartPlugID;
        this.plugAttachedTo = plugAttachedTo;
        this.status = status;
        this.plugRoomID = plugRoomID;
    }

    public int getSmartPlugID()
    { return smartPlugID; }

    public void setSmartPlugID(int smartPlugID)
    { this.smartPlugID = smartPlugID; }


    public String getPlugAttachedTo()
    { return plugAttachedTo; }

    public void setPlugAttachedTo(String plugAttachedTo)
    { this.plugAttachedTo = plugAttachedTo; }


    public boolean getStatus()
    { return status; }

    public void setStatus(boolean status)
    { this.status = status; }


    public void setPlugRoomID(int plugRoomID)
    { this.plugRoomID = plugRoomID; }

    public int getPlugRoomID()
    { return plugRoomID; }


    public String plugInfoToString(String roomName, String StrToggleStatus)
    {
        return  String.format("%1$-50s","\nSmartPlug |attached to: " + plugAttachedTo) +
                String.format("%20s","|room: " + roomName + "|ID: " + smartPlugID +
                "|status: " + StrToggleStatus + "|");
    }
}