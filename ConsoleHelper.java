package CourseWork;

import java.util.Scanner;

public class ConsoleHelper
{
    Scanner input = new Scanner(System.in);

    private smartHouse home;

    public void attachServerObject(smartHouse home)  //INSTEAD OF HAVING (smartHouse home) as parameters everywhere
    { this.home = home; }

    private String breaker()
    {return "\n---------------------------------------------------------------------------------------\n";}


    private String requestRoomNumForPlug(int num)
    {
        return  breaker() +
                "\n||  ENTER PLUG NUMBER " + num + "'s INFORMATION BELOW  ||\n" +
                "\nROOMS AVAILABLE: \n" + home.returnRoomsList() +
                "\n\nUsing the above list, please select the room for PLUG NUMBER: " + num + "\n(USE INTEGER ONLY)";

    }

    private String requestAttachmentList(int plugID)
    {
        return breaker() +
                "\nAVAILABLE DEVICE LIST OPTIONS\n" +
                "These are standard devices that can be attached to\n" +
                "the smart plug:\n" +
                home.returnListOfAttachments() +
                "\nUsing the above list, please select the device to attach to SMART PLUG NUMBER: " + plugID +
                "\n(USE INTEGER ONLY)";
    }


    private String numToAttachedDevice(int plugID)
    {
        int deviceIndex = getIntFromConsole( requestAttachmentList(plugID) );
        return home.returnAttachmentAsString(deviceIndex);
    }


    private void populateRooms()
    {
        input.nextLine();  // needed because first nextLine after nextINT is skipped
        for(int roomID = 1; roomID <= home.getNumOfRooms(); roomID++) //ID instead of index auto increment
        {
            String roomName = getStringFromConsole("\nProvide a name for room number " + (roomID) + ":");
            home.appendRooms(roomID, roomName);
        }
    }

    private void populatePlugs()
    {
        for(int smartPlugID = 1; smartPlugID <= home.getNumOfPlugs(); smartPlugID++) //Unique ID
        {
            int plugRoomID = getIntFromConsole(requestRoomNumForPlug(smartPlugID));
            String plugAttachedTo = numToAttachedDevice(smartPlugID);
            home.appendPlugs(smartPlugID, plugAttachedTo, false, plugRoomID);
        }
    }

    public void populateSmartHome()
    {
        populateRooms();
        populatePlugs();
    }


    public void displayAllData()
    {
        print(breaker()+
                "\n                      ---------------DASHBOARD--------------                      \n" +

                home.returnAllRoomsAndPlugs());
        print("                      -------------MENU OPTIONS-------------\n" +
              "                      ---------please select option:--------\n" +
                "1 - house level options\n" +
                "2 - room level options\n" +
                "3 - plug level options\n" +
                "4 - system options\n");
    }



    public void selectAndProcessOptions()
    {
        int optionNum = getIntFromConsole("Select an option: (USE INTEGER ONLY)");
        switch(optionNum)
        {
            case 1:
                houseLevelOptions();
                break;
            case 2:
                roomLevelOptions();
                break;
            case 3:
                plugLevelOptions();
                break;
            case 4:
                systemLevelOption();
                break;
        }
    }


    //Functions and methods below are for HOUSE LEVEL OPTIONS
    private String requestHouseLevelOptions()
    {
        return breaker()+"\nHOUSE LEVEL OPTIONS\n" +
                "1 - Switch all plugs off\n" +
                "2 - Switch all plugs on\n" +
                "Select an option:";
    }


    private void houseLevelOptions()
    {
        int toggleValue = getIntFromConsole(requestHouseLevelOptions());
        home.setAllToggles(toggleValue);
    }


    //Functions and methods below are for ROOM LEVEL OPTIONS
    private String requestRoomLevelOptions()
    {
        return "\nROOM LEVEL OPTIONS\n" +
                "1 - Switch all plugs off in room\n" +
                "2 - Switch all plugs on in room\n" +
                "3 - Select a plug in the room and toggle its on/off status\n" +
                "\nSelect an option (USE INTEGER ONLY)";
    }


    private String requestListOfRooms()
    { return "\nROOMS AVAILABLE: \n" + home.returnRoomsList() + "\n";}


    private void roomLevelOptions()
    {
        int selectRoomNum = getIntFromConsole(breaker() + requestListOfRooms()
                                                               + "Please select room (USE INTEGER ONLY)");

        print(breaker()+"\nList of Plugs in Room number "+ selectRoomNum + "\n" + home.returnPlugsInRoom(selectRoomNum));

        int optionSelect = getIntFromConsole(requestRoomLevelOptions());

        switch (optionSelect)
        {
            case 1:  //OPTION 1
                home.toggleAllStatusInRoom(selectRoomNum, false);
                break;

            case 2:  //OPTION 2
                home.toggleAllStatusInRoom(selectRoomNum, true);
                break;

            case 3:  //OPTION 3
                int InputSmartPlugID = getIntFromConsole("Please select plug using the Plugs ID");
                home.switchToggleInRoom(InputSmartPlugID);
                break;
        }
    }


    //Functions and methods below are for PLUG LEVEL OPTIONS
    private String returnPlugLevelOptions()
    {
        return "PLUG LEVEL OPTIONS\n" +
                "1 - Switch plug off\n" +
                "2 - Switch plug on\n" +
                "3 - Change attached device\n" +
                "4 - Move plug to different room\n" +
                "\nSelect an option:";
    }


    private void plugLevelOptions()
    {
        print(breaker()+"\nLIST OF ALL PLUGS:\n" + home.returnAllPlugs());
        int selectPlugID = getIntFromConsole("\nPlease select plug using the plug ID:");
        int selectOption = getIntFromConsole(returnPlugLevelOptions());

        switch(selectOption)
        {
            case 1:  //OPTION 1
                home.toggleSinglePlugStatus(selectPlugID,false);
                break;

            case 2:   //OPTION 2
                home.toggleSinglePlugStatus(selectPlugID,true);
                break;

            case 3:  //OPTION 3
                String plugNewAttachment = numToAttachedDevice(selectPlugID);
                home.changeAttachedDevice(selectPlugID, plugNewAttachment);
                break;

            case 4:  //OPTION 4
                int newRoomForPlug = getIntFromConsole(requestListOfRooms()+
                                                    "\nUsing the above list, please select the room for PLUG NUMBER: " +
                                                    selectPlugID + "\n(USE INTEGER ONLY)");
                home.changeRoomOfPlug(selectPlugID, newRoomForPlug);
                break;
        }
    }



    private String requestSystemLevelOptions()
    {
        return  breaker() +"\nSYSTEM LEVEL OPTION\n" +
                "1 - Add more SmartPlug\n" +
                "2 - Add more Attachments\n" +
                "3 - Add more Rooms\n" +
                "Select an option (USE INTEGER ONLY)";
    }


    //Functions and methods below are for SYSTEM LEVEL OPTIONS
    private void systemLevelOption()
    {
        int optionNum = getIntFromConsole(requestSystemLevelOptions());

        switch(optionNum)
        {
            case 1:
                populateNewPlugs();
                break;

            case 2:
                populateNewAttachments();
                break;

            case 3:
                populateNewRooms();
                break;
        }
    }


    private void populateNewPlugs()
    {
        int addPlugs = getIntFromConsole(breaker()+"\nHow many plugs would you like to add?");
        for(int i = 0; i<addPlugs; i++)
        {
            int smartPlugID = home.getNumOfPlugs() + 1;
            int plugRoomID = getIntFromConsole(requestRoomNumForPlug(smartPlugID));
            String plugAttachedTo = numToAttachedDevice(smartPlugID);
            home.appendNewPlugs(smartPlugID, plugAttachedTo, false,plugRoomID);
        }
    }


    private void populateNewAttachments()
    {
        int addAttachment = getIntFromConsole(breaker() +"\nHow many Attachments would you like to add?");
        input.nextLine();
        for(int i = 0; i<addAttachment; i++)
        {
            String newAttachmentName = getStringFromConsole(breaker()+"Enter Name for new attachment number "+(i+1)+":");
            home.appendNewAttachments(newAttachmentName);
        }
    }


    private void populateNewRooms()
    {
        int addRooms = getIntFromConsole(breaker() +"\nHow many rooms would you like to add?");
        input.nextLine();
        for (int i = 0; i<addRooms; i++)
        {
        int roomID = home.getNumOfRooms() + 1;
        String newRoom = getStringFromConsole("\nProvide a name for room number " + (roomID) + ":");
        home.appendNewRooms(roomID, newRoom);
        }
    }







    private String getStringFromConsole(String prompt)
    {
        print(prompt);
        return input.nextLine();
    }
    int getIntFromConsole(String prompt)
    {
        print(prompt);
        return input.nextInt();
    }
    private boolean getBooleanFromConsole(String prompt)
    {
        print(prompt);
        return input.nextBoolean();
    }
    private Double getDoubleFromConsole(String prompt)
    {
        print(prompt);
        return input.nextDouble();
    }

    private void print(int message)     { System.out.println(message); }
    private void print(double message)  { System.out.println(message); }
    private void print(boolean message) { System.out.println(message); }
    private void print(String message)  { System.out.println(message); }
}