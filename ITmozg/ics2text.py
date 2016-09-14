#!/usr/bin/env python

import argparse
from icalendar import Calendar

parser = argparse.ArgumentParser(
    description="Converts ICS calendar to human-readable format")
parser.add_argument("calendar", help="calendar file (e.g. example.ics)")
args = parser.parse_args()

g = open(args.calendar, 'rb')
gcal = Calendar.from_ical(g.read())
for component in gcal.walk():
    if component.name == "VEVENT":
        print component.get('summary') + " (" + \
            component.get('dtstart').to_ical() + " -- " + \
            component.get('dtend').to_ical() + ")"
g.close()
