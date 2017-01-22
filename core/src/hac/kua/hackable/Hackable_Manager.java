package hac.kua.hackable;

import com.badlogic.gdx.utils.ObjectMap;

/**
 * Created by kevin on 1/21/2017.
 * A hackable 'manager' which maintains all the Hackable objects and their lua script instances
 * Each Hackable will be associated with a string. Two hackables cannot share the same key
 */
public final class Hackable_Manager {

    public static ObjectMap<String, Hackable> hackables = new ObjectMap<String, Hackable>();

    //Returns the size of hackables map
    public static int getSize(){ return hackables.size; }

    //Adds a new hackable to the map with the specified key.
    //The key can be used to access Hackables from within lua scripts
    public static void add(String key, Hackable obj)
    {
        if(hackables.containsKey(key)) {
            System.out.println("Key already exists!");
            return;
        }
        hackables.put(key, obj);
    }
}

