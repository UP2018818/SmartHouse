package CourseWork;

public class smartHouse {
    private houseRooms[] roomsArr;
    private smartPlugs[] plugsArr;
    private int indexForRooms;
    private int indexForPlugs;
    private String[] attachmentsArr = {"Lamp", "TV", "Computer", "Phone Charger", "Heater"};


    public smartHouse(int numOfRooms, int numOfPlugs)
    {
        roomsArr = new houseRooms[numOfRooms];
        plugsArr = new smartPlugs[numOfPlugs];
        indexForRooms = 0;
        indexForPlugs = 0;
    }


    public int getNumOfRooms()
    { return roomsArr.length; }

    public int getNumOfPlugs()
    { return plugsArr.length; }

    public int getNumOfAttachments()
    { return attachmentsArr.length; }


    public void appendRooms(int roomID, String roomName)
    {
        if (indexForRooms >= getNumOfRooms())
        { return; }
        houseRooms room = new houseRooms(roomID, roomName);

        roomsArr[indexForRooms] = room;
        indexForRooms++;
    }


    public void appendPlugs(int smartPlugID, String plugAttachedTo, Boolean status, int plugRoomID)
    //append plugs and only append when room ID and roomPlugID is the same
    {
        if (indexForPlugs >= getNumOfPlugs())
        { return; }

        for (houseRooms room : roomsArr)
        {
            if (room.getRoomID() == plugRoomID)
            {
                smartPlugs plug = new smartPlugs(smartPlugID, plugAttachedTo, status, plugRoomID);

                plugsArr[indexForPlugs] = plug;
                indexForPlugs++;
            }
        }
    }


    private String convertStatusToString(boolean statusValue)
    {
        if (statusValue == true)
        { return "ON"; }
        return "OFF";
    }


    public String returnRoomsList() //displays the list of room in step 1 of append
    {
        String result = "";

        for (houseRooms item : roomsArr)
        { result += item.listRoomsAvailable() + " "; }
        return result;
    }


    public String returnAllRoomsAndPlugs()
    {
        String result = "";

        for (houseRooms room : roomsArr)
        {
            result += room.roomIDToString();
            for (smartPlugs plug : plugsArr)
            {
                if (room.getRoomID() == plug.getPlugRoomID())
                {
                    String StrToggleStatus = convertStatusToString(plug.getStatus()); //convert true/false to ON/OFF
                    result += plug.plugInfoToString(room.getRoomName(), StrToggleStatus);
                }
            }
        }
        return result;
    }


    //ALL METHODS UNDER HERE IS FOR ATTACHMENTS
    public String returnAttachmentAsString(int attachmentIndex)
    { return attachmentsArr[attachmentIndex - 1]; }

    public String returnListOfAttachments()
    {
        String result = "";
        for(int i = 0; i < attachmentsArr.length; i++)
        {
            result +=  (i+1)+ "-" + attachmentsArr[i] + "\n";
        }
        return result;
    }


    // Functions below this are for HOUSE LEVEL OPTION
    public void setAllToggles(int toggleValue)
    {
        for (smartPlugs plugNum : plugsArr)
        {
            if (toggleValue == 2)
            { plugNum.setStatus(true); }

            else
            { plugNum.setStatus(false); }
        }
    }



    // Functions below this are for ROOM LEVEL OPTION
    public String returnPlugsInRoom(int roomID)
    {
        String result = "";
        for (houseRooms room : roomsArr)
        {
            if (roomID == room.getRoomID())
            {
                for (smartPlugs plug : plugsArr)
                {
                    if (plug.getPlugRoomID() == room.getRoomID()) //if room number and roomPlug number
                    {
                        String StrToggleStatus = convertStatusToString(plug.getStatus()); //convert true/false to ON/OFF
                        result += plug.plugInfoToString(room.getRoomName(), StrToggleStatus);
                    }
                }
            }
        }
        return result;
    }


    public void toggleAllStatusInRoom(int roomID, boolean status) //OPTION 1 AND 2
    {
        for (houseRooms room : roomsArr)
        {
            if (roomID == room.getRoomID())
            {
                for (smartPlugs plug : plugsArr)
                {
                    if (plug.getPlugRoomID() == room.getRoomID()) //if room number and roomPlug number
                    {
                        plug.setStatus(status);
                        String StrToggleStatus = convertStatusToString(plug.getStatus()); //convert true/false to ON/OFF
                    }
                }
            }
        }
    }


    public void switchToggleInRoom(int InputSmartPlugID)  //OPTION 3
    {
        for (smartPlugs plug : plugsArr)
        {
            if (InputSmartPlugID == plug.getSmartPlugID())
            { plug.setStatus(!plug.getStatus()); }
        }
    }



    // Functions below this are for PLUG LEVEL OPTION
    public String returnAllPlugs()
    {
        String result = "";
        for (houseRooms room : roomsArr)
        {
            for (smartPlugs plug : plugsArr)
                {
                    if (room.getRoomID() == plug.getPlugRoomID())
                    {
                        String StrToggleStatus = convertStatusToString(plug.getStatus()); //convert true/false to ON/OFF
                        result += plug.plugInfoToString(room.getRoomName(), StrToggleStatus);
                    }
                }
        }
        return result;
    }

    public void toggleSinglePlugStatus(int selectPlugID, boolean toggle) // OPTION 1 AND 2
    {  plugsArr[selectPlugID-1].setStatus(toggle); }                     // We subtract 1 because index starts from 0

    public void changeAttachedDevice(int selectPlugID, String plugNewAttachment)    // OPTION 3
    { plugsArr[selectPlugID-1].setPlugAttachedTo(plugNewAttachment); }

    public void changeRoomOfPlug(int selectPlugID, int newRoomForPlug)    // OPTION 4
    { plugsArr[selectPlugID-1].setPlugRoomID(newRoomForPlug); }



    // Functions below this are for SYSTEM LEVEL OPTION
    public void appendNewRooms(int roomID, String roomName)
    {
        int newNumOfRooms = getNumOfRooms() + 1;
        houseRooms[] roomArrCopy = new houseRooms[newNumOfRooms];

        for (int i = 0; i < getNumOfRooms(); i++)
        { roomArrCopy[i] = roomsArr[i]; }

        houseRooms room = new houseRooms(roomID, roomName);
        roomArrCopy[indexForRooms] = room;
        indexForRooms++;

        roomsArr = new houseRooms[newNumOfRooms]; //recopy to display information again
        { roomsArr = roomArrCopy; }
    }


    public void appendNewPlugs(int smartPlugID, String plugAttachedTo, boolean status, int plugRoomID)
    {
        int newNumOfPlugs = getNumOfPlugs() + 1;
        smartPlugs[] plugArrCopy = new smartPlugs[newNumOfPlugs];

        for (int i = 0; i < getNumOfPlugs(); i++)
        { plugArrCopy[i] = plugsArr[i]; }

        smartPlugs plug = new smartPlugs(smartPlugID, plugAttachedTo, status, plugRoomID);
        plugArrCopy[indexForPlugs] = plug;
        indexForPlugs++;

        plugsArr = new smartPlugs[newNumOfPlugs];
        { plugsArr = plugArrCopy; }
    }


    public void appendNewAttachments(String newAttachmentName)
    {
        int newNumOfAttachments = getNumOfAttachments() + 1;
        String[] attachmentsArrCopy = new String[newNumOfAttachments];

        for (int i = 0; i < getNumOfAttachments(); i++)
        { attachmentsArrCopy[i] = attachmentsArr[i]; }

        attachmentsArrCopy[newNumOfAttachments-1] = newAttachmentName;//subtract 1 array index starts from 0

        attachmentsArr = new String[newNumOfAttachments];
        { attachmentsArr = attachmentsArrCopy; }
    }
}

