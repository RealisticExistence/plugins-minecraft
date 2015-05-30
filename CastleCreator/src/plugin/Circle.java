package plugin;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edo on 18/04/15.
 */

class Loc {
    public final int x;
    public final int y;
    public final int z;

    public Loc(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}


public class Circle {

    List<Loc> locations;
    Loc center;
    String axe;
    int radius, index;

    World world;

    public Circle(Location location, int radius) {
        this(location, radius, "y");
    }

    public Circle(Location location, int radius, String axe) {

        int x0 = (int)location.getX();
        int y0 = (int)location.getY();
        int z0 = (int)location.getZ();
        world = location.getWorld();

        locations = new ArrayList<Loc>();
        center = new Loc(x0, y0, z0);
        this.axe = axe;
        this.radius = radius;
        index = 0;

        int f = 1 - radius;
        int ddF_x = 0;
        int ddF_y = -2 * radius;
        int x = 0;
        int y = radius;

        add(0, radius);
        while(x < y) {
            if (f >= 0) {
                y--;
                ddF_y += 2;
                f += ddF_y;
            }
            x++;
            ddF_x += 2;
            f += ddF_x + 1;
            add(x, y);
        }

        int i, size = locations.size() - 1;
        if (locations.get(size).x == locations.get(size).y) {
            size--;
        }
        for (i = size; i > 0; i--) {
            add(locations.get(i).y, locations.get(i).x);
        }

        size = locations.size();
        for (i = 0; i < size; i++) {
            add(- locations.get(i).y, locations.get(i).x);
        }

        size = locations.size();
        for (i = 0; i < size; i++) {
            add(- locations.get(i).x, - locations.get(i).y);
        }

    }

    private void add(int x, int y) {
        locations.add(new Loc(x, y, 0));
    }

    public boolean next() {
        if (index < locations.size() - 1) {
            index++;
            return true;
        } else {
            index = 0;
            return false;
        }
    }

    public void setAxe(String axe) {
        this.axe = axe;
    }

    public int getX() {
        return locations.get(index).x;
    }

    public int getY() {
        return locations.get(index).y;
    }

    public int getZ() {
        return locations.get(index).z;
    }

    public Location getLocation() {
        if (axe.equalsIgnoreCase("z")) {
            return new Location(world, (double)(getX() + center.x), getY() + center.y, getZ() + center.z);
        } else if (axe.equalsIgnoreCase("x")) {
            return new Location(world, getZ() + center.x, getX() + center.y, getY() + center.z);
        } else {
            return new Location(world, (double)(getY() + center.x), (double)(getZ() + center.y), (double)(getX() + center.z));
        }
    }

}
