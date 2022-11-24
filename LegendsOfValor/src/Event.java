public interface Event {
    // the event interface is evoked by a chance when a hero enter a cell
    // for this game, the event have two implementation Market and Battle
    boolean event_occur(double probability);
    

}
