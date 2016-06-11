
/**
  Given an bunch of airline tickets with [from, to], for example [MUC, LHR], [CDG, MUC], [SFO, SJC], [LHR, SFO], please reconstruct the itinerary in order, 
for example: [ CDG, MUC, LHR, SFO, SJC ]. 
//tickets can be represented as nodes

*/

import java.util.*;

class Flight {

    String from, to;
    Flight( String from, String to) {
        this.from = from;
        this.to = to;
    }
 }

class Node {

    String city;
    Node next;

    Node(String city) {
        this.city = city;
    }

}


class FindTrip {

    public static void main(String[] args) {

        List<Flight> flights = new ArrayList<>();   
        flights.add(new Flight("MUC","LHR"));
        flights.add(new Flight("CDG","MUC"));
        flights.add(new Flight("SFO","SJC"));
        flights.add(new Flight("LHR","SFO"));

	List<String> trip = findTrip(flights);
	for(String stop : trip ) {
		System.out.print(" " + stop);
	}
	System.out.println();
    }

    static List<String> findTrip(List<Flight> flights) {

        Map<String, Node> fromMap = new HashMap<>();
        Map<String, Node> toMap = new HashMap<>();

        for(Flight flight : flights) {
		Node from;
		if ( toMap.containsKey(flight.from) ) {
			from = toMap.remove(flight.from);
		} else {
			from = new Node(flight.from);
		}
		Node to;
		if (fromMap.containsKey(flight.to)) {
			to = fromMap.remove(flight.to);
		} else {
			to = new Node(flight.to);
		}
		from.next = to;
		fromMap.put(from.city, from);
		toMap.put(to.city, to);
        }
	Node start = null;
	for(Map.Entry<String,Node> entry : fromMap.entrySet() ) {
		start = (Node)entry.getValue();
		break;
        }
	List<String> ret = new ArrayList<>();
	while ( start != null ) {
        	ret.add(start.city);
		start = start.next;
        }
        return ret;
    }

}
