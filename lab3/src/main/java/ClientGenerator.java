public class ClientGenerator extends Thread{

    private Bank bank;
    public ClientGenerator(Bank bank){
        this.bank = bank;
    }
    @Override
    public void run(){
        long time;
        int money;
        Operation operation;
        Client client;
        Operator operator;

        while (true){
            time = (long)(3000 + Math.random()*5000);  // от 3000 до 7999
            money = (int) (100 + Math.random()*(500+1)); //[100; 600]
            operation = Operation.getOperation();
            client = new Client(time, money, operation);
            System.out.println("Create " + client.toString());
            operator = bank.getOperatorWithMinQueue();
            operator.addClient(client);
            System.out.println(client.getName() + " is in the queue to " + operator.getOperatorName() + " queue size = " + operator.getQueueSize());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}