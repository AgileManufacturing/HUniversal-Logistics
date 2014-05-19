package objects;

public class Route {
	private RoutePoint[] route;
	private RoutePoint[] conflicts;
	private boolean hasConflicts;
	/**
	 * @return the route
	 */
	public RoutePoint[] getRoute() {
		return route;
	}
	/**
	 * @param route the route to set
	 */
	public void setRoute(RoutePoint[] route) {
		this.route = route;
	}
	/**
	 * @return the conflicts
	 */
	public RoutePoint[] getConflicts() {
		return conflicts;
	}
	/**
	 * @param conflicts the conflicts to set
	 */
	public void setConflicts(RoutePoint[] conflicts) {
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
