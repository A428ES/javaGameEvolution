import classes.Weapon;

public class main{
    public static void main(String[] args){
        Weapon lightsaberSingle = new Weapon("lightsaber.json", false);
        lightsaberSingle.setValue(99999);
        lightsaberSingle.saveJson();

        Weapon newSabre = new Weapon("lightsaber.json", false);

        System.out.println(newSabre.getValue());
    }
}