package Backend.Callbacks;
import Backend.Utils.Position;


public interface MovementCallback {
    void call(Position oldPosition , Position newPosition);

}
