import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Operator extends Thread {
    private String name;
    private static int id = 0;
    private Queue<Client> queue;
    private Cashbox cashbox;

    public Operator(){
        this.queue = new ArrayBlockingQueue<Client>(500000);
        this.cashbox = Cashbox.getInstance();
        this.name = Constant.OPERATOR_NAME + id++;
    }

    public int getQueueSize(){
        return queue.size();
    }

    public void addClient(Client client){
        synchronized (queue){
            queue.add(client);
            queue.notify();
        }
    }

    @Override
    public void run(){
        Client client;
        while (true){
            while (this.queue.size() == 0) synchronized (queue) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            client = queue.poll();
            switch (client.getOperation()){
                case put:
                    this.cashbox.putMoney(client.getMoney());
                    System.out.println(client.getName() + " put " + client.getMoney());
                    break;
                case withdraw:
                    if(this.cashbox.getBalance() >= client.getMoney()){
                        this.cashbox.withdrawMoney(client.getMoney());
                        System.out.println(client.getName() + " withdraw " + client.getMoney());
                    }else{
                        System.out.println("\n" + client.getName() + " withdraw " + client.getMoney() + " There is not enough money in the cashbox!");
                    }
                    break;
            }
            System.out.println("CASHBOX: " + this.cashbox.getBalance() + "\n");
            try {
                Thread.sleep(client.getTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public String getOperatorName() {
        return name;
    }

    public Queue<Client> getQueue() {
        return queue;
    }

    public Cashbox getCashbox() {
        return cashbox;
    }

    public void setOperatorName(String name) {
        this.name = name;
    }

    public void setQueue(Queue<Client> queue) {
        this.queue = queue;
    }

    public void setCashbox(Cashbox cashbox) {
        this.cashbox = cashbox;
    }
}
