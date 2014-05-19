package objects;

import java.util.List;

public class Route {
	private List<RoutePoint> route;
	private List<RoutePoint> conflicts;
	private boolean hasConflicts;
	/**
	 * @return the route
	 */
	public List<RoutePoint> getRoute() {
		return route;
	}
	/**
	 * @param route the route to set
	 */
	public void setRoute(List<RoutePoint> route) {
		this.route = route;
	}
	/**
	 * @return the conflicts
	 */
	public List<RoutePoint> getConflicts() {
		return conflicts;
	}
	/**
	 * @param conflicts the conflicts to set
	 */
	public void setConflicts(List<RoutePoint> conflicts) {
		this.conflicts = conflicts;
	}
	/**
	 * @return the hasConflicts
	 */
	public boolean isHasConflicts() {
		return hasConflicts;
	}
	/**
	 * @param hasConflicts the hasConflicts to set
	 */
	public void setHasConflicts(boolean hasConflicts) {
		this.hasConflicts = hasConflicts;
	}
}
