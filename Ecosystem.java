import java.util.Random;

//Autumn Clark
//CS 2235
//Dr. Kirby
//EcosystemV2_HW3_Clark
public class Ecosystem {
    //Instance variables
    public static DoublyLinkedList<Animal> _river = new DoublyLinkedList();

    //Method to populate _river ; 33% Fish, 33% Bear, and 33% null
    public static void PopulateRiver(){
        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < 500; i++){ //Make _river of size 500
            int x = rand.nextInt(3);
            if (x == 0){
                //Add a Fish
                _river.AddHead(new Fish());
            } else if (x == 1){
                //Add a Bear
                _river.AddHead(new Bear());
            } else {
                //Leave the cell null
                _river.AddHead(null);
            }
        }
    }

    //Method to check if there are any Fish in _river
    public static boolean IsFishInRiver(){
        //Loop through every Animal in _river
        DoublyLinkedList.Node tempNode = _river.GetHead(); //Start with head
        for (int i = 0; i < _river.GetSize(); i++) {
            if (tempNode.GetElement() != null) { //Ensure null cells don't get checked
                if (((Animal)tempNode.GetElement()).getClass() == Fish.class) {
                    //Then there is a Fish in _river, then the program needs to continue
                    return true;
                }
            }
            tempNode = tempNode.GetNext(); //Go to the next node
        }
        //There are no Fish
        return false;
    }

    //Method to get the current count of Animals and null cells
    public static void DisplayCounts(){
        int fishCount = 0;
        int bearCount = 0;
        int emptyCount = 0;
        DoublyLinkedList.Node tempNode = _river.GetHead(); //Start with head
        for (int i = 0; i < _river.GetSize(); i++) {
            if (tempNode.GetElement() != null) { //Exclude null cells to look for Animals
                if (tempNode.GetElement().getClass() == Fish.class) {
                    fishCount++;
                } else if (tempNode.GetElement().getClass() == Bear.class) {
                    bearCount++;
                }
            } else { //Null get counted here
                emptyCount++;
            }
            tempNode = tempNode.GetNext(); //Go to the next node
        }
        System.out.println("Theres " + fishCount + " fish, " + bearCount + " bears, and "
                + emptyCount + " empty cells in the river.");
    }

    //Main code
    public static void main(String[] args){
        /* Code to test
        _river = new DoublyLinkedList<Animal>();
        _river.AddHead(new Bear());
        _river.AddHead(new Fish());
        _river.AddHead(new Fish());
        _river.AddHead(new Bear());
        _river.AddTail(null);
        DisplayCounts();
        _river.GetHead().SwapNodes(true);
        DisplayCounts();
        if (_river.GetHead().GetElement().getClass() == Bear.class){
            System.out.println("It worked.");
        }
        if (_river.GetHead().GetNext().GetElement() == null){
            System.out.println("It worked again.");
        }
        if (IsFishInRiver()){
            System.out.println("There's a fish in the river.");
        }
        //_river.GetHead().GetNext().GetNext()._element = null;
        //_river.GetTail().GetPrevious().SwapNodes(true);
        if (!IsFishInRiver()){
            System.out.println("No fish.");
        }
        System.out.println(_river);
        _river.GetHead().SwapNodes(true);
        System.out.println(_river);
         */


        int count = 0; //Int to use as a counter
        PopulateRiver();
        DisplayCounts();
        boolean isGoingRight;
        Random rand = new Random(System.currentTimeMillis());



        while (IsFishInRiver()){
            DoublyLinkedList.Node tempNode = _river.GetHead(); //Start with the head
            for (int i = 0; i < _river.GetSize(); i++){
                if (i == 0){ //We are on the head and can only go right
                    isGoingRight = true;
                } else if (i == _river.GetSize()){ //We are on the tail and can only go left
                    isGoingRight = false;
                } else {
                    int x = rand.nextInt(2);
                    if (x == 0){ //Go right half the time
                        isGoingRight = true;
                    } else { //Go left half the time
                        isGoingRight = false;
                    }
                }
                if (tempNode.GetElement() != null) {
                    tempNode.SwapNodes(isGoingRight);
                }
                count++;
                if (count == 100){ //Counts are displayed every 100th time the for loop is iterated to shorten output frequency
                    DisplayCounts();
                    count = 0;
                }
                tempNode = tempNode.GetNext();
            }
        }
        System.out.println("There are no more Fish. This ecosystem is dead.");
    }
}
