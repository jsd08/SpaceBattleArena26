import java.awt.Color;

import ihs.apcs.spacebattle.*;
import ihs.apcs.spacebattle.commands.*;

public class ExampleShip extends BasicSpaceship {
    private int worldWidth;
    private int worldHeight;
    private Point midpoint;
   
    public static void main(String[] args)
    {
        TextClient.run("10.56.98.121", new ExampleShip());
    }

    @Override
    public RegistrationData registerShip(int numImages, int worldWidth, int worldHeight)
    {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.midpoint = new Point(worldWidth / 2, worldHeight / 2);
        return new RegistrationData("jD Ship", new Color(50, 50, 75), 0);
    }

    public boolean isPointingAtMiddle(int angleToMidpoint)
    {
        return angleToMidpoint == 0;
    }

    public int getAngleToMidpoint(BasicEnvironment env)
    {
        ObjectStatus ship = env.getShipStatus();
        return ship.getPosition().getAngleTo(this.midpoint) - ship.getOrientation();
    }
   
    @Override
    public ShipCommand getNextCommand(BasicEnvironment env)
    {
        ObjectStatus ship = env.getShipStatus();
        double distanceToMid = ship.getPosition().getDistanceTo(this.midpoint);
       
        if (distanceToMid <= 300)
        {
           return new BrakeCommand(0.01);
        }
       
        int angleToMidpoint = getAngleToMidpoint(env);
       
        if (!isPointingAtMiddle(angleToMidpoint))
        {
           return new RotateCommand(angleToMidpoint);
        }
        else
        {
           if (distanceToMid < 500)
           {
             return new FireTorpedoCommand('F');
           }
            
            return new ThrustCommand('B', 2.0, 1.0);
        }
    }  
    }