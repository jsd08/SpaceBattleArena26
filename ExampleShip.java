import java.awt.Color;

import ihs.apcs.spacebattle.*;
import ihs.apcs.spacebattle.commands.*;

public class ExampleShip extends BasicSpaceship {
    public static void main(String[] args)
    {
        TextClient.run("10.56.98.121", new ExampleShip());
    }

    @Override
    public RegistrationData registerShip(int numImages, int worldWidth, int worldHeight)
    {
        return new RegistrationData("jD SHip", new Color(50, 50, 75), 0);
    }

    @Override
    public ShipCommand getNextCommand(BasicEnvironment env)
    {
        ObjectStatus shipStatus = env.getShipStatus();

        // normalize
        while (difference > 180)
            difference -= 360;

        while (difference < -180)
            difference += 360;

        // rotate toward center
        if (difference > 5)
        {
            return new RotateCommand(5);
        }

        if (difference < -5)
        {
            return new RotateCommand(-5);
        }

        // thrust forward
        return new ThrustCommand(1.0);
    }
}