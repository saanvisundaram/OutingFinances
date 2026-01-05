# OutingFinances
Program to calculate transactions between friends after multiple events attended.

1. Input names & payment methods for all friends present
2. Input event names, total cost (& individual cost if values are not equal), and who paid for the event.
3. Get in return calculated values of how much each person owes someone else.

# Specific Class Functions:
  1. Person: Stores information about individuals, including name, payment method (any string acceptable, type or link to pay) and two separate calculations of how much money they owe another person. One calculation is done as a raw score, which does not take into account how much money the other person owes this person. This value is stored as an attribute. This class will also, at request, calculate how much the two people owe eachother, with simple calculation deductions.
  2. Event: Stores information about a specific event (ex: lunch), including name of event, person who paid for the event, how much the event was, who attended the event and did it cost the same for everyone (if no, how much it cost them individually). No calculation is actually performed here, but information about the event can be retrieved through a toString call.
  3. Outing: Stores information about people and events at a specific outing. Provides information about how much each person owes who for that outing.
  4. FrontEnd: Currently holds methods to add information about people, events and outings, and then presents user with final calculations.


Future directions (05/01/26):
  1. Add smaller commands
  2. Convert FrontEnd methods to outing methods
  3. Add ability to input additional event after calculation
