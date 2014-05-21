package object;

import java.util.List;

/**
 * Represents a route in a grid by a {@link object.TransportAgent} object.
 */
public class Route {
	/**
	 * The list of {@link object.RoutePoint objects} of which the route consists.
	 */
	private List<RoutePoint> route;
	/**
	 * The list of {@link object.RoutePoint} objects conflicting with routes of
	 * other {@link object.TransportAgent} objects.</br>
	 * Intended for use by the CalculationService.</br>
	 * <b>Note: this is NOT set or checked by the {@link handler.DatabaseHandler}</b>
	 */
	private List<RoutePoint> conflicts;

	/**
	 * @return The list of {@link object.RoutePoint} objects describing the
	 * route of a transport agent in a grid.
	 */
	public List<RoutePoint> getRoute() {
		return route;
	}
	
	/**
	 * @param route The list of {@link object.RoutePoint} objects describing
	 * the route of a transport agent in a grid.
	 */
	public void setRoute(List<RoutePoint> route) {
		this.route = route;
	}
	
	/**
	 * @return The list of {@link object.RoutePoint} objects describing the
	 * conflicts of this route with routes of other transport agent in a grid.
	 */
	public List<RoutePoint> getConflicts() {
		return conflicts;
	}
	
	/**
	 * @param conflicts The list of {@link object.RoutePoint} objects describing
	 * the conflicts of this route with routes of other transport agent in a grid.
	 */
	public void setConflicts(List<RoutePoint> conflicts) {
		this.conflicts = conflicts;
	}
	
	/**
	 * @return Whether a route contains {@link object.RoutePoint} objects conflicting
	 * with {@link object.Route} objects of other {@link object.TransportAgents}
	 * objects.</br>
	 * Note: only checks whether the conflicts list of this object contains any entries.
	 */
	public boolean hasConflicts() {
		return !conflicts.isEmpty();
	}
	
	/**
	 * @return The ID of the {@link object.TransportAgent}
	 */
	public int getTransportAgentID() {
		if (route.isEmpty()) {
			return -1;
		} else {
			return route.get(0).getTransportAgentID();
		}
	}
}
