import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

public class Client {
    private String name;
    private static int id = 0;
    private long time;
    private int money;
    private Operation operation;

    public Client(long time, int money, Operation operation) {
        this.time = time;
        this.money = money;
        this.operation = operation;
        this.name = Constant.CLIENT_NAME + id++;
    }

    public String toString(){
        return this.name + "; Operation: " + this.operation + "; Money: " + this.money + "; Time: " + this.time;
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    public int getMoney() {
        return money;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
