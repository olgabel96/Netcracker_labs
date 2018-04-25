import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Operator> operators;
    public Bank(){
        operators = new ArrayList<Operator>();
        Operator operator;
        for(int i = 0; i < Constant.OPERATORS_QUANTITY; i++){
            operator = new Operator();
            operators.add(operator);
            operator.start();
        }
    }

    public void open(){
        ClientGenerator clientGenerator = new ClientGenerator(this);
        clientGenerator.start();
    }

    public synchronized Operator getOperatorWithMinQueue(){
        Operator operator = null;
        int minQueue = Integer.MAX_VALUE;
        for (Operator o : this.operators){
            if (o.getQueueSize() < minQueue){
                minQueue = o.getQueueSize();
                operator = o;
            }
        }
        return operator != null?operator : operators.get(0);
    }
}
