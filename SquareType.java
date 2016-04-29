package brainstorm;

/** SquareTypes are used in Map and are the
 * bottom layer. They are used in the collisionhandler.*/

public enum SquareType
{
    /** Outside is outside the visible frame. Keeps player
     * from walking outside the frame.*/
    OUTSIDE,
    /** Ground is what you can walk on.*/
    GROUND,
    /** Houses can be entered.*/
    HOUSE,
    /** Door lets you exit houses.*/
    DOOR
}
