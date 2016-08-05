package player;

public class Attributes {

	enum Attribute{
		STRENGTH, DEXTERITY, ENDURANCE, SPEED, INTELLIGENS, LUCK
	};
	
	int strength;
	int dexterity;
	int endurance;
	int speed;
	int intelligens;
	int luck;
	
	
	
	protected Attributes(){
		
		int strength = 1;
		int dexterity = 1;
		int endurance = 1;
		int speed = 1;
		int intelligens = 1;
		int luck = 1;
		
	}
	
	int getAttribute(Attribute attribute){
		switch (attribute){
		case STRENGTH:
			return strength;
		case DEXTERITY:
			return dexterity;
		case ENDURANCE:
			return endurance;
		case SPEED:
			return speed;
		case INTELLIGENS:
			return intelligens;
		case LUCK:
			return luck;
		}
		return -1;		
	}
	
	void increaseAttribute(Attribute attribute, int var){
		switch (attribute){
		case STRENGTH:
			strength = strength + var;
		case DEXTERITY:
			 dexterity = dexterity + var;
		case ENDURANCE:
			 endurance = endurance + var;
		case SPEED:
			 speed = speed + var;
		case INTELLIGENS:
			 intelligens = intelligens + var;
		case LUCK:
			 luck = luck + var;
		}		
	}
	

}
