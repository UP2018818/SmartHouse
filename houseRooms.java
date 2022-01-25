package CourseWork;

public class houseRooms
{
    private int roomID;
    private String roomName;

    public houseRooms(int roomID, String roomName)
    {
        this.roomID = roomID;
        this.roomName = roomName;
    }

    public int getRoomID()
    { return roomID; }

    public String getRoomName()
    { return roomName; }

    public void setRoomID(int roomID)
    { this.roomID = roomID; }

    public void setRoomName(String roomName)
    { this.roomName = roomName; }

    public String listRoomsAvailable()
    { return roomID + " - " + roomName + " | "; }

    public String roomIDToString()
    { return "\nRoom: " + roomID; }
}
