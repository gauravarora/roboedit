package robotedit

import ca.odell.glazedlists.EventList
import ca.odell.glazedlists.BasicEventList
import ca.odell.glazedlists.SortedList
import groovy.beans.Bindable

class RobotEditModel {
   // @Bindable String propName
	EventList persons = new SortedList( new BasicEventList(),
		{a, b -> a.name <=> b.name} as Comparator)

	RobotEditModel() {
		persons.addAll([
			[name: 'Adam',  lastName: 'Savage'],
			[name: 'Jamie', lastName: 'Hyneman'],
			[name: 'Kari',  lastName: 'Byron'],
			[name: 'Grant', lastName: 'Imahara'],
			[name: 'Tori',  lastName: 'Belleci'],
			[name: 'Buster',  lastName: ''],
		])
	}
}