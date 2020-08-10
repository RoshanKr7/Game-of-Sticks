import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class NimGame implements Game<List<Integer>, Integer, Integer> {

    public final static Integer[] players = {0, 1};
    public final static List<Integer> initialState = new ArrayList<Integer>();

    public NimGame(int size) {
    	int chance = new Random().nextInt(2);
        initialState.add(chance);
        initialState.add(size);
    }

    @Override
    public List<Integer> getInitialState() {
        return initialState;
    }

    @Override
    public Integer[] getPlayers() {
        return players;
    }

    @Override
    public Integer getPlayer(List<Integer> state) {
        return state.get(0);
    }

    @Override
    public List<Integer> getActions(List<Integer> state) {			//Get Valid Actions
        ArrayList<Integer> actions = new ArrayList<Integer>();
        for (int i = 1; i <= Math.min(3, state.get(1)); i++)
            actions.add(i);
        return actions;
    }

    @Override
    public List<Integer> getResult(List<Integer> state, Integer action) {
        ArrayList<Integer> newState = new ArrayList<Integer>();
        newState.add(1 - state.get(0));
        newState.add(state.get(1) - action);
        return newState;
    }

    @Override
    public boolean isTerminal(List<Integer> state) {
        return state.get(1) == 0;
    }

    @Override
    public double getUtility(List<Integer> state, Integer player) {
        if (state.get(0) == 1 - player) {
            if (state.get(1) == 1)
                return Double.POSITIVE_INFINITY;
            else if (((state.get(1) - 1) % 4) == 0)
                return 1;
            else
                return -1;
        } else {
            if (state.get(1) == 1)
                return Double.NEGATIVE_INFINITY;
            else if (((state.get(1) - 1) % 4) == 0)
                return -1;
            else
                return 1;
        }
    }
}