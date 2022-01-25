package CourseWork;

public class Dashboard
{
    public static void main(String[] args)
    {
        ConsoleHelper console = new ConsoleHelper();

        int numOfRooms = console.getIntFromConsole("How many rooms are there in the House?");
        int numOfPlugs = console.getIntFromConsole("\nHow many plugs would you like in your House?");

        smartHouse home = new smartHouse(numOfRooms, numOfPlugs);

        console.attachServerObject(home);
        console.populateSmartHome();

        while(true)
        {
            console.displayAllData();
            console.selectAndProcessOptions();
        }
    }
}


