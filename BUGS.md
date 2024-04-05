# Bug Reports

Modify Reservation
------------------

* Modify reservation does not work when you supply to a room number that already
  has a reservation tied to it
    * Fix: Add an error message that states room is already reserved
* Modify reservation REQUIRES you to enter a Room Number and Price
    * Issue 1: User cannot modify just one of these issues
    * Issue 2: If user tries to bypass this and enter the room number they
      already have, then the modify cannot go through because the room
      is already tied to the reservation we are trying to modify.
        * Fix: Don't require all cells to be filled
* Modify reservation does not check that start/end dates are before today
    * Fix: we should only allow people to make reservations for today


Delete Reservation
------------------

* We are able to delete a reservation and have it reflect on the table
* HOWEVER, I am not sure if this is being updated in the database
* Bug reconstruction
    * Log-in
    * Delete both reservations tied to room 101
    * Try to modify a reservation and change room to 101
    * Confirm changes does not push any changes
        * Conclusion: room 101 reservation is just deleted from the table
          and NOT the database


Add Reservation
---------------

* Reservations are presumably created for the person currently logged in
* What if a client is logged-in? How do they create a reservation for a user?


Log-in
------

* Log-in does not verify password is correct, only that username is correct
