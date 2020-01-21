import edu.wpi.first.networktables.*;

public class Network {
    private NetworkTableInstance instance;
    private NetworkTable table;

    public Network() {
        instance = NetworkTableInstance.getDefault();
        table = instance.getTable("");
    }
}