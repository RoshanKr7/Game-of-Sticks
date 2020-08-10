import java.util.List;
import java.util.Scanner;

public class NimGameplay {

    public static void main(String[] args) {
    	Scanner in = new Scanner(System.in);
    	int type;
    	System.out.println("Which type of battle, do you want?\n1.Human vs AI\n2.AI vs AI\n(Type the Required number)");
    	do {
    		type = in.nextInt();
    		if(type != 1 && type != 2)
    		{
    			System.out.println("Please choose a valid Agruments [1 or 2]");
    		}
    	}while(type != 1 && type != 2);
    	System.out.println("Initial No of Sticks?");
    	int sticks=in.nextInt();
        NimGame game = new NimGame(sticks);
        
        AlphaBetaSearch<List<Integer>, Integer, Integer> alphabetaSearch = AlphaBetaSearch.createFor(game);
        AlphaBetaSearch<List<Integer>, Integer, Integer> alphabetaSearch1 = AlphaBetaSearch.createFor(game);
        List<Integer> state = game.getInitialState();
        
        System.out.println("======================" + "\nPlayer " + (state.get(0)+1) +" has won the toss");
        while (!game.isTerminal(state)) {
            System.out.println("======================");
            System.out.println("No. of Sticks Left in the Heap is "+state.get(1));
            int action = -1;
            if (state.get(0) == 0) {
                //human
            	if(type == 1)
            	{
            		List<Integer> actions = game.getActions(state);
                    
                    while (!actions.contains(action)) {
                        System.out.println("player "+ (state.get(0)+1) +", what is your action?");
                        action = in.nextInt();
                        if(!actions.contains(action))
                        {
                        	System.out.println("Invalid Action!! Make a Valid move");
                        }
                    }
                }
            	else
            	{
            		//AI
            		System.out.println("player "+ (state.get(0)+1) +", what is your action?");
                    action = alphabetaSearch1.makeDecision(state);
            	}
            } else {
                //AI
                System.out.println("player "+ (state.get(0)+1) +", what is your action?");
                action = alphabetaSearch.makeDecision(state);
            }
            System.out.println("Chosen action is " + action);
            state = game.getResult(state, action);
        }
        System.out.print("GAME OVER: ");
        System.out.println("Player "+ (state.get(0)+1) + " wins!");
        in.close();

    }
}