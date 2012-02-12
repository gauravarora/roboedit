package robotedit

import ca.odell.glazedlists.EventList
import ca.odell.glazedlists.BasicEventList
import ca.odell.glazedlists.SortedList
import groovy.beans.Bindable

class RobotEditModel {
	// @Bindable String propName
	EventList settings = new BasicEventList()
	EventList variables = new BasicEventList()
	EventList testcases= new BasicEventList()
	EventList keywords = new BasicEventList()
	def columns = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O']

	RobotEditModel() {
		
	}
}