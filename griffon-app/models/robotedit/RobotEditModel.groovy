package robotedit

import ca.odell.glazedlists.EventList
import ca.odell.glazedlists.BasicEventList
import ca.odell.glazedlists.SortedList
import groovy.beans.Bindable

class RobotEditModel {
	// @Bindable String propName
	EventList persons = new BasicEventList()
	def columns = ['A', 'B', 'C', 'D']

	RobotEditModel() {
		
	}
}