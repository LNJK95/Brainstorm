package brainstorm;

public class Dumbfuck extends Enemy
{
    public Dumbfuck(int level) {
           super(level);
           maxHealth = 0;
           defence = 0;
           attack = 0;
           speed = 0;
           cHealth = maxHealth;
        }

        public String toString() {
    	return "Dumbfuck";
        }

}
